package mx.mariovaldez.melicodechallenge.data.remote.models

import mx.mariovaldez.melicodechallenge.data.remote.mappers.ClientExceptionMapper
import retrofit2.Call
import retrofit2.CallAdapter

internal class ResponseCallAdapter constructor(
    private val delegateAdapter: CallAdapter<Any, Call<*>>,
    private val clientExceptionMapper: ClientExceptionMapper
) : CallAdapter<Any, Call<*>> by delegateAdapter {

    override fun adapt(call: Call<Any>): Call<*> =
        delegateAdapter.adapt(ResponseCall(call, clientExceptionMapper))
}
