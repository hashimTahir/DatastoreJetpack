/*
 * Copyright (c) 2021/  4/ 14.  Created by Hashim Tahir
 */

package com.hashim.datastore.ui

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hashim.datastore.Constants.Companion.USER_PREFERENCES_NAME
import com.hashim.datastore.models.SortOrder
import com.hashim.datastore.models.Task
import com.hashim.datastore.models.UiModel
import com.hashim.datastore.models.UserPreferences
import com.hashim.datastore.repo.TasksRepository
import com.hashim.datastore.repo.UserPreferencesRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {


    private val Context.hDataStrore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    val hTaskRepo = TasksRepository
    private val hUserRepo = UserPreferencesRepository(application.hDataStrore)

    private val hUserPrefrencesFlow = hUserRepo.hUserPrefrencesFlow


    /*Take tasks lists and  What settings are stored in data store.
    * combine two flows, resort the list*/
    private val hUiFlow = combine(
        hTaskRepo.hTasks,
        hUserPrefrencesFlow
    ) { taskList: List<Task>, userPref: UserPreferences ->
        return@combine UiModel(
            hTaskList = hFilterTaskList(
                taskList,
                userPref.hShowCompleted,
                userPref.hSortOrder,
            ),
            hShowCompleted = userPref.hShowCompleted,
            hSortOrder = userPref.hSortOrder
        )
    }

    val hUiLD = hUiFlow.asLiveData()

    private fun hFilterTaskList(
        taskList: List<Task>,
        hShowCompleted: Boolean,
        hSortOrder: SortOrder
    ): List<Task> {
        // filter the tasks
        val hFilteredTasks = if (hShowCompleted) {
            taskList
        } else {
            taskList.filter { !it.completed }
        }
        // sort the tasks
        return when (hSortOrder) {
            SortOrder.NONE -> hFilteredTasks
            SortOrder.BY_DEADLINE -> hFilteredTasks.sortedByDescending { it.deadline }
            SortOrder.BY_PRIORITY -> hFilteredTasks.sortedBy { it.priority }
            SortOrder.BY_DEADLINE_AND_PRIORITY -> hFilteredTasks.sortedWith(
                compareByDescending<Task> { it.deadline }.thenBy { it.priority }
            )
        }
    }


    fun hShowComletedTasks(checked: Boolean) {
        viewModelScope.launch {
            hUserRepo.hUpdateCompletedTasks(checked)
        }
    }

    fun hEnableDisableDeadline(checked: Boolean) {
        viewModelScope.launch {
            hUserRepo.hEnableDisableSortByDeadLine(checked)
        }
    }

    fun hSortByPririoty(checked: Boolean) {
        viewModelScope.launch {
            hUserRepo.hEnableDisableSortByPririoty(checked)
        }
    }


}