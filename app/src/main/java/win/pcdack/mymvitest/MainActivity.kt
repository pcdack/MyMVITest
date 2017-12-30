package win.pcdack.mymvitest

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_main.*
import win.pcdack.mymvitest.domain.GetWeatherState
import win.pcdack.mymvitest.mvi.WeatherPresenter
import win.pcdack.mymvitest.mvi.WeatherView

class MainActivity : MviActivity<WeatherView, WeatherPresenter>(), WeatherView {

    //将意图与按钮点击关联起来，只要按钮点击，那么就相当于发送这个意图
    override fun getWeatherIntent()=get_weather_info_btn.clicks()
    //创建一个Presenter
    override fun createPresenter()= WeatherPresenter()

    override fun renderToUi(state: GetWeatherState) =//根据不同的状态，来选择不同的函数，实现不同的展示
            when(state){
                is GetWeatherState.LoadingState->renderLoadingUi()    //加载状态的UI
                is GetWeatherState.DataState->renderDataUi(state)     //渲染数据时的UI
                is GetWeatherState.ErrorState->renderErrorUi(state)   //出错后的UI
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun renderErrorUi(state: GetWeatherState.ErrorState) {
        progress_bar.visible=false
        info.text=state.error.message
    }

    private fun renderDataUi(state: GetWeatherState.DataState) {
        progress_bar.visible=false
        info.text=state.weatherData

    }

    private fun renderLoadingUi() {
        progress_bar.visible=true
    }
}
