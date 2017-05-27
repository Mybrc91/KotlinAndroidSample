package win.gdzrch.kotlinsample.network

import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okio.GzipSink
import okio.Okio
import okio.BufferedSink
import okhttp3.RequestBody
import java.io.IOException


/**
 * Created by w on 2017/5/25.
 */
class HttpHeaderInterceptor:Interceptor{
    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()

        val headerBuilder = request.newBuilder()
                .header("Content-Encoding", "gzip")
                .header("Accept", "application/vnd.github.v3+json")
                .header("Authorization", "token f1ad78deb7c1aaa21f3db887bb8fad5b3d9de558")
                .method(request.method(), gzip(request.body()))
         val addHeaderRequest =  headerBuilder.build()

        val response = chain.proceed(addHeaderRequest)


        return response
    }

    private fun gzip(body: RequestBody?): RequestBody? {
        if (body == null){
            return null
        }
        return object : RequestBody() {
            override fun contentType(): MediaType? {
                return body.contentType()
            }

            override fun contentLength(): Long {
                return -1 // We don't know the compressed length in advance!
            }

            @Throws(IOException::class)
            override fun writeTo(sink: BufferedSink) {
                val gzipSink = Okio.buffer(GzipSink(sink))
                body.writeTo(gzipSink)
                gzipSink.close()
            }
        }
    }

}