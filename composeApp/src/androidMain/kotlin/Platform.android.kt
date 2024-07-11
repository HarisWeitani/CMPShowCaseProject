import android.os.Build
import org.hariswei.cmpshowcaseproject.BuildConfig

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val baseUrl: String = BuildConfig.BASE_URL
}

actual fun getPlatform(): Platform = AndroidPlatform()