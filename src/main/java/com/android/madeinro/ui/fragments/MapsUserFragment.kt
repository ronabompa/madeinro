package com.android.madeinro.ui.fragments

import android.content.ContentValues
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.madeinro.BuildConfig
import com.android.madeinro.Constants
import com.android.madeinro.Constants.cluj
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentMapsUserBinding
import com.android.madeinro.ui.adapters.ProductsClientAdapter
import com.android.madeinro.ui.adapters.ProductsGuestAdapter
import com.google.android.gms.common.api.Status

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlin.math.log2

class MapsUserFragment : Fragment(), View.OnClickListener {

    private lateinit var instance: MapsUserFragment

    private var _mapsUserBinding: FragmentMapsUserBinding? = null
    private val mapsUserBinding get() = _mapsUserBinding!!
    private val productsAdapter by lazy { ProductsClientAdapter() }

    private lateinit var map: GoogleMap
    private  var circle: Circle? = null


    fun  getInstance(): MapsUserFragment{
        instance = MapsUserFragment()
        return instance
    }

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        map.addMarker(MarkerOptions().position(cluj).title("Marker in Cluj").draggable(true))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cluj,12f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _mapsUserBinding = FragmentMapsUserBinding.inflate(inflater,container, false)
        context?.let { Places.initialize(it, BuildConfig.MAPS_API_KEY) }

        setupRecyclerView()
        getAutocompleteResults()

        // NEAR ME seekBar
        mapsUserBinding.nearMeUserSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                createCircle(i)
                mapsUserBinding.progressSeekBarUserMapTextView.text = "$i km"
                val moveOffset: Int = i * (seekBar.width - 2 * seekBar.thumbOffset) / seekBar.max

                mapsUserBinding.progressSeekBarUserMapTextView.setX(seekBar.x + moveOffset + seekBar.thumbOffset / 2)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        // BUTTON Near Me
        mapsUserBinding.nearMeUserFloatingActionButton.setOnClickListener {
            toggleVisibilityNearMe()
        }

        // BUTTON Search
        mapsUserBinding.searchUserFloatingActionButton.setOnClickListener {
            toggleVisibilitySearch()
        }

        // BUTTON Products
        mapsUserBinding.productsUserFloatingActionButton.setOnClickListener{
            toggleVisibilitySelectedProducts()
        }

        // BUTTON FAB Menu
        mapsUserBinding.menuFloatingActionButton.setOnClickListener{
            // toggleVisibility little FAB
            // de modficiat, dar asta momentan pentru LOG OUT
            toggleVisibilityFABMenu()
        }

        // BUTTON FAB Chat
        mapsUserBinding.chatFloatingActionButton.setOnClickListener{
            this.onClick(mapsUserBinding.chatFloatingActionButton)
        }

        // BUTTON FAB Settings
        mapsUserBinding.settingsFloatingActionButton.setOnClickListener{
            this.onClick(mapsUserBinding.settingsFloatingActionButton)
        }

        // BUTTON FAB Add
        mapsUserBinding.shoppingCartFloatingActionButton.setOnClickListener{
            this.onClick(mapsUserBinding.shoppingCartFloatingActionButton)
        }

        // BUTTON FAB Favorites
        mapsUserBinding.favoriteFloatingActionButton.setOnClickListener{
            this.onClick(mapsUserBinding.favoriteFloatingActionButton)
        }

        return mapsUserBinding.root
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapUser) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    // for navigation purposes
    override fun onClick(v: View) {
        if(v == mapsUserBinding.chatFloatingActionButton) {
            Navigation.findNavController(v).navigate(R.id.action_mapsUserFragment_to_chatFragment)
        } else if(v == mapsUserBinding.settingsFloatingActionButton){
            Navigation.findNavController(v).navigate(R.id.action_mapsUserFragment_to_customSettingsFragment)
        } else if(v == mapsUserBinding.shoppingCartFloatingActionButton){
                Navigation.findNavController(v).navigate(R.id.action_mapsUserFragment_to_shopppingCartFragment)
            } else if(v == mapsUserBinding.menuFloatingActionButton){
            Navigation.findNavController(v).navigate(R.id.action_mapsUserFragment_to_loginFragment)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _mapsUserBinding = null
    }

    // TOGGLE
    private fun toggleVisibilityNearMe()
    {
        mapsUserBinding.searchUserCardView.visibility = View.INVISIBLE
        if(mapsUserBinding.nearMeUserSeekBar.visibility == View.VISIBLE){
            mapsUserBinding.nearMeUserSeekBar.visibility = View.INVISIBLE
            mapsUserBinding.searchUserFloatingActionButton.visibility = View.VISIBLE
            circle?.remove()
        } else
        {
            mapsUserBinding.nearMeUserSeekBar.visibility = View.VISIBLE
            mapsUserBinding.searchUserFloatingActionButton.visibility = View.INVISIBLE
        }
    }

    // TOGGLE
    private fun toggleVisibilitySearch()
    {
        if(mapsUserBinding.searchUserCardView.visibility == View.VISIBLE){
            mapsUserBinding.nearMeUserFloatingActionButton.visibility = View.VISIBLE
            mapsUserBinding.searchUserCardView.visibility = View.INVISIBLE
            mapsUserBinding.searchUserFloatingActionButton.animate().translationX(0f)
        } else
        {
            mapsUserBinding.searchUserCardView.visibility = View.VISIBLE
            mapsUserBinding.searchUserFloatingActionButton.animate().translationX(-170.0f)
            mapsUserBinding.nearMeUserFloatingActionButton.visibility = View.INVISIBLE
        }
    }

    // TOGGLE
    private fun toggleVisibilitySelectedProducts()
    {
        if(mapsUserBinding.productsUserRecyclerView.visibility == View.VISIBLE){
            mapsUserBinding.productsUserRecyclerView.visibility = View.INVISIBLE
        } else
        {
            mapsUserBinding.productsUserRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun toggleVisibilityFABMenu()
    {
        if(mapsUserBinding.chatFloatingActionButton.visibility == View.VISIBLE){
            mapsUserBinding.chatFloatingActionButton.visibility = View.INVISIBLE
            mapsUserBinding.favoriteFloatingActionButton.visibility = View.INVISIBLE
            mapsUserBinding.settingsFloatingActionButton.visibility = View.INVISIBLE
            mapsUserBinding.shoppingCartFloatingActionButton.visibility = View.INVISIBLE
        } else
        {
            mapsUserBinding.chatFloatingActionButton.visibility = View.VISIBLE
            mapsUserBinding.favoriteFloatingActionButton.visibility = View.VISIBLE
            mapsUserBinding.settingsFloatingActionButton.visibility = View.VISIBLE
            mapsUserBinding.shoppingCartFloatingActionButton.visibility = View.VISIBLE
        }
    }

    private fun getAutocompleteResults(){
        val autocompleteUserFragment = childFragmentManager.findFragmentById(R.id.autocompleteUserFragment)
                as AutocompleteSupportFragment

        autocompleteUserFragment.setPlaceFields(listOf(
            Place.Field.ID, Place.Field.NAME,
            Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.ADDRESS_COMPONENTS, Place.Field.PHONE_NUMBER))

        autocompleteUserFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Log.i(ContentValues.TAG, "Place: ${place.name}, ${place.id}")
                map.clear()
                map.addMarker(MarkerOptions().position(place.latLng!!).title(place.name).draggable(false))
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(place.latLng!!, 14f))

            }
            override fun onError(status: Status) {
                Log.i(ContentValues.TAG, "An error occurred: $status")
            }
        })
    }

    private fun createCircle(i: Int) {
        if(circle == null) {
            circle = map.addCircle(CircleOptions().apply {
                center(cluj)
                radius(i*1000.0)
                fillColor(R.color.white)
                strokeColor(R.color.green_700)
            })
            map.animateCamera(CameraUpdateFactory.zoomTo((log2(20000.0/i).toFloat())))
        } else
        {
            circle?.remove()
            circle = map.addCircle(CircleOptions().apply {
                center(cluj)
                radius(i * 1000.0)
                fillColor(R.color.white)
                strokeColor(R.color.green_700)
            })
            map.animateCamera(CameraUpdateFactory.zoomTo((log2(20000.0 / i).toFloat())))
        }
    }

    private fun setupRecyclerView(){
        mapsUserBinding.productsUserRecyclerView.adapter = productsAdapter
        mapsUserBinding.productsUserRecyclerView.layoutManager =  GridLayoutManager(requireContext(), 3, LinearLayoutManager.VERTICAL, false)
    }

}