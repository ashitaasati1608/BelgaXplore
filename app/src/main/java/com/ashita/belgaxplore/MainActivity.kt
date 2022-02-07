package com.ashita.belgaxplore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ashita.belgaxplore.presentation.ui.Navigation
import com.ashita.belgaxplore.ui.theme.BelgaXploreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BelgaXploreTheme() {
                Navigation()
            }
        }
    }
}

