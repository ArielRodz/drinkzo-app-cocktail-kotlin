package com.example.mybar.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybar.API.DrinksItem
import com.example.mybar.API.MarsApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class Status { LOADING, DONE, ERROR }




class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<Status>()

    // The external immutable LiveData for the request status
    val status: LiveData<Status>
        get() = _status



    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<List<DrinksItem>>()

    // The external immutable LiveData for the response String
    val response: LiveData<List<DrinksItem>>
        get() = _response

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )



    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    fun filterByIngredient(ingredient: String) {
        coroutineScope.launch {
            _status.value = Status.LOADING

            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = MarsApi.retrofitService.FilterByIngredient(ingredient)
            try {
                _status.value = Status.LOADING

                // Await the completion of our Retrofit request
                var response = getPropertiesDeferred.await()
                _status.value = Status.DONE
                _response.value = response.drinks as List<DrinksItem>?

            } catch (e: Exception) {
                _status.value = Status.ERROR

                Log.d("OverviewViewModel", e.message)
            }
        }
    }
    fun filterByGlass(glass: String) {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            _status.value = Status.LOADING

            var getPropertiesDeferred = MarsApi.retrofitService.FilterByGlass(glass)
            try {
                // Await the completion of our Retrofit request
                _status.value = Status.LOADING

                var response = getPropertiesDeferred.await()
                _status.value = Status.DONE
                _response.value = response.drinks as List<DrinksItem>?
            } catch (e: Exception) {
                _status.value = Status.ERROR

                Log.d("OverviewViewModel", e.message)
            }
        }
    }


    fun filterByCategory(glass: String) {
        coroutineScope.launch {
            _status.value = Status.LOADING
            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = MarsApi.retrofitService.FilterByCategory(glass)
            try {
                // Await the completion of our Retrofit request
                _status.value = Status.LOADING
                var response = getPropertiesDeferred.await()
                _status.value = Status.DONE
                _response.value = response.drinks as List<DrinksItem>?

            } catch (e: Exception) {

                _status.value = Status.ERROR

                Log.d("OverviewViewModel", e.message)
            }
        }
    }

    fun filterByAlcoholic(glass: String) {
        coroutineScope.launch {
            _status.value = Status.LOADING

            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = MarsApi.retrofitService.FilterByAlcoholic(glass)
            try {
                _status.value = Status.LOADING

                // Await the completion of our Retrofit request
                var response = getPropertiesDeferred.await()
                _status.value = Status.DONE

                _response.value = response.drinks as List<DrinksItem>?
            } catch (e: Exception) {
                _status.value = Status.ERROR

                Log.d("OverviewViewModel", e.message)
            }
        }
    }


        /**
         * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
         * Retrofit service to stop.
         */
        override fun onCleared() {
            super.onCleared()
            viewModelJob.cancel()
        }
}