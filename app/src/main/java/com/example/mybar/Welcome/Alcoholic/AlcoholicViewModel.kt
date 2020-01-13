package com.example.mybar.Welcome.Alcoholic

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



class AlcoholicViewModel() : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<Status>()

    // The external immutable LiveData for the request status
    val status: LiveData<Status>
        get() = _status



    // Alcoholic
    private val _responseAlcoholic = MutableLiveData<List<AlcoholicItem>>()
    val responseAlcoholic: LiveData<List<AlcoholicItem>>
        get() = _responseAlcoholic

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getAlcoholicProperties()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    private fun getAlcoholicProperties() {
        coroutineScope.launch {

            _status.value = Status.LOADING

            // Get the Deferred object for our Retrofit request
            var getAlcoholicDeferred = MarsApi.retrofitService.getAlcoholic()
            try {

                _status.value = Status.LOADING

                // Await the completion of our Retrofit request
                var response = getAlcoholicDeferred.await()
                _status.value = Status.DONE

                _responseAlcoholic.value = response.drinks as List<AlcoholicItem>?
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