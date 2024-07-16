package di

import data.database.PeopleDatabase
import org.hariswei.cmpshowcaseproject.database.getPeopleDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val databaseModule: Module = module {
    single<PeopleDatabase> {
        getPeopleDatabase(get())
    }
}