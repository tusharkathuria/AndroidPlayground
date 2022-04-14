package com.tusharkathuria.androidplayground.github_explore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.tusharkathuria.androidplayground.commonui.theme.AndroidPlaygroundTheme
import com.tusharkathuria.androidplayground.github_explore.data.local.AppDatabase
import com.tusharkathuria.androidplayground.github_explore.data.remote.GithubApiService
import com.tusharkathuria.androidplayground.github_explore.ui.Navigation
import com.tusharkathuria.androidplayground.github_explore.ui.RepoListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidPlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}