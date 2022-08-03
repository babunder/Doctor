package demo.com.mydoctors.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Disease")
data class Disease(

    @PrimaryKey
    @ColumnInfo(name = "Disease_Code")
    var diseaseCode: String,

    @ColumnInfo(name = "Disease_Name")
    var diseaseName: String,

    @ColumnInfo(name = "Disease_Description")
    var diseaseDescription: String,

    @ColumnInfo(name = "Language_Id")
    var languageId: String,

    @ColumnInfo(name = "Images_Path")
    var imagesPath: String,

    @ColumnInfo(name = "Videos_Path")
    var videosPath: String,

    @ColumnInfo(name = "Dos")
    var dos: String,

    @ColumnInfo(name = "Dont")
    var dont: String,

    @ColumnInfo(name = "Medicine")
    var medicine: String
)