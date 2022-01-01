import com.google.gson.annotations.SerializedName



data class DocsReview (
	@SerializedName("id") val id : Int?,
	@SerializedName("movieId") val movieId : Int?,
	@SerializedName("title") val title : String?,
	@SerializedName("type") val type : String?,
	@SerializedName("review") val review : String?,
	@SerializedName("date") val date : String?,
	@SerializedName("author") val author : String?
)