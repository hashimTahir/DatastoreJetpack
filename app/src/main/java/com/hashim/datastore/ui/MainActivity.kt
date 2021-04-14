/*
 * Copyright (c) 2021/  4/ 14.  Created by Hashim Tahir
 */

package com.hashim.datastore.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.hashim.datastore.TasksAdapter
import com.hashim.datastore.databinding.ActivityMainBinding
import com.hashim.datastore.models.SortOrder
import timber.log.Timber

class MainActivity : AppCompatActivity() {


    private val hTaskViewModel: MainViewModel by viewModels()

    lateinit var hActivityMainBinding: ActivityMainBinding

    private val hTaskAdapter = TasksAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(hActivityMainBinding.root)

        hInitRecyclerView()
        hSetupListeners()



        hTaskViewModel.hUiLD.observe(this) {

            hTaskAdapter.submitList(it.hTaskList)

            hActivityMainBinding.hSortChip.isChecked =
                it.hSortOrder == SortOrder.BY_DEADLINE || it.hSortOrder == SortOrder.BY_DEADLINE_AND_PRIORITY
            hActivityMainBinding.hDeadLineChip.isChecked =
                it.hSortOrder == SortOrder.BY_PRIORITY || it.hSortOrder == SortOrder.BY_DEADLINE_AND_PRIORITY
        }
    }

    private fun hSetupListeners() {
        hActivityMainBinding.hCompletedSw.setOnCheckedChangeListener { _, checked ->
            hTaskViewModel.hShowComletedTasks(checked)
        }

        hActivityMainBinding.hDeadLineChip.setOnCheckedChangeListener { _, checked ->
            Timber.d("Dead Lind chip")
            if (hActivityMainBinding.hDeadLineChip.isPressed) {
                hTaskViewModel.hEnableDisableDeadline(checked)
            }
        }
        hActivityMainBinding.hSortChip.setOnCheckedChangeListener { _, checked ->
            if (hActivityMainBinding.hSortChip.isPressed) {
                hTaskViewModel.hSortByPririoty(checked)
            }
        }
    }


    private fun hInitRecyclerView() {
        val hItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        hActivityMainBinding.hTasksRv.apply {
            addItemDecoration(hItemDecoration)
            adapter = hTaskAdapter
        }
    }
}