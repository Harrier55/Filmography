package ru.harrier55.project.filmography.ui.googlemaps

import android.provider.Settings.Global.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.PlacesRootClass
import ru.harrier55.project.filmography.data.webconnection.WebConnectionOkHttp

private const val MAPS_PLACE_TYPES = "restaurant"
private const val MAPS_PLACE_ZOOM = 15f


class MapsFragmentViewModel : ViewModel() {


    private lateinit var placesRootClass: PlacesRootClass
    private val webConnectionOkHttp by lazy { WebConnectionOkHttp() }
    private val gson by lazy { Gson() }

    private val _placesRootClassStorage = MutableLiveData<PlacesRootClass>()
    val placesRootClassStorage: LiveData<PlacesRootClass>
        get() = _placesRootClassStorage


     fun getDataFromGooglePlacesApi(position: LatLng, key: String) {

        val urlPlacesApi =
            "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${position.latitude},${position.longitude}&radius=300&type=$MAPS_PLACE_TYPES&key=$key" // Tay
        webConnectionOkHttp.getDataFromWeb(urlPlacesApi, onCallbackFromGooglePlacesApi)
    }

    private val onCallbackFromGooglePlacesApi = object : OnCallbackFromGooglePlacesApi {
        override fun responsePlacesFromGooglePlacesApi(resultJsonString: String) {
            placesRootClass = gson.fromJson(resultJsonString, PlacesRootClass::class.java)
            _placesRootClassStorage.postValue(placesRootClass)
        }

        override fun errorPlacesFromGooglePlacesApi() {
            // todo
        }
    }
}