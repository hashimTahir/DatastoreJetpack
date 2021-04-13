/*
 * Copyright (c) 2021/  4/ 13.  Created by Hashim Tahir
 */

package com.hashim.datastore

import java.util.*


/*Model for the task object*/
data class Task(
    val name: String,
    val deadline: Date,
    val priority: TaskPriority,
    val completed: Boolean = false
)