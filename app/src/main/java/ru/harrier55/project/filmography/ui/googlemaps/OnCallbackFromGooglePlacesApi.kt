package ru.harrier55.project.filmography.ui.googlemaps

import com.google.android.gms.maps.model.LatLng
import ru.harrier55.project.filmography.data.PlacesRootClass
import java.io.IOException

interface OnCallbackFromGooglePlacesApi {
    fun responsePlacesFromGooglePlacesApi(resultJsonString: String)
    fun errorPlacesFromGooglePlacesApi( e: IOException)
}