package com.itzikpich.moviesapp.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.itzikpich.moviesapp.R
import com.itzikpich.moviesapp.adapters.CategoriesAdapter
import com.itzikpich.moviesapp.utilities.loadAsset
import com.itzikpich.moviesapp.models.CategoryItem
import com.itzikpich.moviesapp.utilities.observeOnce
import com.itzikpich.moviesapp.view_holders.CategoryViewHolder
import com.itzikpich.moviesapp.view_models.HomeViewModel
import com.itzikpich.moviesapp.widgets.CategoryLayout
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject

class HomeFragment: BaseFragment(R.layout.fragment_home) {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var gson: Gson

    override fun onAttach(context: Context) {
        super.onAttach(context)
//      inject Dagger in the fragments's onAttach() method, no matter before or after super
        mainActivity.activityComponent.inject(this)
//      and onCreateView too
//      (shared instance with the Activity and the other Fragments)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.setToolbarTitle("TMDB")
        mainActivity.setToolbarColor(R.color.orange_400)
        mainActivity.expandToolbar(false, view.recyclerview_fragment_home)
        loadAsset(view.context, "categories.json")?.let { json ->
            CategoryItem.parseJsonToList(gson, json)?.let { categories -> // todo - handle parse error
                Log.d(TAG, "loadAsset categories\n: $categories")
                homeViewModel.getMultipleMovies(categories)

                val categoryAdapter = object : CategoriesAdapter() {
                    override fun getLayoutId(position: Int, obj: CategoryItem): Int = R.layout.item_empty_layout
                    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder = CategoryViewHolder(view, object : CategoryLayout.CategoryLayoutInputListener {
                        override fun onMoreButtonClicked(id: Int, title: String) {
                            navigateToCategoryFragment(id, title)
                        }

                        override fun onItemClicked(id: Int, title: String) {
                            navigateToMovieDetailsFragment(id, title)
                        }

                        override fun onRefreshList(id: Int, page: Int) {
                            onRefresh(id, page)
                        }

                    })
                }
                view.recyclerview_fragment_home.adapter = categoryAdapter

                categories.forEach {
                    homeViewModel.getCategoryFromLocal(it.id)
                    homeViewModel.categoryLiveData.observe(viewLifecycleOwner, { category ->
                        category?.let {
                            categoryAdapter.updateItemsByCategory(it)
                        }
                    })
                }
            }
        }
    }

    fun navigateToCategoryFragment(id: Int, title: String) = findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCategoryFragment(id, title))
    fun navigateToMovieDetailsFragment(id: Int, title: String) = findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(id, title))
    fun onRefresh(id: Int, page: Int) {
        homeViewModel.getLocalCategory(id)
        homeViewModel.categoryByIdLiveData.observeOnce(viewLifecycleOwner, { categoty ->
            homeViewModel.getMovies(categoty, page)
            homeViewModel.categoryByIdLiveData.value = null
        })
    }

}