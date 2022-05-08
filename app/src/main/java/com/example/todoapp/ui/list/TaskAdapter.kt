package com.example.todoapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.model.Task

class TaskAdapter(
    private var tasks: ArrayList<Task>
): RecyclerView.Adapter<TaskViewHolder>() {
    private var listener: OnClickBalloonListener? = null
    private var longListener: OnLongClickBalloonListener? = null
    fun interface OnClickBalloonListener {
        fun onClickBalloon(task: Task)
    }
    fun interface OnLongClickBalloonListener {
        fun onLongClickBalloon(message: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutRes = R.layout.itemview_task;
        val itemView = layoutInflater.inflate(layoutRes, parent, false)
        return TaskViewHolder(itemView, this)
    }
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(this.tasks[position])
    }
    override fun getItemCount(): Int {
        return this.tasks.size
    }
//    override fun getItemViewType(position: Int): Int {
//        return if(this.tasks[position].isReceived) RCVD else SEND
//    }
    // metodos auxiliares
    fun setOnClickBalloonListener(listener: OnClickBalloonListener?) {
        this.listener = listener
    }
    fun getOnClickBalloonListener(): OnClickBalloonListener? {
        return this.listener
    }
    fun setOnLongClickBalloonListener(listener: OnLongClickBalloonListener?) {
        this.longListener = listener
    }
    fun getOnLongClickBalloonListener(): OnLongClickBalloonListener? {
        return this.longListener
    }
}