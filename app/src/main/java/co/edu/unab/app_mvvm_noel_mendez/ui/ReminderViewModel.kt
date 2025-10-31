package co.edu.unab.app_mvvm_noel_mendez.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unab.app_mvvm_noel_mendez.data.Reminder
import co.edu.unab.app_mvvm_noel_mendez.data.ReminderRepository
import co.edu.unab.app_mvvm_noel_mendez.utils.ViewModelFactory
import kotlinx.coroutines.launch

class ReminderViewModel(private val repository: ReminderRepository) : ViewModel() {

    val allReminders: LiveData<List<Reminder>> = repository.allReminders

    fun addReminder(title: String, description: String) {
        viewModelScope.launch {
            repository.insert(Reminder(title = title, description = description))
        }
    }

    fun toggleDone(reminder: Reminder) {
        viewModelScope.launch {
            repository.update(reminder.copy(isDone = !reminder.isDone))
        }
    }

    fun delete(reminder: Reminder) {
        viewModelScope.launch {
            repository.delete(reminder)
        }
    }

    companion object {
        fun factory(application: Application): ViewModelFactory {
            val repository = ReminderRepository.getInstance(application)
            return ViewModelFactory(repository)
        }
    }
}