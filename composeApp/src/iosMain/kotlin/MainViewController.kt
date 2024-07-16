import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import org.hariswei.cmpshowcaseproject.database.getMovieDatabase
import org.hariswei.cmpshowcaseproject.di.initKoin

//fun MainViewController() =
//    ComposeUIViewController(
//        configure = {
//            initKoin()
//        }
//    ){
//        App()
//    }

fun MainViewController() =
    ComposeUIViewController{
        val dao = remember {
            getMovieDatabase().movieDao()
        }
        TestApp(dao)
    }
