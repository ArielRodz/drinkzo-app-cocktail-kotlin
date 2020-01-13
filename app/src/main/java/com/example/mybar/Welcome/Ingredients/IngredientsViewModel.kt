package com.example.mybar.Welcome.Ingredients

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybar.API.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class Status { LOADING, DONE, ERROR }



class IngredientsViewModel() : ViewModel() {



    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<Status>()

    // The external immutable LiveData for the request status
    val status: LiveData<Status>
        get() = _status


      // Ingredient
    private val _responseIngredient = MutableLiveData<List<IngredientItem>>()
    val responseIngredient: LiveData<List<IngredientItem>>
        get() = _responseIngredient


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getIngredientProperties()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */


    private fun getIngredientProperties() {
        coroutineScope.launch {
            _status.value = Status.LOADING
            // Get the Deferred object for our Retrofit request
            var getIngredientDeferred = MarsApi.retrofitService.getIngredient()
            try {
                _status.value = Status.LOADING

                // Await the completion of our Retrofit request
                var response = getIngredientDeferred.await()
                _status.value = Status.DONE
                _responseIngredient.value = response.drinks as List<IngredientItem>?
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