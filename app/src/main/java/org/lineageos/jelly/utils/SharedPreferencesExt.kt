/*
 * SPDX-FileCopyrightText: 2023 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.jelly.utils

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import org.lineageos.jelly.R
import org.lineageos.jelly.suggestions.SuggestionProvider

class SharedPreferencesExt(context: Context) {
    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private val defaultSearchEngine: String
    val defaultHomePage: String
    private val defaultSuggestionProvider: String

    init {
        context.resources.let {
            defaultSearchEngine = it.getString(R.string.default_search_engine)
            defaultHomePage = it.getString(R.string.default_home_page)
            defaultSuggestionProvider = it.getString(R.string.default_suggestion_provider)
        }
    }

    val adBlocker: Boolean
        get() = sharedPreferences.getBoolean(KEY_ADBLOCKER, true)

    val urlBarSearch: Boolean
        get() = sharedPreferences.getBoolean(KEY_URLBAR, true)

    val searchEngine: String
        get() = sharedPreferences.getString(
            SEARCH_ENGINE_KEY, defaultSearchEngine
        ) ?: defaultSearchEngine

    var homePage: String
        get() = sharedPreferences.getString(HOME_PAGE_KEY, defaultHomePage) ?: defaultHomePage
        set(value) = sharedPreferences.edit { putString(HOME_PAGE_KEY, value) }

    val advancedShareEnabled: Boolean
        get() = sharedPreferences.getBoolean(
            ADVANCED_SHARE_ENABLED_KEY, ADVANCED_SHARE_ENABLED_DEFAULT
        )

    val lookLockEnabled: Boolean
        get() = sharedPreferences.getBoolean(LOOK_LOCK_ENABLED_KEY, LOOK_LOCK_ENABLED_DEFAULT)

    val javascriptEnabled: Boolean
        get() = sharedPreferences.getBoolean(JAVASCRIPT_ENABLED_KEY, JAVASCRIPT_ENABLED_DEFAULT)

    val locationEnabled: Boolean
        get() = sharedPreferences.getBoolean(LOCATION_ENABLED_KEY, LOCATION_ENABLED_DEFAULT)

    val cookiesEnabled: Boolean
        get() = sharedPreferences.getBoolean(COOKIES_ENABLED_KEY, COOKIES_ENABLED_DEFAULT)

    val incognitoPolicy: Int
        get() = Integer.parseInt(sharedPreferences.getString(KEY_INCOGNITO_POLICY, "0")!!)

    val dpToolbar: Int
        get() = sharedPreferences.getInt(DP_URLBAR_KEY, 50)

    val webForceDark: Boolean
        get() = sharedPreferences.getBoolean(FORCE_DARK_KEY, false)

    val randomUserAgent: Boolean
        get() = sharedPreferences.getBoolean(RANDOM_USERAGENT, false)

    val doNotTrackEnabled: Boolean
        get() = sharedPreferences.getBoolean(DO_NOT_TRACK_ENABLED_KEY, DO_NOT_TRACK_ENABLED_DEFAULT)

    val saveFormData: Boolean
        get() = sharedPreferences.getBoolean(KEY_SAVE_FORM_DATA, true)

    val suggestionProvider: SuggestionProvider
        get() = runCatching {
            SuggestionProvider.valueOf(
                sharedPreferences.getString(
                    SUGGESTION_PROVIDER_KEY, defaultSuggestionProvider
                ) ?: defaultSuggestionProvider
            )
        }.getOrDefault(SuggestionProvider.NONE)

    val reachModeEnabled: Boolean
        get() = sharedPreferences.getBoolean(REACH_MODE_ENABLED_KEY, REACH_MODE_ENABLED_DEFAULT)

    companion object {
        private const val SEARCH_ENGINE_KEY = "key_search_engine"

        private const val HOME_PAGE_KEY = "key_home_page"

        private const val ADVANCED_SHARE_ENABLED_KEY = "key_advanced_share"
        private const val ADVANCED_SHARE_ENABLED_DEFAULT = false

        private const val LOOK_LOCK_ENABLED_KEY = "key_looklock"
        private const val LOOK_LOCK_ENABLED_DEFAULT = false

        private const val JAVASCRIPT_ENABLED_KEY = "key_javascript"
        private const val JAVASCRIPT_ENABLED_DEFAULT = true

        private const val LOCATION_ENABLED_KEY = "key_location"
        private const val LOCATION_ENABLED_DEFAULT = true

        private const val COOKIES_ENABLED_KEY = "key_cookie"
        private const val COOKIES_ENABLED_DEFAULT = true

        private const val DO_NOT_TRACK_ENABLED_KEY = "key_do_not_track"
        private const val DO_NOT_TRACK_ENABLED_DEFAULT = false

        private const val SUGGESTION_PROVIDER_KEY = "key_suggestion_provider"

        private const val REACH_MODE_ENABLED_KEY = "key_reach_mode"
        private const val REACH_MODE_ENABLED_DEFAULT = false

        private const val KEY_INCOGNITO_POLICY = "key_incognito_policy"
        private const val KEY_SAVE_FORM_DATA = "key_save_form_data"
        private const val KEY_ADBLOCKER = "key_adblocker"
        private const val KEY_URLBAR = "key_urlbar"
        private const val DP_URLBAR_KEY = "key_dp_toolbar"
        private const val FORCE_DARK_KEY = "key_force_dark"
        private const val RANDOM_USERAGENT = "key_random_useragent"
    }
}
