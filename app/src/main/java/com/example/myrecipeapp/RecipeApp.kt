package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val recipeViewModel:MainViewModel = viewModel()
    val viewState = recipeViewModel.recipeState

    NavHost(navController = navHostController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(modifier, viewState) {
                navHostController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navHostController.navigate(Screen.DetailScreen.route)
            }
        }
        composable(route = Screen.DetailScreen.route) {
            val category = navHostController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?:
                Category("","","","")
            CategoryDetailsScreen(category = category)
        }
    }
}