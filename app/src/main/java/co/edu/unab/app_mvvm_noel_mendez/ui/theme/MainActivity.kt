package co.edu.unab.app_mvvm_noel_mendez.ui.theme

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.edu.unab.app_mvvm_noel_mendez.data.ReminderDatabase
import co.edu.unab.app_mvvm_noel_mendez.data.ReminderRepository
import co.edu.unab.app_mvvm_noel_mendez.databinding.ActivityMainBinding
import co.edu.unab.app_mvvm_noel_mendez.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ReminderAdapter

    private val viewModel: ReminderViewModel by viewModels {
        val dao = ReminderDatabase.getDatabase(application).reminderDao()
        val repository = ReminderRepository(dao)
        ViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ReminderAdapter(
            onToggleDone = { viewModel.toggleDone(it) },
            onDelete = { viewModel.delete(it) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.allReminders.observe(this) { reminders ->
            adapter.submitList(reminders)
        }

        binding.btnAdd.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val desc = binding.etDescription.text.toString()
            if (title.isNotEmpty()) {
                viewModel.addReminder(title, desc)
                binding.etTitle.text.clear()
                binding.etDescription.text.clear()
            }
        }
    }
}
