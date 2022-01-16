package ru.ds.movies_search.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ds.movies_search.model.IRepository
import ru.ds.movies_search.model.Repository
import java.lang.Thread.sleep


//берет данные из сети (базы данных) и возврвщает LiveData
class MainViewModel(private val liveDataToObserve : MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {
    //возвращает LiveData
    fun getLiveData() = liveDataToObserve
        //что бы добраться до метода getMovieFromServer реализуем через интерфасе repository
    private val repository: IRepository = Repository()



    fun getMovies(){

        liveDataToObserve.postValue(AppState.Loading)
        Thread{
            sleep(2000)
            liveDataToObserve.postValue(AppState.Success(repository.getMovieFromServer()))
        }.start()
    }
}