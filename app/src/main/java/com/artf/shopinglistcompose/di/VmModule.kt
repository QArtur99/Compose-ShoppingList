package com.artf.shopinglistcompose.di

import android.content.Context
import com.artf.shopinglistcompose.ui.data.NewProductViewModel
import com.artf.shopinglistcompose.ui.data.NewListViewModel
import com.artf.data.database.ShoppingListDatabase
import com.artf.data.database.ShoppingListDatabaseDao
import com.artf.data.repository.ShoppingListRepository
import com.artf.data.repository.ShoppingListRepositoryImpl
import com.artf.shopinglistcompose.ui.data.ScreenBackStack
import com.artf.shopinglistcompose.ui.data.SharedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { SharedViewModel(get()) }
    viewModel { NewProductViewModel(get()) }
    viewModel { NewListViewModel(get()) }
}

val dataModule = module {
    single { createRepository(get(), get()) }
    single { createDataBase(get()) }
    single { createIoDispatcher() }
}

val uiDataModule = module {
    single { createBackStack() }
}

fun createBackStack() = ScreenBackStack()

fun createIoDispatcher() = Dispatchers.Default

fun createDataBase(context: Context): ShoppingListDatabaseDao {
    return ShoppingListDatabase.getInstance(context).shoppingListDatabaseDao
}

fun createRepository(
    shoppingListDatabase: ShoppingListDatabaseDao,
    ioDispatcher: CoroutineDispatcher
): ShoppingListRepository {
    return ShoppingListRepositoryImpl(shoppingListDatabase, ioDispatcher)
}