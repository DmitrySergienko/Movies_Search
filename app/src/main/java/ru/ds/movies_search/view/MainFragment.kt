package ru.ds.movies_search.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import ru.ds.movies_search.databinding.MainFragmentBinding
import ru.ds.movies_search.viewModel.AppState
import ru.ds.movies_search.viewModel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }
//эти танцы с бубнами _binding/binding для тогоч тобы в конечном счете binding точно не был null
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //обсервер следит за любыми изменениями в ViewModel, и если они есть то выполняет любую функцию ниже
        val observer = Observer<AppState>{ state->
                       renderData(state)
        }
        //подисываемся на LiveData и получение новых данных
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getMovies()

    }

    private fun renderData(state: AppState) {
        when(state){
            is AppState.Success ->{
                val moviesData = state.moviesData
                binding.loadingLayout.visibility = View.GONE
                binding.movieName.text = state.moviesData.name
                binding.movieType.text = state.moviesData.type
                binding.mainView.isVisible = true
            }
            is AppState.Loading ->{
                binding.loadingLayout.isVisible = true
                binding.mainView.isVisible = false
            }
            //при ошибке покажет ошибку и при нажатии перезапрашивает данные
            is AppState.Error -> {
                Snackbar
                    .make(binding.root,"Error",Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") {viewModel.getMovies()}
                    .show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}