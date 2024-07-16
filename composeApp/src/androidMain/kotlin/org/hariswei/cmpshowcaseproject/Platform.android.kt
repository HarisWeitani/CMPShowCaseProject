package org.hariswei.cmpshowcaseproject

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val baseUrl: String = BuildConfig.BASE_URL
    override val imageBaseUrl: String = BuildConfig.IMAGE_BASE_URL
}

actual fun getPlatform(): Platform = AndroidPlatform()