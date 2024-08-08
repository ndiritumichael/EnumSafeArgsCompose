package com.dev.enumsafeargscompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


@Serializable
data object HomeDestination


@Serializable
data class DetailDestination(val levels: Levels)




enum class Levels{
    LEVEL_1,
    LEVEL_2,
    LEVEL_3,
    LEVEL_4,
    LEVEL_5,
}
@Composable
fun AppNavigation(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = HomeDestination){

        composable<HomeDestination> {
            HomeScreen{ level ->
                navHostController.navigate(DetailDestination(level))
            }
        }

        composable<DetailDestination>{ backStackEntry ->

            val detailScreen :DetailDestination  = backStackEntry.toRoute()

            DetailScreen(levels = detailScreen.levels){
                navHostController.navigateUp()
            }

        }
    }


}


@Composable
fun HomeScreen(onclick : (Levels) ->Unit){
  Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
      Levels.entries.forEach { level ->

          Button(onClick = {
              onclick(level)
          }) {
              Text(text = "Go to ${level.name}")
          }
      }

    }
}


@Composable
fun DetailScreen(levels: Levels,goBack : () ->Unit){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "You clicked ${levels.name}")

            Button(onClick = goBack) {

                Text(text = "Go Back")

            }
        }
    }
}

