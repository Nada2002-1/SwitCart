package com.example.home.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.home.ui.favorites.FavouriteScreen
import com.example.home.ui.theme.Product

class ProductDetailScreen(private val product: Product) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        var quantity by remember { mutableStateOf(1) }
        val isFavorite = remember { mutableStateOf(false) }
        val navigator = LocalNavigator.currentOrThrow
        val favoriteItems = remember { mutableStateListOf<Product>() }

        Scaffold(
            modifier = Modifier.background(Color.White),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Product Details",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    },
                    actions = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Box {
                            Image(
                                painter = painterResource(id = product.imageResId),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(300.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop
                            )

                            IconButton(
                                onClick = {
                                    if (!isFavorite.value) {
                                        favoriteItems.add(product)
                                    } else {
                                        favoriteItems.remove(product)
                                    }
                                    isFavorite.value = !isFavorite.value
                                },
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .size(40.dp)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = if (isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Add to Favorites",
                                    tint = if (isFavorite.value) Color.Red else Color.Gray
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${product.price}$",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    color = Color.Gray
                                )
                            )

                            Text(
                                text = product.name,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // محاذاة "Description" إلى الجهة اليمنى
                Text(
                    text = "Description",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.End) // تحريك الوصف لليمين
                )

                Text(
                    text = product.description,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.End) // تحريك الوصف لليمين
                )

                Spacer(modifier = Modifier.height(16.dp))

                QuantityControl(
                    quantity = quantity,
                    onIncrease = { quantity++ },
                    onDecrease = { if (quantity > 1) quantity-- }
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Add to Cart",
                        color = Color.White,
                        style = TextStyle(fontSize = 18.sp)
                    )
                }


                Button(
                    onClick = { navigator.push(FavouriteScreen(favoriteItems)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "View Favorites",
                        color = Color.White,
                        style = TextStyle(fontSize = 18.sp)
                    )
                }
            }
        }
    }

    @Composable
    fun QuantityControl(quantity: Int, onIncrease: () -> Unit, onDecrease: () -> Unit) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(50),
                color = if (quantity > 1) Color.LightGray else Color.Gray,
                modifier = Modifier
                    .size(36.dp)
                    .clickable(onClick = { if (quantity > 1) onDecrease() })
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Decrease quantity",
                    tint = Color.White
                )
            }

            Text(
                text = quantity.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Surface(
                shape = RoundedCornerShape(50),
                color = Color.LightGray,
                modifier = Modifier
                    .size(36.dp)
                    .clickable(onClick = onIncrease)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase quantity",
                    tint = Color.White
                )
            }
        }
    }
}