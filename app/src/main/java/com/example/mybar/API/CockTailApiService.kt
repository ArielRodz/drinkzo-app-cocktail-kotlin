package com.example.mybar.API

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.Path
import retrofit2.http.Query
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private var mClient: OkHttpClient? = null

/**
 * Don't forget to remove Interceptors (or change Logging Level to NONE)
 * in production! Otherwise people will be able to see your request and response on Log Cat.
 */
val client: OkHttpClient
    @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
    get() {
        if (mClient == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpBuilder = OkHttpClient.Builder()
            httpBuilder
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor)  /// show all JSON in logCat
            mClient = httpBuilder.build()

        }
        return mClient!!
    }



/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(client)
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface MarsApiService {
    /**
     * Returns a Coroutine [Deferred] [List] of [MarsProperty] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */

    @GET("lookup.php")
    fun findById(@Query("i")  id: String?):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<Response>

    @GET("filter.php")
    fun FilterByIngredient(@Query("i")  category: String?):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<Response>

    @GET("filter.php")
    fun FilterByGlass(@Query("g")  glass: String?):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<Response>

    @GET("filter.php")
    fun FilterByCategory(@Query("c")  category: String?):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<Response>

    @GET("filter.php")
    fun FilterByAlcoholic(@Query("a")  alcoholic: String?):
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<Response>



    @GET("list.php?a=list")
    fun getAlcoholic():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<ResponseAlcoholic>

    @GET("list.php?i=list")
    fun getIngredient():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<ResponseIngredient>

    @GET("list.php?g=list")
    fun getGlasses():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<ResponseGlass>

    @GET("list.php?c=list")
    fun getCategories():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<ResponseCategory>

    @GET("random.php")
    fun getRandom():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<Response>


}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MarsApi {
    val retrofitService : MarsApiService by lazy { retrofit.create(
        MarsApiService::class.java) }
}