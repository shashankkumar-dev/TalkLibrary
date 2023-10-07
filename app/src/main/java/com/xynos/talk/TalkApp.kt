package com.xynos.talk

import android.app.Application
import com.xynos.talk.di.AppComponent
import com.xynos.talk.di.DaggerAppComponent

class TalkApp: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}