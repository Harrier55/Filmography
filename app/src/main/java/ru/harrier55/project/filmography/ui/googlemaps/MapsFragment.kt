package ru.harrier55.project.filmography.ui.googlemaps

import androidx.fragment.app.Fragment

import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.PlacesRootClass
import ru.harrier55.project.filmography.data.Result


private const val GPS_PERMISSION = android.Manifest.permission.ACCESS_FINE_LOCATION
private const val MAPS_PLACE_ZOOM = 15f

class MapsFragment : Fragment() {

    private val TAG: String = "@@@"
    private val viewModel by lazy { ViewModelProvider(this)[MapsFragmentViewModel::class.java] }
    var mapView: GoogleMap? = null
    private var gpsPosition: LatLng = LatLng(55.1640, 61.4428) // Chelyabinsk


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permissionCheck.launch(GPS_PERMISSION)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        viewModel.placesRootClassStorage.observe(viewLifecycleOwner, Observer{
            fillMarkers(it)
        })
        viewModel.errorList.observe(viewLifecycleOwner, Observer {
            showToast(it.toString())
        })
    }

    /**Проверяем разрешение на геопозиционирование*/
    private val permissionCheck =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
             if (!isGranted) {
                showToast("Разрешение не получено")
            }
        }

    private val callback = OnMapReadyCallback { googleMap ->
        mapView = googleMap
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.addMarker(MarkerOptions().position(gpsPosition).title("Marker "))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(gpsPosition))

        googleMap.setOnMapLongClickListener { onLongClickMap(it) }
    }

    private fun onLongClickMap(clickPosition: LatLng) {
        gpsPosition = LatLng(clickPosition.latitude, clickPosition.longitude)
        val key: String = this.resources.getString(R.string.api_key)

        viewModel.getDataFromGooglePlacesApi(gpsPosition, key)
    }

    private fun fillMarkers(placesRootClass: PlacesRootClass) {
        for (result: Result in placesRootClass.results) {
            val mapPositionGooglePlace =
                LatLng(result.geometry.location.lat, result.geometry.location.lng)
            Log.d(TAG, "onCallback Markers$mapPositionGooglePlace")
            mapView!!.addMarker(
                MarkerOptions().position(mapPositionGooglePlace).title(result.name)
            )
        }
        showMarkers(gpsPosition)
    }

    private fun showMarkers(position: LatLng) {
        mapView!!.addMarker(
            MarkerOptions().position(position).title("It's I am")
                .icon(BitmapDescriptorFactory.defaultMarker(150f))
        )
        mapView!!.moveCamera(CameraUpdateFactory.newLatLng(position))
        mapView!!.animateCamera(CameraUpdateFactory.zoomTo(MAPS_PLACE_ZOOM))
    }

    private fun showToast(send: String){
        Toast.makeText(requireContext(),send,Toast.LENGTH_SHORT).show()
    }


}