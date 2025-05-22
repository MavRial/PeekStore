package com.example.peekstore.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.peekstore.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peekstore.presentation.login.components.ZetaButtonBasic
import com.example.peekstore.presentation.login.components.ZetaImageLogo
import com.example.peekstore.presentation.login.components.ZetaOutlinedTextField
import com.example.peekstore.presentation.login.components.ZetaText
import com.example.peekstore.presentation.login.components.ZetaTextLink
import com.example.peekstore.presentation.login.state.AuthResult

import com.example.peekstore.presentation.login.viewmodel.LoginViewModel
import com.example.peekstore.ui.theme.ColorButton


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onLoginSuccess: (String) -> Unit // Navegar a Home con UID
) {
    val loginState by loginViewModel.loginState
    val context = LocalContext.current
    var isLoginMode by remember { mutableStateOf(true) }
    val authResult by loginViewModel.authResult

    LaunchedEffect(authResult) {
        when (authResult){
            is AuthResult.Success -> {
                val uid = (authResult as AuthResult.Success).uid
                onLoginSuccess(uid) // navegar a home
            }
            is AuthResult.Error -> {

            }
            else -> Unit
        }
    }




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {

        when(authResult){
            is AuthResult.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = ColorButton
                )
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(horizontal = 30.dp)
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    ZetaImageLogo(
                        image = painterResource(R.drawable.image_logo_rf),
                        width = 200.dp,
                        height = 200.dp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    ZetaText(
                        text = if(isLoginMode) "login" else "Registro",
                        fontSize = 36.sp,
                        maxLines = 1,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    if (!isLoginMode){
                        ZetaOutlinedTextField(
                            value = loginState.username,
                            onValueChange = { loginViewModel.onUsernameChanged(it) },
                            label = "Name",
                            keyboardType = KeyboardType.Text,
                            leadingIcon = painterResource(id = R.drawable.ic_person)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    ZetaOutlinedTextField(
                        value = loginState.email,
                        onValueChange = { loginViewModel.onEmailChanged(it) },
                        label = "Email",
                        keyboardType = KeyboardType.Email,
                        leadingIcon = painterResource(id = R.drawable.ic_email)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    ZetaOutlinedTextField(
                        value = loginState.password,
                        onValueChange = { loginViewModel.onPasswordChanged(it) },
                        label = "Paswword",
                        keyboardType = KeyboardType.Password,
                        leadingIcon = painterResource(id = R.drawable.ic_password),
                        isPassword = true,
                        isPasswordVisible = loginState.isPasswordVisible,
                        onVisibilityToggle = { loginViewModel.onPasswordVisibilityChanged() }
                    )
                    Spacer(modifier = Modifier.height(40.dp))

                    ZetaTextLink(
                        text = if (isLoginMode) "¿No tienes cuenta?" else "¿Ya tienes cuenta?",
                        linkColor = Color.Blue,
                        textLink = if (isLoginMode) "Regístrate aquí!!" else "Ingresa aquí",
                        onClick = { isLoginMode = !isLoginMode },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    if (isLoginMode){
                        ZetaTextLink(
                            text = "recupera tu contraseña", linkColor = Color.Blue,
                            textLink = "aqui",
                            onClick = { },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }


                ZetaButtonBasic(
                    onClick = {
                        if (isLoginMode){
                            loginViewModel.login(context, onLoginSuccess)
                        }else {
                            loginViewModel.register(context, onLoginSuccess )
                        }
                    },

                    backgroundColor = ColorButton,
                    text = if (isLoginMode) "Login" else "Register",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(30.dp)
                )
            }
            }
        }
}