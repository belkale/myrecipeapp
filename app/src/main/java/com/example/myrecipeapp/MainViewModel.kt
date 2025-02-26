package com.example.myrecipeapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var recipeState by mutableStateOf(RecipeState())
        private set

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                recipeState = recipeState.copy(list = response.categories, isLoading = false)
            } catch(e: Exception) {
                recipeState = recipeState.copy(error = "Error fetching categories. ${e.message}",
                    isLoading = false)
            }
        }
    }
    data class RecipeState(
        val isLoading: Boolean = false,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}