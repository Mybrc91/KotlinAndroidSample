package win.gdzrch.kotlinsample.network

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by w on 2017/5/25.
 */
interface SampleService {
    @GET("/user")
    fun user() : Observable<Repo>

    @GET("/user/repos")
    fun listRepo() : Observable<List<Repo>>

    @GET("/search/repositories")
    fun search(@Query("q") q:String):Observable<SearchRepo>
}