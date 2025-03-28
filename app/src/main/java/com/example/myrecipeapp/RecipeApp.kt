package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun RecipeApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val recipeViewModel: MainViewModel = viewModel()
    val viewState = recipeViewModel.recipeState

    NavHost(navController, startDestination = Recipe) {
        composable<Recipe> {
            RecipeScreen(modifier, viewState) {
                navController.navigate(route = it)
            }
        }
        composable<Category> { backstackEntry ->
            val category = backstackEntry.toRoute<Category>()
            CategoryDetailsScreen(modifier, category = category)
        }
    }
}