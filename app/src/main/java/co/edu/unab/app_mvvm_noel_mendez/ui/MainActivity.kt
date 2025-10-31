package co.edu.unab.app_mvvm_noel_mendez.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.edu.unab.app_mvvm_noel_mendez.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ReminderViewModel by viewModels {
        ReminderViewModel.factory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout con ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        val adapter = ReminderAdapter(
            onDelete = { reminder -> viewModel.delete(reminder) },
            onToggleDone = { reminder -> viewModel.toggleDone(reminder) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observar los datos del ViewModel
        viewModel.allReminders.observe(this) { reminders ->
            adapter.submitList(reminders)
        }

        // Botón para agregar recordatorio
        binding.btnAdd.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                viewModel.addReminder(title, description)
                binding.etTitle.text.clear()
                binding.etDescription.text.clear()
            }
        }
    }
}
