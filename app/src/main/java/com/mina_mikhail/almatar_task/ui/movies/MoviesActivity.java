package com.mina_mikhail.almatar_task.ui.movies;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.view.View;
import com.mina_mikhail.almatar_task.R;
import com.mina_mikhail.almatar_task.data.enums.NetworkState;
import com.mina_mikhail.almatar_task.data.model.Movie;
import com.mina_mikhail.almatar_task.databinding.ActivityMoviesBinding;
import com.mina_mikhail.almatar_task.ui.base.BaseActivity;
import com.mina_mikhail.almatar_task.ui.movie_details.MovieDetailsActivity;
import com.mina_mikhail.almatar_task.utils.CommonUtils;
import com.mina_mikhail.almatar_task.utils.Constants;
import com.mina_mikhail.raseedi_task.BR;
import java.util.ArrayList;
import java.util.List;

public class MoviesActivity
    extends BaseActivity<ActivityMoviesBinding, MoviesViewModel>
    implements MoviesAdapter.MoviesListener {

  public static void open(Activity activity) {
    Intent intent = new Intent(activity, MoviesActivity.class);
    activity.startActivity(intent);
  }

  private MoviesViewModel mViewModel;

  private MoviesAdapter moviesAdapter;
  private List<Movie> movies = new ArrayList<>();

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_movies;
  }

  @Override
  public MoviesViewModel getViewModel() {
    return mViewModel;
  }

  @Override
  protected void setUpViewModel() {
    mViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
    getViewDataBinding().setViewModel(getViewModel());
    initBaseObservables();
  }

  @Override
  protected void onStart() {
    super.onStart();

    moviesAdapter.registerListener(this);
  }

  @Override
  protected void onStop() {
    moviesAdapter.unRegisterListener();

    super.onStop();
  }

  @Override
  protected void setUpViews() {
    setupToolbar();

    initMoviesRecyclerView();

    getData();
  }

  private void setupToolbar() {
    setSupportActionBar(getViewDataBinding().includedToolbar.toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    getViewDataBinding().includedToolbar.toolbar
        .setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_white));
    getViewDataBinding().includedToolbar.toolbar.setNavigationOnClickListener(v -> onBackPressed());
    setTitle("");
    getViewDataBinding().includedToolbar.toolbarTitle
        .setText(getResources().getString(R.string.popular_movies));
  }

  private void initMoviesRecyclerView() {
    CommonUtils.configRecyclerView(getViewDataBinding().includedList.recyclerView, true);
    moviesAdapter = new MoviesAdapter(movies);
    getViewDataBinding().includedList.recyclerView.setAdapter(moviesAdapter);
    getViewDataBinding().includedList.reloadBtn.setOnClickListener(v -> getData());
  }

  private void getData() {
    showProgress();
    getViewModel().getMovies(Constants.SORT_BY);
  }

  @Override
  protected void setUpObservables() {
    getViewModel().getMoviesData().getNetworkState().observe(this, state -> {
      if (state != null) {
        if (state == NetworkState.LOADED) {
          if (!getViewModel().getMoviesData().getData().isEmpty()) {
            showMessage(getViewModel().getMoviesData().getMessage());
            moviesAdapter.replaceItems(getViewModel().getMoviesData().getData());
            showData();
          } else {
            showNoData();
          }
        } else if (state == NetworkState.FAILED) {
          showNoData();
        } else if (state == NetworkState.NO_INTERNET) {
          showNoInternet();
        }
      }
    });
  }

  @Override
  public void onMovieClicked(Movie movie) {
    MovieDetailsActivity.open(this, movie.getId());
  }

  private void showData() {
    getViewDataBinding().includedList.recyclerView.setVisibility(View.VISIBLE);
    getViewDataBinding().includedList.container.setVisibility(View.GONE);
    getViewDataBinding().includedList.progressBar.setVisibility(View.GONE);
  }

  private void showNoData() {
    getViewDataBinding().includedList.recyclerView.setVisibility(View.GONE);
    getViewDataBinding().includedList.progressBar.setVisibility(View.GONE);
    getViewDataBinding().includedList.emptyViewContainer.setVisibility(View.VISIBLE);
    getViewDataBinding().includedList.internetErrorViewContainer.setVisibility(View.GONE);
    getViewDataBinding().includedList.reloadBtn.setVisibility(View.GONE);
    getViewDataBinding().includedList.container.setVisibility(View.VISIBLE);
  }

  private void showProgress() {
    getViewDataBinding().includedList.recyclerView.setVisibility(View.GONE);
    getViewDataBinding().includedList.progressBar.setVisibility(View.VISIBLE);
    getViewDataBinding().includedList.emptyViewContainer.setVisibility(View.GONE);
    getViewDataBinding().includedList.internetErrorViewContainer.setVisibility(View.GONE);
    getViewDataBinding().includedList.reloadBtn.setVisibility(View.GONE);
    getViewDataBinding().includedList.container.setVisibility(View.VISIBLE);
  }

  private void showNoInternet() {
    getViewDataBinding().includedList.recyclerView.setVisibility(View.GONE);
    getViewDataBinding().includedList.progressBar.setVisibility(View.GONE);
    getViewDataBinding().includedList.emptyViewContainer.setVisibility(View.GONE);
    getViewDataBinding().includedList.internetErrorViewContainer.setVisibility(View.VISIBLE);
    getViewDataBinding().includedList.reloadBtn.setVisibility(View.VISIBLE);
    getViewDataBinding().includedList.container.setVisibility(View.VISIBLE);
  }
}