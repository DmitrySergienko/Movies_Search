package ru.ds.movies_search.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import ru.ds.movies_search.R
import ru.ds.movies_search.databinding.FragmentMainBinding
import ru.ds.movies_search.model.Film
import ru.ds.movies_search.myFirstSnackBar
import ru.ds.movies_search.showSnackBar
import ru.ds.movies_search.view.details.DetailsFragment



import ru.ds.movies_search.viewModel.AppState
import ru.ds.movies_search.viewModel.MainViewModel
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    //отложенная или линивая инициализация ViewModel
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(film: Film) {
            val manager = activity?.supportFragmentManager
            if (manager != null) {
                val bundle = Bundle()
                bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA,film)
                manager.beginTransaction()
                    .replace(R.id.container, DetailsFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commit()
            }
        }
    })
    private var isDataSetRus: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            mainFragmentRecyclerView.adapter = adapter
            mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }
        }
        viewModel.apply {
            getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
            getMovieFromLocalSourceRus()
        }
    }

    private fun changeWeatherDataSet() {
        if (isDataSetRus) {
            viewModel.getMovieFromLocalSourceWorld()
            // binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        } else {
            viewModel.getMovieFromLocalSourceRus()
            // binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        }
        isDataSetRus = !isDataSetRus
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                with(binding) {
                    mainFragmentLoadingLayout.visibility = View.GONE
                    mainFragmentRecyclerView.isVisible = true
                }
                adapter.setWeather(appState.movieData)
            }
            is AppState.Loading -> {
                with(binding) {
                    mainFragmentLoadingLayout.visibility = View.VISIBLE
                    mainFragmentFAB.myFirstSnackBar(
                        actionText = getString(R.string.reload),
                        { viewModel.getMovieFromLocalSourceRus() },
                    )
                    mainFragmentRecyclerView.isVisible = false
                }
            }
            is AppState.Error -> {
                with(binding) {
                    mainFragmentLoadingLayout.visibility = View.GONE
                    mainFragmentFAB.showSnackBar(text = getString(R.string.error),
                        actionText = getString(R.string.reload),
                        { viewModel.getMovieFromLocalSourceRus() })
                }
            }
        }
    }
//выходим в интернет
  //  private fun connection(){
  //      //открываем соединение
  //      var urlConnection:HttpsURLConnection? = null
  //  val url = URL("ya.ru") // указать адресс URI
  //  urlConnection = url.openConnection() as HttpsURLConnection // устанавливаем соединение
  //  urlConnection.requestMethod = "GET" // устанавливаем тип запроса
  //  urlConnection.readTimeout = 10000 // устанвливаем timeout на чтение
  //  val out: OutputStream = BufferedInputStream(urlConnection.inputStream) // пишем
  //  writeStrim(out)
  //  val inf = BufferedReader(InputStreamReader(urlConnection.inputStream))
  //  urlConnection.disconnect() // закоываем соединенение


   // }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
        adapter.removeListener()
    }

    companion object {
        fun newInstance() =
            MainFragment()
    }
}

interface OnItemViewClickListener {
    fun onItemViewClick(film: Film)
}