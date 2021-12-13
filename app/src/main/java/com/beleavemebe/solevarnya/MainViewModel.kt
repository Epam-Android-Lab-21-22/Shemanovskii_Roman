package com.beleavemebe.solevarnya

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val mutableNetworkState = MutableLiveData<NetworkState>(NetworkState.Unknown)
    val networkState: LiveData<NetworkState> = mutableNetworkState

    sealed class NetworkState {
        object Unknown : NetworkState()
        object Connected : NetworkState()
        object Disconnected : NetworkState()
    }

    init {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkRequestCallback =
            object : ConnectivityManager.NetworkCallback()
        {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                mutableNetworkState.postValue(NetworkState.Connected)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                mutableNetworkState.postValue(NetworkState.Disconnected)
            }
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            cm.registerDefaultNetworkCallback(networkRequestCallback)
        } else {
            val networkRequest = NetworkRequest.Builder().build()
            cm.registerNetworkCallback(networkRequest, networkRequestCallback)
        }
    }

    private val context: Context
        get() = getApplication<Application>().applicationContext
}
