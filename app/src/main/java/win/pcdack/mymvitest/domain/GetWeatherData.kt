package win.pcdack.mymvitest.domain

import io.reactivex.Observable
import win.pcdack.mymvitest.data.WeatherRepository

/**
 * Created by pcdack on 17-12-30.
 *
 */
object GetWeatherData {
    fun getWeather():Observable<GetWeatherState>{
        return WeatherRepository.loadWeatherInfoJson()
                .map<GetWeatherState>{GetWeatherState.DataState(it)}
                .startWith(GetWeatherState.LoadingState)
                .onErrorReturn { GetWeatherState.ErrorState(it) }
    }
}
