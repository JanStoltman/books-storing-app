package com.yggdralisk.booksstorage

import android.app.Application
import com.evernote.android.state.StateSaver
import io.realm.Realm
import timber.log.Timber

@Suppress("unused")
class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Timber.plant(Timber.DebugTree())
        StateSaver.setEnabledForAllActivitiesAndSupportFragments(this, true);
    }
}