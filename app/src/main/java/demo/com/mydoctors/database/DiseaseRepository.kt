package demo.com.mydoctors.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class DiseaseRepository(private val diseaseDAO: DiseaseDAO) {

    fun getDiseaseInfo(diseaseCode: String, languageId: String): LiveData<Disease> {
        return diseaseDAO.getDisease(diseaseCode, languageId)
    }

    @WorkerThread
    suspend fun insert(disease: Disease) {
        diseaseDAO.insert(disease)
    }

    @WorkerThread
    suspend fun update(disease: Disease) {
        diseaseDAO.updateDetails(disease)
    }
}