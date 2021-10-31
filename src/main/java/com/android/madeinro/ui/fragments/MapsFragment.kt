package com.android.madeinro.ui.fragments

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.madeinro.BuildConfig.MAPS_API_KEY
import com.android.madeinro.Constants.cluj
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentMapsBinding
import com.android.madeinro.services.MapsService
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


class MapsFragment : Fragment(), View.OnClickListener {

    private var _mapsBinding: FragmentMapsBinding? = null
    private val mapsBinding get() = _mapsBinding!!

    var mapsService: MapsService = MapsService()
    private var tracker: SelectionTracker<Long>? = null

    private val productsAdapter by lazy { ProductsGuestAdapter() }
    private lateinit var map: GoogleMap
    private  var circle: Circle? = null

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        map.addMarker(MarkerOptions().position(cluj).title("Marker in Cluj").draggable(true))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cluj,12f))

        map.uiSettings.apply {
            //isZoomControlsEnabled = true
            //isMyLocationButtonEnabled = true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mapsBinding = FragmentMapsBinding.inflate(inflater,container, false)
        context?.let { Places.initialize(it, MAPS_API_KEY) }

        getAutocompleteResults()
        setupRecyclerView()

        // NEAR ME seekBar
        mapsBinding.nearMeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                //////mapsService.createCircle(i, map)
                createCircle(i)
                mapsBinding.progressSeekBarTextView.text = "$i km"
                val moveOffset: Int = i * (seekBar.width - 2 * seekBar.thumbOffset) / seekBar.max

                mapsBinding.progressSeekBarTextView.setX(seekBar.x + moveOffset + seekBar.thumbOffset / 2)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        // BUTTON Near Me
        mapsBinding.nearMeFloatingActionButton.setOnClickListener {
            toggleVisibilityNearMe()
        }

        // BUTTON Search
        mapsBinding.searchFloatingActionButton.setOnClickListener {
            toggleVisibilitySearch()
        }

        // BUTTON Products
        mapsBinding.foodFloatingActionButton.setOnClickListener{
            toggleVisibilitySelectedProducts()
        }

        // BUTTON Login
        mapsBinding.loginFloatingActionButton.setOnClickListener{
            this.onClick(mapsBinding.loginFloatingActionButton)
        }

        return mapsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mapsBinding = null
    }

    // TOGGLE
    private fun toggleVisibilityNearMe()
    {
        mapsBinding.searchCardView.visibility = View.INVISIBLE
        if(mapsBinding.nearMeSeekBar.visibility == View.VISIBLE){
            mapsBinding.nearMeSeekBar.visibility = View.INVISIBLE
            mapsBinding.searchFloatingActionButton.visibility = View.VISIBLE
            mapsBinding.progressSeekBarTextView.visibility = View.INVISIBLE
            circle?.remove()
        } else
        {
            mapsBinding.nearMeSeekBar.visibility = View.VISIBLE
            mapsBinding.searchFloatingActionButton.visibility = View.INVISIBLE
            mapsBinding.progressSeekBarTextView.visibility = View.VISIBLE
        }
    }

    // TOGGLE
    private fun toggleVisibilitySearch()
    {
        if(mapsBinding.searchCardView.visibility == View.VISIBLE){
            mapsBinding.nearMeFloatingActionButton.visibility = View.VISIBLE
            mapsBinding.searchCardView.visibility = View.INVISIBLE
            mapsBinding.searchFloatingActionButton.animate().translationX(0f)
        } else
        {
            mapsBinding.searchCardView.visibility = View.VISIBLE
            mapsBinding.searchFloatingActionButton.animate().translationX(-170.0f)
            mapsBinding.nearMeFloatingActionButton.visibility = View.INVISIBLE
        }
    }

    // TOGGLE
    private fun toggleVisibilitySelectedProducts()
    {
        if(mapsBinding.productsRecyclerView.visibility == View.VISIBLE){
            mapsBinding.productsRecyclerView.visibility = View.INVISIBLE
        } else
        {
            mapsBinding.productsRecyclerView.visibility = View.VISIBLE
        }
    }


    private fun setupRecyclerView(){
        mapsBinding.productsRecyclerView.adapter = productsAdapter
        mapsBinding.productsRecyclerView.layoutManager =  GridLayoutManager(requireContext(), 3, LinearLayoutManager.VERTICAL, false)

    }

    private fun getAutocompleteResults(){
        // initializare
        val autocompleteFragment = childFragmentManager.findFragmentById(R.id.autocompleteFragment)
                as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(listOf(
            Place.Field.ID, Place.Field.NAME,
            Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.ADDRESS_COMPONENTS, Place.Field.PHONE_NUMBER))

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Log.i(ContentValues.TAG, "Place: ${place.name}, ${place.id}")
                map.clear()
                map.addMarker(MarkerOptions().position(place.latLng!!).title(place.name).draggable(false))
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(place.latLng!!, 14f))

                // autocompleteFragment.clearFragmentResult("x") idk what is supposed to do
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

    // for navigation purposes
    override fun onClick(v: View) {
        //map.clear()
        onDestroyView()
        Navigation.findNavController(v).navigate(R.id.action_mapsFragment_to_loginFragment)
    }
}
