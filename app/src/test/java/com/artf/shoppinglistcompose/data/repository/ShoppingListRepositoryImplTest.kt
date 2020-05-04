package com.artf.shoppinglistcompose.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.artf.shoppinglistcompose.data.database.ShoppingListSource
import com.artf.shoppinglistcompose.data.database.model.Product
import com.artf.shoppinglistcompose.data.database.model.ShoppingList
import com.artf.shoppinglistcompose.data.status.ResultStatus
import com.artf.shoppinglistcompose.MainCoroutineRule
import com.artf.shoppinglistcompose.util.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class ShoppingListRepositoryImplTest {

    private val shoppingListDao = Mockito.mock(ShoppingListSource::class.java)
    private val repo = ShoppingListRepositoryImpl(shoppingListDao, Dispatchers.Unconfined)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    @ExperimentalCoroutinesApi
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun insertShoppingList() = runBlockingTest {
        val shoppingList = ShoppingList()
        repo.insertShoppingList(shoppingList)
        verify(shoppingListDao).insertShoppingList(shoppingList)
    }

    @Test
    fun updateShoppingList() = runBlockingTest {
        val shoppingList = ShoppingList()
        repo.updateShoppingList(shoppingList)
        verify(shoppingListDao).updateShoppingList(shoppingList)
    }

    @Test
    fun insertProduct() = runBlockingTest {
        val product = Product()
        repo.insertProduct(product)
        verify(shoppingListDao).insertProduct(product)
    }

    @Test
    fun updateProduct() = runBlockingTest {
        val product = Product()
        repo.updateProduct(product)
        verify(shoppingListDao).updateProduct(product)
    }

    @Test
    fun deleteProduct() = runBlockingTest {
        val product = Product()
        repo.deleteProduct(product)
        verify(shoppingListDao).deleteProduct(product)
    }

    @Test
    fun getCurrentShoppingList() {
        val shoppingLists = emptyList<ShoppingList>()
        val result = ResultStatus.Success(shoppingLists)
        val liveData = MutableLiveData<ResultStatus<List<ShoppingList>>>().apply { value = result }
        `when`(shoppingListDao.getCurrentShoppingList()).thenReturn(liveData)
        val observer = mock<Observer<ResultStatus<List<ShoppingList>>>>()
        repo.getCurrentShoppingList().observeForever(observer)
        verify(observer).onChanged(result)
        verify(shoppingListDao).getCurrentShoppingList()
    }

    @Test
    fun getArchivedShoppingList() {
        val shoppingLists = emptyList<ShoppingList>()
        val result = ResultStatus.Success(shoppingLists)
        val liveData = MutableLiveData<ResultStatus<List<ShoppingList>>>().apply { value = result }
        `when`(shoppingListDao.getArchivedShoppingList()).thenReturn(liveData)
        val observer = mock<Observer<ResultStatus<List<ShoppingList>>>>()
        repo.getArchivedShoppingList().observeForever(observer)
        verify(observer).onChanged(result)
        verify(shoppingListDao).getArchivedShoppingList()
    }

    @Test
    fun getProductList() {
        val listId = 0L
        val product = emptyList<Product>()
        val result = ResultStatus.Success(product)
        val liveData = MutableLiveData<ResultStatus<List<Product>>>().apply { value = result }
        `when`(shoppingListDao.getProductList(listId)).thenReturn(liveData)
        val observer = mock<Observer<ResultStatus<List<Product>>>>()
        repo.getProductList(listId).observeForever(observer)
        verify(observer).onChanged(result)
        verify(shoppingListDao).getProductList(listId)
    }
}