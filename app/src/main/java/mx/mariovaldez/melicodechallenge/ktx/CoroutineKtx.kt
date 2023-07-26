package mx.mariovaldez.melicodechallenge.ktx

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun launchMain(callback: suspend () -> Unit) {
    CoroutineScope(Dispatchers.Main).launch { callback() }
}
