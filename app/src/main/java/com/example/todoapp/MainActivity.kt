package com.example.todoapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.model.Task
import com.example.todoapp.ui.list.TaskAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var etxtTask: EditText
    private lateinit var stUrgent : Switch
    private lateinit var tasks: ArrayList<Task>
    private lateinit var rvTaskBoard: RecyclerView
    private lateinit var chatMessageAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.etxtTask = findViewById(R.id.etxtTask)
        this.stUrgent = findViewById(R.id.st_urgent)
        this.rvTaskBoard = findViewById(R.id.rvTaskBoard)



        if(savedInstanceState == null)
            this.tasks = ArrayList()
        else if(savedInstanceState.containsKey("RECOVER_MESSAGES")) {
            this.tasks =
                savedInstanceState.getParcelableArrayList<Task>("RECOVER_MESSAGES")
                        as ArrayList<Task>
        }
        this.chatMessageAdapter = TaskAdapter(this.tasks)
        this.chatMessageAdapter.setOnClickBalloonListener { task ->
            Toast.makeText(this, task.description, Toast.LENGTH_SHORT).show()
        }
        this.chatMessageAdapter.setOnLongClickBalloonListener { message ->
            val index = this.tasks.indexOf(message)
            this.tasks.removeAt(index)
            this.chatMessageAdapter.notifyItemRemoved(index)
        }
        this.rvTaskBoard.layoutManager = LinearLayoutManager(this)
        this.rvTaskBoard.adapter = this.chatMessageAdapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("RECOVER_MESSAGES", this.tasks)
    }

    fun onClickAdd(v: View) {
        val messageText = this.etxtTask.text.toString()
        if(messageText.isNotBlank()) {
            val tsk = Task(messageText,
                this.stUrgent.isChecked
                )
            this.tasks.add(tsk)
            this.chatMessageAdapter.notifyItemInserted(this.tasks.size-1)
            this.rvTaskBoard.scrollToPosition(this.tasks.size-1)
            this.etxtTask.text.clear()
        }
    }
}