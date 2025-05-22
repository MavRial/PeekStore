package com.example.peekstore.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.example.peekstore.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
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
    navcontrol: NavController,
    onLoginSuccess: (String) -> Unit // Navegar a Home con UID
) {
    val loginState by loginViewModel.loginState
    val context = LocalContext.current

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
                text = "Login",
                fontSize = 36.sp,
                maxLines = 1,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(30.dp))
            ZetaOutlinedTextField(
                value = loginState.username,
                onValueChange = { loginViewModel.onUsernameChanged(it) },
                label = "Name",
                keyboardType = KeyboardType.Text,
                leadingIcon = painterResource(id = R.drawable.ic_person)
            )
            Spacer(modifier = Modifier.height(20.dp))
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
                text = "¿No tienes cuenta?", linkColor = Color.Blue,
                textLink = "Registrate aqui!!",
                onClick = { },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            ZetaTextLink(
                text = "recupera tu contraseña", linkColor = Color.Blue,
                textLink = "aqui",
                onClick = { },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
        ZetaButtonBasic(
            onClick = { loginViewModel.login(context,onLoginSuccess) },
            backgroundColor = ColorButton,
            text = "Login", color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(30.dp)
        )
    }
}