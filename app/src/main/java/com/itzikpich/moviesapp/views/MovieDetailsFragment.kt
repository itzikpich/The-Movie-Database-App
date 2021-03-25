package com.itzikpich.moviesapp.views

import android.content.Context
import android.os.Bundle
import android.view.View
import com.itzikpich.moviesapp.R
import com.itzikpich.moviesapp.view_models.MovieDetailsViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import javax.inject.Inject

class MovieDetailsFragment: BaseFragment(R.layout.fragment_movie_details) {

    @Inject
    lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
//      inject Dagger in the fragments's onAttach() method, no matter before or after super
        mainActivity.activityComponent.inject(this)
//      and onCreateView too
//      (shared instance with the Activity and the other Fragments)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (this.arguments?.get("movieTitle") as? String)?.let {
            mainActivity.supportActionBar?.title = it
        }
        (this.arguments?.get("movieId") as? Int)?.let {

            // region movieDetails
            movieDetailsViewModel.getMovieDetailsFromRemote(it)
            movieDetailsViewModel.getMovieFromLocal(it)
            movieDetailsViewModel.movieDetailsLiveData.observe(viewLifecycleOwner, { movieDetails ->
                movieDetails?.let { details ->
                    view.movie_details_major.setData(details)
                    view.movie_details_overview.text = details.overview
                }
            })
            //endregion

            // region creditsItem
            movieDetailsViewModel.getCreditsFromRemote(it)
            movieDetailsViewModel.getCreditsFromLocal(it)
            movieDetailsViewModel.creditsItemLiveData.observe(viewLifecycleOwner, { credits ->
                credits?.let { item ->
                    view.recyclerview_movie_details_cast.setCreditsItem(item)
                }
            })
            //endregion

            // region videoItem
            movieDetailsViewModel.getVideoItemFromRemote(it)
            movieDetailsViewModel.getVideosFromLocal(it)
            movieDetailsViewModel.videosItemLiveData.observe(viewLifecycleOwner, { videoItem ->
                videoItem?.let { item ->
                    view.recyclerview_movie_details_videos.setVideosItem(item)
                }
            })
            //endregion

        }
    }

}