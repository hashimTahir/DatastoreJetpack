/*
 * Copyright (c) 2021/  4/ 14.  Created by Hashim Tahir
 */

package com.hashim.datastore.repo

import com.hashim.datastore.models.Task
import com.hashim.datastore.models.TaskPriority
import kotlinx.coroutines.flow.flowOf
import java.text.SimpleDateFormat
import java.util.*

/*Sample data for the tasks*/
object TasksRepository {

    private val hSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    val hTasks = flowOf(
        listOf(
            Task(
                name = "Task 1",
                deadline = hSimpleDateFormat.parse("2020-07-03")!!,
                priority = TaskPriority.LOW,
                completed = true
            ),
            Task(
                name = "Task 2",
                deadline = hSimpleDateFormat.parse("2020-04-03")!!,
                priority = TaskPriority.MEDIUM,
                completed = true
            ),
            Task(
                name = "Task 3",
                deadline = hSimpleDateFormat.parse("2020-05-03")!!,
                priority = TaskPriority.LOW
            ),
            Task(
                name = "Task 4",
                deadline = hSimpleDateFormat.parse("2020-06-03")!!,
                priority = TaskPriority.HIGH
            ),
            Task(
                name = "Task 5",
                deadline = Date(),
                priority = TaskPriority.MEDIUM
            ),
        )
    )
}