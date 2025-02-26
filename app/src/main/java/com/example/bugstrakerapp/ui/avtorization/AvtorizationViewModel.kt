package com.example.bugstrakerapp.ui.avtorization

import com.example.bugstrakerapp.model.client.KaurcevClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AvtorizationViewModel(){
val _loading:MutableStateFlow<Avt> = MutableStateFlow(Avt.Erorr)
    val loading:StateFlow<Avt> = _loading

  suspend fun avtorization(login:String,password:String){

        val tokenCS = coroutineScope{
            async {
                val avtorization=KaurcevClient().getClient().avtorization(login=login,password=password)
                val rezult = if (avtorization.data != null){
                    avtorization.data.token
                } else{
                    "Ошибка авторизации"
                }
                return@async rezult
            }
        }
      val token =tokenCS.await()

    }

}
sealed interface Avt{
    data object Loading : Avt
    data object Avtorization : Avt
    data object Erorr : Avt
}


