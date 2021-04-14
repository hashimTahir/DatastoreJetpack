/*
 * Copyright (c) 2021/  4/ 14.  Created by Hashim Tahir
 */

package com.hashim.datastore.ui

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hashim.datastore.R
import com.hashim.datastore.models.Task
import com.hashim.datastore.models.TaskPriority.*
import com.hashim.datastore.databinding.TaskViewItemBinding
import java.text.SimpleDateFormat
import java.util.*


class TaskViewHolder(
    private val hTaskViewItemBinding: TaskViewItemBinding
) : RecyclerView.ViewHolder(hTaskViewItemBinding.root) {

    private val hDateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)


    fun hBind(task: Task) {
        hTaskViewItemBinding.hTaskName.text = task.name
        setTaskPriority(task)
        hTaskViewItemBinding.hTaskDeadLine.text = hDateFormat.format(task.deadline)
        val hColor = if (task.completed) {
            R.color.teal_200
        } else {
            R.color.white
        }
        itemView.setBackgroundColor(
            ContextCompat.getColor(
                itemView.context,
                hColor
            )
        )
    }

    private fun setTaskPriority(task: Task) {
        hTaskViewItemBinding.hTaskPriority.text = itemView.context.resources.getString(
            R.string.priority_value,
            task.priority.name
        )
        val hTextColor = when (task.priority) {
            HIGH -> R.color.red
            MEDIUM -> R.color.yellow
            LOW -> R.color.green
        }
        hTaskViewItemBinding.hTaskPriority.setTextColor(
            ContextCompat.getColor(
                itemView.context,
                hTextColor
            )
        )
    }
}