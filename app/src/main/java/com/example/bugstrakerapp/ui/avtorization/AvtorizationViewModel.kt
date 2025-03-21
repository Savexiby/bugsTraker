package com.example.bugstrakerapp.ui.avtorization

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bugstrakerapp.MainActivity
import com.example.bugstrakerapp.model.DataStoreManeger
import com.example.bugstrakerapp.model.client.KaurcevClient
import com.example.bugstrakerapp.model.data.LoginPasswordJson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AvtorizationViewModel : ViewModel() {
    private val _loading: MutableStateFlow<Avt> = MutableStateFlow(Avt.Loading)
    val loading: StateFlow<Avt> = _loading


    suspend fun avtorization(login: String, password: String, manegerDataStore: DataStoreManeger) {

        try {
            val avtorization = KaurcevClient().getClient()
                .avtorization(LoginPasswordJson(username = login, password = password))
            if (avtorization.data != null) {
                manegerDataStore.tokenSet(avtorization.data.token)
                Log.e("avt", "Токен ${avtorization.data.token}")
            } else {
                _loading.value = Avt.Erorr
                Log.e("avt", "Не пришел токен с сервера")
            }
        } catch (e: Exception) {
            _loading.value = Avt.Erorr
            Log.e("avt", "${e.message}")
        }

    }

}

sealed interface Avt {
    data object Loading : Avt
    data object Avtorization : Avt
    data object Erorr : Avt
}


