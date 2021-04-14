/*
 * Copyright (c) 2021/  4/ 14.  Created by Hashim Tahir
 */

package com.hashim.datastore.models

data class UiModel(
    val hTaskList: List<Task>,
    val hShowCompleted: Boolean,
    val hSortOrder: SortOrder
)
