package org.hariswei.cmpshowcaseproject

import androidx.compose.ui.window.ComposeUIViewController
import org.hariswei.cmpshowcaseproject.di.initKoin

fun MainViewController() =
    ComposeUIViewController(
        configure = {
            initKoin()
        }
    ){
        App()
    }