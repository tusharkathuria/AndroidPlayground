package com.tusharkathuria.androidplayground.coroutines.fetch_image_using_open_stream

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tusharkathuria.androidplayground.coroutines.ui.theme.AndroidPlaygroundTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import java.net.URL

class MainActivity : ComponentActivity() {
    private val IMAGE_URL = "https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png"
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var bitmap by remember {
                mutableStateOf<Bitmap?>(null)
            }
            LaunchedEffect(key1 = Unit) {
                val originalDeferred = coroutineScope.async(Dispatchers.IO) {
                    getOriginalBitmap()
                }
                val localBitmap = originalDeferred.await()
                val filteredBitmapDeferred = coroutineScope.async(Dispatchers.Default) {
                    Filter.apply(localBitmap)
                }
                bitmap = filteredBitmapDeferred.await()
            }
            AndroidPlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    bitmap?.let {
                        Image(it.asImageBitmap(), contentDescription = "")
                    }
                }
            }
        }
    }

    private fun getOriginalBitmap() =
        URL(IMAGE_URL).openStream().use {
            BitmapFactory.decodeStream(it)
        }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidPlaygroundTheme {
        Greeting("Android")
    }
}