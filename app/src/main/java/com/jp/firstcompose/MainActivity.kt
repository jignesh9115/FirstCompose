package com.jp.firstcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            /*  Column and LazyColumn  */
            //ShowData()

            /*  compose state hoisting  */
            StateHoisting()
        }
    }
}





