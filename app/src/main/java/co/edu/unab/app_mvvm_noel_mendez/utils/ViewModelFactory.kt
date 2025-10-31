package co.edu.unab.app_mvvm_noel_mendez.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.unab.app_mvvm_noel_mendez.data.ReminderRepository
import co.edu.unab.app_mvvm_noel_mendez.ui.theme.ReminderViewModel

class ViewModelFactory(private val repository: ReminderRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReminderViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
