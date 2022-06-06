package demo.com.mydoctors.model

data class DiseasesDetails(
    var diseases_code: String,
    var diseases_name: String,
    var diseases_description: String,
    var languange_id: String,
    var image_path: List<String>,
    var youtube_link: List<String>,
    var Dos: String,
    var Dont: String,
    var Medicine: String
)