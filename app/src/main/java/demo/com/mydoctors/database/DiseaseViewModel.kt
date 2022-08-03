package demo.com.mydoctors.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DiseaseViewModel(private val repository: DiseaseRepository) : ViewModel() {

    val diseaseInfo: (String, String) -> LiveData<Disease> =
        { code: String, langId: String -> repository.getDiseaseInfo(code, langId) }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(disease: Disease) = viewModelScope.launch {
        repository.insert(disease)
    }
}

class DiseaseViewModelFactory(private val repository: DiseaseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiseaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DiseaseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}