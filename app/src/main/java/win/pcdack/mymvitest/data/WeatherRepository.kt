package win.pcdack.mymvitest.data

import java.io.IOException

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.annotations.NonNull
import io.reactivex.exceptions.Exceptions
import io.reactivex.schedulers.Schedulers
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Created by pcdack on 17-12-30.
 *
 */

object WeatherRepository {
    private val URL = "http://www.dg121.com/index.php/portal/share/hour24"

    fun loadWeatherInfoJson(): Observable<String> {
        return Observable.create(ObservableOnSubscribe<String> { e ->
            val okHttpClient = OkHttpClient()
            val request = Request.Builder()
                    .url(URL)
                    .build()
            val call = okHttpClient.newCall(request)
            try {
                val response = call.execute()
                e.onNext(response.body()!!.string())
                e.onComplete()
            } catch (ex: IOException) {
                e.onError(ex)
            }
        }).subscribeOn(Schedulers.io())
    }
}
