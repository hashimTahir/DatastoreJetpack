/*
 * Copyright (c) 2021/  4/ 13.  Created by Hashim Tahir
 */

package com.hashim.datastore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hashim.datastore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val hTaskViewModel: MainViewModel by viewModels()

    lateinit var hActivityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(hActivityMainBinding.root)

        hInitRecyclerView()
        hSetupListeners()


    }

    private fun hSetupListeners() {

    }

    private fun hInitRecyclerView() {

    }


}