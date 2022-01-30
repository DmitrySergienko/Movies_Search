package ru.ds.movies_search.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ds.movies_search.model.Film
import ru.ds.movies_search.model.IRepository
import ru.ds.movies_search.model.Repository
import java.lang.Error
import java.lang.Thread.sleep


//берет данные из сети (базы данных) и возврвщает LiveData
class MainViewModel(private val liveDataToObserve : MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {

    private val repository: IRepository = Repository()
    //возвращает LiveData
    fun getLiveData() = liveDataToObserve
        //что бы добраться до метода getMovieFromServer реализуем через интерфасе repository
        fun getMovieFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)

        fun getMovieFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)

        fun getMovieFromRemoteSource() = getDataFromLocalSource(isRussian = true)


//метод для запроса из репозитория информации
    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(
                AppState.Success(
                    if (isRussian) repository.getMovieFromLocalBdRus()
                    else repository.getMovieFromLocalBdWorld()
                )

            )
        }.start()
    }
}