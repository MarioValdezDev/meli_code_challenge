package mx.mariovaldez.melicodechallenge.app.domain.usecases

import mx.mariovaldez.melicodechallenge.BuildConfig
import timber.log.Timber

internal class InitializeTimber {

    operator fun invoke() {
        if (BuildConfig.DEBUG) {
            println("InitializeTimber")
            Timber.plant(Timber.DebugTree())
        }
    }
}
