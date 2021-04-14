/*
 * Copyright (c) 2021/  4/ 14.  Created by Hashim Tahir
 */

package com.hashim.datastore.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.hashim.datastore.models.SortOrder
import com.hashim.datastore.models.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException


class UserPreferencesRepository(private val hDataStore: DataStore<Preferences>) {

    /*Keys collection*/
    private object PreferencesKeys {
        val SORT_ORDER = stringPreferencesKey("sort_order")
        val SHOW_COMPLETED = booleanPreferencesKey("show_completed")
    }

    /*Get data from data store which returns a flow. map it defined type UserPrefrences*/
    val hUserPrefrencesFlow: Flow<UserPreferences> = hDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.d("Error reading preferences. $exception")
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val hSortOrder =
                SortOrder.valueOf(
                    preferences[PreferencesKeys.SORT_ORDER] ?: SortOrder.NONE.name
                )

            val hShowCompleted = preferences[PreferencesKeys.SHOW_COMPLETED] ?: false
            UserPreferences(hShowCompleted, hSortOrder)
        }

    suspend fun hEnableDisableSortByDeadLine(enable: Boolean) {
        /*get previously stored values, if exist from data strore.*/
        hDataStore.edit { preferences ->
            val hCurrentSortOrder = SortOrder.valueOf(
                preferences[PreferencesKeys.SORT_ORDER] ?: SortOrder.NONE.name
            )

            val hNewSortOrder =
                if (enable) {
                    if (hCurrentSortOrder == SortOrder.BY_PRIORITY) {
                        SortOrder.BY_DEADLINE_AND_PRIORITY
                    } else {
                        SortOrder.BY_DEADLINE
                    }
                } else {
                    if (hCurrentSortOrder == SortOrder.BY_DEADLINE_AND_PRIORITY) {
                        SortOrder.BY_PRIORITY
                    } else {
                        SortOrder.NONE
                    }
                }

            /*Save new values*/
            preferences[PreferencesKeys.SORT_ORDER] = hNewSortOrder.name
        }
    }

    /**
     * Enable / disable sort by priority.
     */
    suspend fun hEnableDisableSortByPririoty(enable: Boolean) {
        hDataStore.edit { preferences ->
            val hCurretSortOrder = SortOrder.valueOf(
                preferences[PreferencesKeys.SORT_ORDER] ?: SortOrder.NONE.name
            )

            val hNewSortOrder =
                if (enable) {
                    if (hCurretSortOrder == SortOrder.BY_DEADLINE) {
                        SortOrder.BY_DEADLINE_AND_PRIORITY
                    } else {
                        SortOrder.BY_PRIORITY
                    }
                } else {
                    if (hCurretSortOrder == SortOrder.BY_DEADLINE_AND_PRIORITY) {
                        SortOrder.BY_DEADLINE
                    } else {
                        SortOrder.NONE
                    }
                }

            preferences[PreferencesKeys.SORT_ORDER] = hNewSortOrder.name
        }
    }

    suspend fun hUpdateCompletedTasks(showCompleted: Boolean) {
        hDataStore.edit { preferences ->
            preferences[PreferencesKeys.SHOW_COMPLETED] = showCompleted
        }
    }
}