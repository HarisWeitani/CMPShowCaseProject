import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getPeopleDatabase
import di.initKoin

fun MainViewController() =
    ComposeUIViewController(
        configure = {
            initKoin()
        }
    ){
        val dao = remember {
            getPeopleDatabase().peopleDao()
        }
        App(dao)
    }