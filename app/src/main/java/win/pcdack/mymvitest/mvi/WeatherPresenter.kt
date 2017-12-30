package win.pcdack.mymvitest.mvi

import android.util.Log
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import win.pcdack.mymvitest.domain.GetWeatherData
import win.pcdack.mymvitest.domain.GetWeatherState

/**
 * Created by pcdack on 17-12-30.
 *
 */
class WeatherPresenter:MviBasePresenter<WeatherView,GetWeatherState>() {
    //绑定意图
    override fun bindIntents() {
        val getWeatherInfo=
                intent (WeatherView::getWeatherIntent)
                        .subscribeOn(Schedulers.io())
                        .switchMap{GetWeatherData.getWeather()}
                        .doOnNext { Log.d("状态",it.toString()) }
                        .observeOn(AndroidSchedulers.mainThread())
        subscribeViewState(getWeatherInfo,WeatherView::renderToUi)
    }
}