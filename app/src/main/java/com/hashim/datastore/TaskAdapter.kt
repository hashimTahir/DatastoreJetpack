/*
 * Copyright (c) 2021/  4/ 13.  Created by Hashim Tahir
 */

package com.hashim.datastore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hashim.datastore.databinding.TaskViewItemBinding


class TasksAdapter : ListAdapter<Task, TaskViewHolder>(TASKS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val hItem = getItem(position)
        if (hItem != null) {
            holder.hBind(hItem)
        }
    }

    companion object {
        private val TASKS_COMPARATOR = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem == newItem
        }
    }
}