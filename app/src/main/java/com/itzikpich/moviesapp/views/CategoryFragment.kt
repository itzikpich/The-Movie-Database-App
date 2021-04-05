package com.itzikpich.moviesapp.views

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.itzikpich.moviesapp.NavGraphDirections
import com.itzikpich.moviesapp.R
import com.itzikpich.moviesapp.adapters.GridMoviesPagedAdapter
import com.itzikpich.moviesapp.view_models.CategoryViewModel
import com.itzikpich.moviesapp.view_models.HomeViewModel
import com.itzikpich.moviesapp.widgets.CategoryLayout
import kotlinx.android.synthetic.main.fragment_category.view.*
import javax.inject.Inject

class CategoryFragment: BaseFragment(R.layout.fragment_category) {

//    @Inject
//    lateinit var categoryViewModel: CategoryViewModel

    private val categoryViewModel by viewModels<CategoryViewModel> { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//      inject Dagger in the fragments's onAttach() method, no matter before or after super
        mainActivity.activityComponent.inject(this)
//      and onCreateView too
//      (shared instance with the Activity and the other Fragments)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (this.arguments?.get("categoryTitle") as? String)?.let {
//            mainActivity.supportActionBar?.title = it
            mainActivity.expandToolbar(false, view.recyclerview_fragment_category)
            mainActivity.setToolbarTitle(it)
            mainActivity.setToolbarColor(R.color.orange_400)
        }
        (this.arguments?.get("categoryId") as? Int)?.let {

            categoryViewModel.getLocalCategory(it)
            categoryViewModel.categoryMutableLiveData.observe(viewLifecycleOwner, { category ->
                val moviesAdapter = category.let {
                    GridMoviesPagedAdapter(it, object : CategoryLayout.MovieItemListener {

                        override fun onItemClicked(id: Int, title: String) {
                            findNavController().navigate(NavGraphDirections.actionToMovieDetailsFragment(id, title))
                        }

                    })
                }

                view.recyclerview_fragment_category.apply {
                    adapter = moviesAdapter
                    moviesAdapter.submitList(category?.movies)
                }
            })
        }
    }

}