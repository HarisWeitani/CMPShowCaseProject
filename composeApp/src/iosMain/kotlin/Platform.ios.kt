import platform.Foundation.NSBundle
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val baseUrl: String = NSBundle.mainBundle.objectForInfoDictionaryKey("BASE_URL") as String
}

actual fun getPlatform(): Platform = IOSPlatform()