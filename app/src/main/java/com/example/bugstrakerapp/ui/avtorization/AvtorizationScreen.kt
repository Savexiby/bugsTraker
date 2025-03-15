package com.example.bugstrakerapp.ui.avtorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.bugstrakerapp.R
import com.example.bugstrakerapp.model.DataStoreManeger
import com.example.bugstrakerapp.ui.SnackBarError
import com.example.bugstrakerapp.ui.theme.BugsTrakerAppTheme
import com.example.bugstrakerapp.ui.theme.padding
import com.example.bugstrakerapp.ui.theme.shape
import kotlinx.coroutines.async

@Preview(showBackground = true)
@Composable
private fun PrecScreen() {
    ScreenView(avtorizationClick = { login, password -> {} })
}

@Composable
fun ScreenAvtorization(manegerDataStore: DataStoreManeger) {
    val model: AvtorizationViewModel = viewModel()
    val state = model.loading.collectAsState()
    when (state.value) {
        is Avt.Loading -> ScreenView(avtorizationClick = { login, password ->
            model.avtorization(
                login = login,
                password = password,
                manegerDataStore = manegerDataStore
            )
        })

        is Avt.Erorr -> {
            SnackBarError(stringResource(R.string.erroravtorization))
        }

        is Avt.Avtorization -> {
            SnackBarError("Успешная авторизация")

        }
    }
}

@Composable
fun ScreenView(avtorizationClick: suspend (login: String, password: String) -> Unit) {
    val loginState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val corutineScope = rememberCoroutineScope()
    BugsTrakerAppTheme {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Вход в KAURCEV ID",
                    modifier = Modifier.padding(bottom = padding.padding24)
                )

                LoginField(title = "Почта или логин", loginState = loginState)
                PasswordField(title = "Пароль", passwordState = passwordState)
                Text(
                    text = "Забыли пароль?",
                    modifier = Modifier
                        .align(alignment = Alignment.End)
                        .padding(vertical = padding.padding10)
                )
                Button(
                    onClick = {
                        corutineScope.async {
                            avtorizationClick(
                                loginState.value,
                                passwordState.value
                            )
                        }
                    },
                    shape = RoundedCornerShape(shape.shape8),
                    modifier = Modifier.fillMaxWidth()
                ) { Text(text = "Войти") }
                TextLink()
                Text(
                    text = stringResource(R.string.learnmore),
                    modifier = Modifier.padding(top = padding.padding24),
                    color = Color.Gray
                )
            }
        }
    }

}

@Composable
private fun LoginField(title: String, loginState: MutableState<String>) {
    Column {
        Text(
            text = title,
            modifier = Modifier
                .padding(bottom = padding.padding10, top = padding.padding10)
        )
        OutlinedTextField(value = loginState.value,
            shape = RoundedCornerShape(shape.shape8),
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = title) },
            onValueChange = { newText -> loginState.value = newText }
        )
    }
}

@Composable
private fun PasswordField(title: String, passwordState: MutableState<String>) {

    val chekPasswordIcon = remember { mutableStateOf(true) }
    val icon = if (chekPasswordIcon.value == true) {
        R.drawable.closedeye
    } else {
        R.drawable.openeye
    }
    Column {
        Text(
            text = title,
            modifier = Modifier
                .padding(bottom = padding.padding10, top = padding.padding10)
        )
        OutlinedTextField(
            shape = RoundedCornerShape(shape.shape8),
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(title) },
            visualTransformation = if (!chekPasswordIcon.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = {
                    chekPasswordIcon.value = !chekPasswordIcon.value
                }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "Видимость пароля"
                    )
                }
            }
        )
    }
}

@Composable
private fun TextLink() {
    Text(textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = padding.padding10),
        text = buildAnnotatedString {
            append("Нажимая \"Войти\", вы принимаете ")
            withLink(
                LinkAnnotation.Url(
                    url = "https://docs.kaurcev.dev/termsofuse/",
                    styles = TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.primary))
                )
            ) {
                append(stringResource(R.string.useragreement))
            }
            append(" и ")
            withLink(
                LinkAnnotation.Url(
                    url = "https://docs.kaurcev.dev/privacy/",
                    styles = TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.primary))
                )
            ) {
                append(stringResource(R.string.privacypolicy))
            }
        })
}