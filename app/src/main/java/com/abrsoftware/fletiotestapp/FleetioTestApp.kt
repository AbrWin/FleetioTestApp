package com.abrsoftware.fletiotestapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class FleetioTestApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeTimber()
    }

    private fun initializeTimber() {
        // Siempre usar DebugTree para ahora
        // TODO: Usar BuildConfig.DEBUG cuando esté disponible
        Timber.plant(Timber.DebugTree())
    }
}

/**
 * Logging tree para producción - no muestra logs de DEBUG
 */
private class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == android.util.Log.ERROR || priority == android.util.Log.WARN) {
            // En producción, podrías enviar logs críticos a Crashlytics o tu servicio de logging
            android.util.Log.println(priority, tag, message)
        }
    }
}