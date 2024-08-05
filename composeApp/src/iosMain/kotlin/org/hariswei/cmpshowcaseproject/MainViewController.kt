package org.hariswei.cmpshowcaseproject

import androidx.compose.ui.window.ComposeUIViewController
import org.hariswei.cmpshowcaseproject.di.initKoin
import org.hariswei.cmpshowcaseproject.ui.app.App

fun MainViewController() =
    ComposeUIViewController(
        configure = {
            initKoin()
        }
    ){
        App()
    }