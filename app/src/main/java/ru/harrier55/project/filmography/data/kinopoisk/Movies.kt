package com.example.example

import com.google.gson.annotations.SerializedName


data class Movies (

  @SerializedName("id"                ) var id               : Int?         = null,
  @SerializedName("id_kinopoisk"      ) var idKinopoisk      : Int?         = null,
  @SerializedName("url"               ) var url              : String?      = null,
  @SerializedName("type"              ) var type             : String?      = null,
  @SerializedName("title"             ) var title            : String?      = null,
  @SerializedName("title_alternative" ) var titleAlternative : String?      = null,
  @SerializedName("tagline"           ) var tagline          : String?      = null,
  @SerializedName("description"       ) var description      : String?      = null,
  @SerializedName("year"              ) var year             : Int?         = null,
  @SerializedName("poster"            ) var poster           : String?      = null,
  @SerializedName("trailer"           ) var trailer          : String?      = null,
  @SerializedName("age"               ) var age              : String?      = null,
  @SerializedName("actors"            ) var actors           : List<String> = arrayListOf(),
  @SerializedName("countries"         ) var countries        : List<String> = arrayListOf(),
  @SerializedName("genres"            ) var genres           : List<String> = arrayListOf(),
  @SerializedName("directors"         ) var directors        : List<String> = arrayListOf(),
  @SerializedName("screenwriters"     ) var screenwriters    : List<String> = arrayListOf(),
  @SerializedName("producers"         ) var producers        : List<String> = arrayListOf(),
  @SerializedName("operators"         ) var operators        : List<String> = arrayListOf(),
  @SerializedName("composers"         ) var composers        : List<String> = arrayListOf(),
  @SerializedName("painters"          ) var painters         : List<String> = arrayListOf(),
  @SerializedName("editors"           ) var editors          : List<String> = arrayListOf(),
  @SerializedName("budget"            ) var budget           : String?      = null,
  @SerializedName("rating_kinopoisk"  ) var ratingKinopoisk  : String?      = null,
  @SerializedName("rating_imdb"       ) var ratingImdb       : String?      = null,
  @SerializedName("kinopoisk_votes"   ) var kinopoiskVotes   : String?      = null,
  @SerializedName("imdb_votes"        ) var imdbVotes        : String?      = null,
  @SerializedName("fees_world"        ) var feesWorld        : String?      = null,
  @SerializedName("fees_russia"       ) var feesRussia       : String?      = null,
  @SerializedName("premiere_world"    ) var premiereWorld    : String?      = null,
  @SerializedName("premiere_russia"   ) var premiereRussia   : String?      = null,
  @SerializedName("frames"            ) var frames           : List<String> = arrayListOf(),
  @SerializedName("screenshots"       ) var screenshots      : List<String> = arrayListOf(),
  @SerializedName("videocdn"          ) var videocdn         : Videocdn?    = Videocdn(),
  @SerializedName("hdvb"              ) var hdvb             : Hdvb?        = Hdvb(),
  @SerializedName("collapse"          ) var collapse         : Collapse?    = Collapse(),
  @SerializedName("kodik"             ) var kodik            : Kodik?       = Kodik()

)