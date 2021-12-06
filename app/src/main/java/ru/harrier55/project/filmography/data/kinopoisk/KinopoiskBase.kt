import com.google.gson.annotations.SerializedName


data class KinopoiskBase (

	@SerializedName("docs") val docs : List<Docs>,
	@SerializedName("total") val total : Int,
	@SerializedName("limit") val limit : Int,
	@SerializedName("page") val page : Int,
	@SerializedName("pages") val pages : Int
)