package win.pcdack.mymvitest.domain

/**
 * Created by pcdack on 17-12-30.
 *
 */
sealed class GetWeatherState {
    object LoadingState:GetWeatherState()
    data class DataState(val weatherData:String):GetWeatherState()
    data class ErrorState(val error:Throwable):GetWeatherState()
}