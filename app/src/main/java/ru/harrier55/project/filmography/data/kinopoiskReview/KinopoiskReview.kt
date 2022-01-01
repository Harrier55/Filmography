import com.google.gson.annotations.SerializedName


data class KinopoiskReview (

    @SerializedName("docs") val docs : List<DocsReview>,
    @SerializedName("total") val total : Int,
    @SerializedName("limit") val limit : Int,
    @SerializedName("page") val page : Int,
    @SerializedName("pages") val pages : Int
)