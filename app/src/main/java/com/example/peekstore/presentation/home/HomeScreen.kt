package com.example.peekstore.presentation.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.peekstore.presentation.home.component.LogoutButtom
import com.example.peekstore.presentation.home.component.ProductList
import com.example.peekstore.presentation.home.component.ProductSeachField
import com.example.peekstore.presentation.home.state.HomeState
import com.example.peekstore.presentation.home.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    uid: String,
    homeViewModel: HomeViewModel,
    onLogout: () -> Unit,
    ) {
    val context = LocalContext.current
    val state = homeViewModel.state.value
    val searchQuery = homeViewModel.searchQuery.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        when (state){
            is HomeState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is HomeState.Success -> {
                val products = state.products.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }
                Column {
                    ProductSeachField(
                        searchQuery = searchQuery,
                        onSearchQueryChanged = homeViewModel::onSearchQueryChange
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ProductList(
                        products = products,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LogoutButtom(
                        onLogout = {
                            homeViewModel.logout(context){
                                onLogout()
                            }
                        }
                    )
                }
            }
            is HomeState.Error -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.message,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {homeViewModel.fetchProducts() } ) {
                        Text("Reintentar")
                    }
                }
            }
        }
    }

}