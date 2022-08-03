package demo.com.mydoctors.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiseaseDAO {

    @Query("SELECT * FROM Disease WHERE Disease_Code == :diseaseCode AND  Language_Id == :languageId")
    fun getDisease(diseaseCode: String, languageId: String): LiveData<Disease>

//    @Query("SELECT * FROM Disease")
//    fun getAllDiseaseList()

    @Insert
    suspend fun insert(disease: Disease)

//    @Update
//    suspend fun update(disease: LiveData<Disease>)

    @Query("delete from Disease")
    fun deleteAllDisease()

    suspend fun updateDetails(disease: Disease) {
        val details = getDisease(disease.diseaseCode, disease.languageId)
        details.value?.diseaseName = disease.diseaseName
        details.value?.diseaseDescription = disease.diseaseDescription
        details.value?.imagesPath = disease.imagesPath
        details.value?.videosPath = disease.videosPath
        details.value?.dos = disease.dos
        details.value?.dont = disease.dont
        details.value?.medicine = disease.medicine
       // update(details)
    }

}