/*
 * Copyright (c) 2021/  4/ 13.  Created by Hashim Tahir
 */

package com.hashim.datastore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.hashim.datastore.databinding.ActivityMainBinding

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


    }

    private fun hSetupListeners() {
        hActivityMainBinding.hCompletedSw.setOnCheckedChangeListener { _, checked ->
            hTaskViewModel.hShowComletedTasks(checked)
        }

        hActivityMainBinding.hDeadLineChip.setOnCheckedChangeListener { _, checked ->
            hTaskViewModel.hEnableDisableDeadline(checked)
        }
        hActivityMainBinding.hSortChip.setOnCheckedChangeListener { _, checked ->
            hTaskViewModel.hSortByPririoty(checked)
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