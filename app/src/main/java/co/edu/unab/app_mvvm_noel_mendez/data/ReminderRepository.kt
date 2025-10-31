package co.edu.unab.app_mvvm_noel_mendez.data

import android.app.Application

class ReminderRepository(private val dao: ReminderDao) {

    val allReminders = dao.getAllReminders()

    suspend fun insert(reminder: Reminder) = dao.insert(reminder)
    suspend fun update(reminder: Reminder) = dao.update(reminder)
    suspend fun delete(reminder: Reminder) = dao.delete(reminder)

    companion object {
        @Volatile
        private var INSTANCE: ReminderRepository? = null

        fun getInstance(application: Application): ReminderRepository {
            return INSTANCE ?: synchronized(this) {
                val database = ReminderDatabase.getDatabase(application)
                val instance = ReminderRepository(database.reminderDao())
                INSTANCE = instance
                instance
            }
        }
    }
}