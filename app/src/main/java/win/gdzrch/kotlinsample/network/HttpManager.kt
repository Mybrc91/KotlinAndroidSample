package win.gdzrch.kotlinsample.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


/**
 * Created by w on 2017/5/25.
 */
object HttpManager {

    private var mInstance: HttpManager? = null
    var service:SampleService

    fun getInstance(): HttpManager {
        if (mInstance == null) {
            synchronized(HttpManager::class.java) {
                if (mInstance == null)
                    mInstance = HttpManager
            }
        }
        return mInstance!!
    }


    init {
        val client = OkHttpClient.Builder()
                .addInterceptor(HttpHeaderInterceptor())
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        service = retrofit.create<SampleService>(SampleService::class.java)
    }
}