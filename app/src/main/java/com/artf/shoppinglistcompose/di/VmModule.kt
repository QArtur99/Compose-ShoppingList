package com.artf.shoppinglistcompose.di

import android.content.Context
import com.artf.data.database.ShoppingListDatabase
import com.artf.data.database.ShoppingListDatabaseDao
import com.artf.data.database.ShoppingListSource
import com.artf.data.database.ShoppingListSourceImpl
import com.artf.data.repository.ShoppingListRepository
import com.artf.data.repository.ShoppingListRepositoryImpl
import com.artf.shoppinglistcompose.ui.data.ScreenBackStackImpl
import com.artf.shoppinglistcompose.ui.data.SharedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { SharedViewModel(get(), get()) }
}

val dataModule = module {
    single { createShoppingListSource(get(), get()) }
    single { createRepository(get(), get()) }
    single { createDatabase(get()) }
    single { createIoDispatcher() }
}

val uiDataModule = module {
    single { createBackStack() }
}

fun createBackStack() = ScreenBackStackImpl()

fun createIoDispatcher() = Dispatchers.Default

fun createDatabase(context: Context): ShoppingListDatabaseDao {
    return ShoppingListDatabase.getInstance(context).shoppingListDatabaseDao
}

fun createRepository(
    shoppingListDatabase: ShoppingListSource,
    ioDispatcher: CoroutineDispatcher
): ShoppingListRepository {
    return ShoppingListRepositoryImpl(shoppingListDatabase, ioDispatcher)
}

fun createShoppingListSource(
    shoppingListDatabase: ShoppingListDatabaseDao,
    ioDispatcher: CoroutineDispatcher
): ShoppingListSource {
    return ShoppingListSourceImpl(shoppingListDatabase, ioDispatcher)
}