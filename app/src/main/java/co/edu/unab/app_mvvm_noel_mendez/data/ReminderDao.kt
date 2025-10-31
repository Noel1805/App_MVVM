package co.edu.unab.app_mvvm_noel_mendez.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: Reminder)

    @Update
    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)

    @Query("SELECT * FROM reminder_table ORDER BY id DESC")
    fun getAllReminders(): LiveData<List<Reminder>>
}
