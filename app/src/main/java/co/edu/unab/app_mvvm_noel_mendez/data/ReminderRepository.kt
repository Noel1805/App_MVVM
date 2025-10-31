package co.edu.unab.app_mvvm_noel_mendez.data

class ReminderRepository(private val dao: ReminderDao) {

    val allReminders = dao.getAllReminders()

    suspend fun insert(reminder: Reminder) = dao.insert(reminder)
    suspend fun update(reminder: Reminder) = dao.update(reminder)
    suspend fun delete(reminder: Reminder) = dao.delete(reminder)
}
