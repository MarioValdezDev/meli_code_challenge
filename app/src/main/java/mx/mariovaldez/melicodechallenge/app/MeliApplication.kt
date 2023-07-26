package mx.mariovaldez.melicodechallenge.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import mx.mariovaldez.melicodechallenge.app.domain.usecases.InitializeTimber

@HiltAndroidApp
class MeliApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        InitializeTimber().invoke()
    }
}
