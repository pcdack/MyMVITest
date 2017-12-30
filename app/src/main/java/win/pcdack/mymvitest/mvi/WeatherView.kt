package win.pcdack.mymvitest.mvi

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable
import win.pcdack.mymvitest.domain.GetWeatherState

/**
 * Created by pcdack on 17-12-30.
 *
 */
interface WeatherView:MvpView {
    //请求天气意图
    fun getWeatherIntent():Observable<Unit>
    //将请求的结果渲染到UI上
    fun renderToUi(state:GetWeatherState)
}