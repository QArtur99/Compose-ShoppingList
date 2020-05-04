package com.artf.shoppinglistcompose.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.artf.shoppinglistcompose.MainCoroutineRule
import com.artf.shoppinglistcompose.data.database.ShoppingListDatabaseDao
import com.artf.shoppinglistcompose.data.database.ShoppingListSourceImpl
import com.artf.shoppinglistcompose.data.database.model.Product
import com.artf.shoppinglistcompose.data.database.model.ShoppingList
import com.artf.shoppinglistcompose.data.status.ResultStatus
import com.artf.shoppinglistcompose.util.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.isA
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class ShoppingListSourceImplTest {

    private val shoppingListDao = Mockito.mock(ShoppingListDatabaseDao::class.java)
    private val source = ShoppingListSourceImpl(shoppingListDao, Dispatchers.Unconfined)

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
        source.insertShoppingList(shoppingList)
        verify(shoppingListDao).insertShoppingList(shoppingList)
    }

    @Test
    fun updateShoppingList() = runBlockingTest {
        val shoppingList = ShoppingList()
        source.updateShoppingList(shoppingList)
        verify(shoppingListDao).updateShoppingList(shoppingList)
    }

    @Test
    fun insertProduct() = runBlockingTest {
        val product = Product()
        source.insertProduct(product)
        verify(shoppingListDao).insertProduct(product)
    }

    @Test
    fun updateProduct() = runBlockingTest {
        val product = Product()
        source.updateProduct(product)
        verify(shoppingListDao).updateProduct(product)
    }

    @Test
    fun deleteProduct() = runBlockingTest {
        val product = Product()
        source.deleteProduct(product)
        verify(shoppingListDao).deleteProduct(product)
    }

    @Test
    fun getCurrentShoppingList() {
        val shoppingLists = emptyList<ShoppingList>()
        val liveData = MutableLiveData<List<ShoppingList>>().apply { value = shoppingLists }
        `when`(shoppingListDao.getCurrentShoppingList()).thenReturn(liveData)
        val observer = mock<Observer<ResultStatus<List<ShoppingList>>>>()
        source.getCurrentShoppingList().observeForever(observer)
        verify(observer).onChanged(isA(ResultStatus.Error::class.java))
        verify(shoppingListDao).getCurrentShoppingList()
    }

    @Test
    fun getArchivedShoppingList() {
        val shoppingLists = emptyList<ShoppingList>()
        val liveData = MutableLiveData<List<ShoppingList>>().apply { value = shoppingLists }
        `when`(shoppingListDao.getArchivedShoppingList()).thenReturn(liveData)
        val observer = mock<Observer<ResultStatus<List<ShoppingList>>>>()
        source.getArchivedShoppingList().observeForever(observer)
        verify(observer).onChanged(isA(ResultStatus.Error::class.java))
        verify(shoppingListDao).getArchivedShoppingList()
    }

    @Test
    fun getProductList() {
        val listId = 0L
        val product = emptyList<Product>()
        val liveData = MutableLiveData<List<Product>>().apply { value = product }
        `when`(shoppingListDao.getProductList(listId)).thenReturn(liveData)
        val observer = mock<Observer<ResultStatus<List<Product>>>>()
        source.getProductList(listId).observeForever(observer)
        verify(observer).onChanged(isA(ResultStatus.Error::class.java))
        verify(shoppingListDao).getProductList(listId)
    }
}