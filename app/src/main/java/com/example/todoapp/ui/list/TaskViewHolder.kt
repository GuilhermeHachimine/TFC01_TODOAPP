package com.example.todoapp.ui.list;

import android.graphics.Color
import android.view.View;
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.model.Task

class TaskViewHolder(
    itemView : View,
    private val adapter: TaskAdapter
): RecyclerView.ViewHolder(itemView) {
    // findViewById é um algoritmo de busca em árvore O(nlogn), podemos evitar
// overloading buscando uma vez e "segurando" o endereço de memória
// dos componentes de interesse.
    private val txtTask: TextView = itemView.findViewById(R.id.txtTask)
    private val cbTaskStatus: CheckBox = itemView.findViewById(R.id.cbTaskStatus)
    private val cvTask: CardView = itemView.findViewById(R.id.cvTask)
    private lateinit var currentTask: Task

    init {
        itemView.setOnClickListener {
            this.adapter.getOnClickBalloonListener()?.onClickBalloon(this.currentTask)
        }
        itemView.setOnLongClickListener {
            this.adapter.getOnLongClickBalloonListener()?.onLongClickBalloon(this.currentTask)
            true
        }
    }


    fun bind(task: Task) {
        this.currentTask = task
        if(this.currentTask.isUrgent) {
            this.cvTask.setCardBackgroundColor(Color.parseColor("#FFFF0000"))
        } else {
            this.cvTask.setCardBackgroundColor(Color.parseColor("#FF8CC63F"))
        }
        this.txtTask.text = this.currentTask.description
        this.cbTaskStatus.isChecked = this.currentTask.isDone
    }
}