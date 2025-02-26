package com.example.myrecipeapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier = Modifier, mainViewModel: MainViewModel) {
    val recipeState = mainViewModel.recipeState
    Box(modifier = modifier.fillMaxSize()) {
        when {
            recipeState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            recipeState.error != null -> {
                Text("Error: ${recipeState.error}")
            }
            else -> {
                CategoryScreen(recipeState.list)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories.size) {
            index -> CategoryItem(categories[index])
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Image(painter = rememberAsyncImagePainter(
            category.strCategoryThumb,
            onError = {
                Log.e("CategoryItem", "Error loading image: ${it.result.throwable.message}")
            }
        ),
            category.strCategory,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
            )
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
    }
}