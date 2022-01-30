package ru.ds.movies_search.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.ds.movies_search.R
import ru.ds.movies_search.databinding.FragmentDetailsBinding
import ru.ds.movies_search.model.Film


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//обращаемся к arguments, если они не Null вдергиваем из них Parceble типизированный by Film, ели не Null выполняем то, что в скобках расширения let
        arguments?.getParcelable<Film>(BUNDLE_EXTRA)?.let { film ->
            film.movie.also {
                binding.movieName.text = it.name
                binding.movieType.text = "${getString(R.string.movie_type)} ${it.name} ${it.type}"
            }
            //binding.temperatureValue.text = film.temperature.toString()
            //binding.feelsLikeValue.text = weather.feelsLike.toString()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {

        const val BUNDLE_EXTRA = "film"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
