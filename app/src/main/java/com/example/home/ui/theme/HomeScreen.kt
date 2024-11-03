package com.example.home.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.home.R
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.navigator.currentOrThrow

// تأكد من استيراد CartScreen
import com.example.home.ui.cart.CartScreen
import com.example.home.ui.detail.ProductDetailScreen
import com.example.home.ui.favorites.FavouriteScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {

                val primaryColor = Color(0xFF6200EE)
                val backgroundColor = Color.White
                val cardBackgroundColor = Color.LightGray
                val textColor = Color.Black
                val grayColor = Color.Gray

                val chaires = listOf(
                    Product(name = "Swoon Lounge", price = "1750", imageResId = R.drawable.chair2, description = "A sleek, minimalist chair with clean lines, a lightweight metal or plywood frame, and a soft upholstered seat in muted tones. Tapered legs add elegance, making it a versatile choice for any space."),
                    Product(name = "Wooden Chair", price = "2000", imageResId = R.drawable.chair3, description = "A sleek, minimalist chair with clean lines, a lightweight metal or plywood frame, and a soft upholstered seat in muted tones. Tapered legs add elegance, making it a versatile choice for any space."),
                    Product(name = "Mathis Chair", price = "1750", imageResId = R.drawable.chair4, description = "A sleek, minimalist chair with clean lines, a lightweight metal or plywood frame, and a soft upholstered seat in muted tones. Tapered legs add elegance, making it a versatile choice for any space.")
                )

                val navigator = LocalNavigator.currentOrThrow

                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            containerColor = backgroundColor,
                            contentColor = textColor,
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                IconButton(onClick = {

                                    navigator.push(CartScreen(chaires))
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.ShoppingCart,
                                        contentDescription = "Cart",
                                        modifier = Modifier.size(28.dp)
                                    )
                                }

                                IconButton(onClick = {

                                    navigator.push(FavouriteScreen(chaires))
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favorites",
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                                IconButton(onClick = {

                                    navigator.push(HomeScreen())
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = "Home",
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            }
                        }
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundColor)
                            .padding(paddingValues)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = "Explore What Your Home Needs",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(vertical = 16.dp)
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Text("Search for products") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            shape = RoundedCornerShape(8.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.chair1),
                            contentDescription = "Main Image",
                            modifier = Modifier
                                .size(width = 300.dp, height = 150.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "View All",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = textColor
                                ),
                                modifier = Modifier
                                    .clickable { /* TODO: View All Products */ }
                                    .padding(vertical = 8.dp)
                            )

                            Text(
                                text = "New Arrivals",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = textColor
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp)
                        ) {
                            items(chaires) { product ->
                                Card(
                                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
                                    modifier = Modifier
                                        .width(200.dp)
                                        .padding(vertical = 8.dp)
                                        .clickable { navigator.push(ProductDetailScreen(product)) }
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Spacer(modifier = Modifier.height(8.dp))

                                        Image(
                                            painter = painterResource(id = product.imageResId),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(150.dp)
                                                .clip(RoundedCornerShape(16.dp)),
                                            contentScale = ContentScale.Crop
                                        )

                                        Spacer(modifier = Modifier.height(8.dp))

                                        Text(
                                            text = product.name,
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 16.sp
                                            ),
                                            modifier = Modifier.padding(bottom = 4.dp)
                                        )

                                        Text(
                                            text = "${product.price}$",
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 18.sp,
                                                color = Color(0xFF333333)
                                            ),
                                            modifier = Modifier.padding(top = 4.dp)
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = product.description,
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                color = grayColor
                                            ),
                                            modifier = Modifier
                                                .padding(top = 4.dp)
                                                .fillMaxWidth(),
                                            textAlign = TextAlign.Center
                                        )

                                        Spacer(modifier = Modifier.height(16.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
