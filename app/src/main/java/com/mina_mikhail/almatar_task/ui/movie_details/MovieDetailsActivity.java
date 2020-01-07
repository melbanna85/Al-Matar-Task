package com.mina_mikhail.almatar_task.ui.movie_details;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.view.View;
import com.mina_mikhail.almatar_task.R;
import com.mina_mikhail.almatar_task.data.enums.NetworkState;
import com.mina_mikhail.almatar_task.data.model.ProductionCompany;
import com.mina_mikhail.almatar_task.databinding.ActivityMovieDetailsBinding;
import com.mina_mikhail.almatar_task.ui.base.BaseActivity;
import com.mina_mikhail.almatar_task.ui.gallery_slider.GallerySliderActivity;
import com.mina_mikhail.almatar_task.utils.CommonUtils;
import com.mina_mikhail.almatar_task.utils.Constants;
import com.mina_mikhail.raseedi_task.BR;
import java.util.List;

public class MovieDetailsActivity
    extends BaseActivity<ActivityMovieDetailsBinding, MovieDetailsViewModel> {

  public static void open(Activity activity, int movieID) {
    Intent intent = new Intent(activity, MovieDetailsActivity.class);
    intent.putExtra(Constants.KEY_MOVIE_ID, movieID);
    activity.startActivity(intent);
  }

  private MovieDetailsViewModel mViewModel;

  private int movieID;

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_movie_details;
  }

  @Override
  public MovieDetailsViewModel getViewModel() {
    return mViewModel;
  }

  @Override
  protected void setUpViewModel() {
    mViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
    getViewDataBinding().setViewModel(getViewModel());
    initBaseObservables();
  }

  @Override
  protected void setUpViews() {
    setupToolbar();

    getIntentData();

    getData();

    getViewDataBinding().generalInclude.reloadBtn.setOnClickListener(v -> getData());
  }

  private void getIntentData() {
    movieID = getIntent().getIntExtra(Constants.KEY_MOVIE_ID, 0);
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
  }

  private void getData() {
    showProgress();
    getViewModel().getMovieDetails(movieID);
  }

  @Override
  protected void setUpObservables() {
    getViewModel().getMovieDetailsData().getNetworkState().observe(this, state -> {
      if (state != null) {
        if (state == NetworkState.LOADED) {
          if (getViewModel().getMovieDetailsData().getData() != null) {
            showMessage(getViewModel().getMovieDetailsData().getMessage());

            getViewDataBinding().setMovie(getViewModel().getMovieDetailsData().getData());
            getViewDataBinding().rateAmount
                .setRating(getViewModel().getMovieDetailsData().getData().getVote_average());

            if (getViewModel().getMovieDetailsData().getData().getProduction_companies() != null
                && !getViewModel().getMovieDetailsData().getData().getProduction_companies()
                .isEmpty()) {

              getViewDataBinding().productionCompaniesContainer.setVisibility(View.VISIBLE);

              setupCompaniesRecyclerView(
                  getViewModel().getMovieDetailsData().getData().getProduction_companies());
            }

            if (getViewModel().getMovieDetailsData().getData().getBudget() != 0) {
              getViewDataBinding().budgetContainer.setVisibility(View.VISIBLE);
            }

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

    getViewModel().onPosterClicked().observe(this, images -> {
      if (images != null && !images.isEmpty()) {
        GallerySliderActivity.open(this, images, 0);
      }
    });

    getViewModel().onBackdropClicked().observe(this, images -> {
      if (images != null && !images.isEmpty()) {
        GallerySliderActivity.open(this, images, 1);
      }
    });
  }

  private void setupCompaniesRecyclerView(List<ProductionCompany> production_companies) {
    CommonUtils.configRecyclerView(getViewDataBinding().companiesRecyclerView, false);
    ProductionCompaniesAdapter moviesAdapter = new ProductionCompaniesAdapter(production_companies);
    getViewDataBinding().companiesRecyclerView.setAdapter(moviesAdapter);
  }

  private void showData() {
    getViewDataBinding().movieDetails.setVisibility(View.VISIBLE);
    getViewDataBinding().generalInclude.contentGeneral.setVisibility(View.GONE);
  }

  private void showNoData() {
    getViewDataBinding().generalInclude.contentGeneral.setVisibility(View.VISIBLE);
    getViewDataBinding().movieDetails.setVisibility(View.GONE);
    getViewDataBinding().generalInclude.progressBar.setVisibility(View.GONE);
    getViewDataBinding().generalInclude.emptyViewContainer.setVisibility(View.VISIBLE);
    getViewDataBinding().generalInclude.internetErrorViewContainer.setVisibility(View.GONE);
    getViewDataBinding().generalInclude.reloadBtn.setVisibility(View.VISIBLE);
  }

  private void showNoInternet() {
    getViewDataBinding().generalInclude.contentGeneral.setVisibility(View.VISIBLE);
    getViewDataBinding().movieDetails.setVisibility(View.GONE);
    getViewDataBinding().generalInclude.progressBar.setVisibility(View.GONE);
    getViewDataBinding().generalInclude.emptyViewContainer.setVisibility(View.GONE);
    getViewDataBinding().generalInclude.internetErrorViewContainer.setVisibility(View.VISIBLE);
    getViewDataBinding().generalInclude.reloadBtn.setVisibility(View.VISIBLE);
  }

  private void showProgress() {
    getViewDataBinding().generalInclude.contentGeneral.setVisibility(View.VISIBLE);
    getViewDataBinding().movieDetails.setVisibility(View.GONE);
    getViewDataBinding().generalInclude.progressBar.setVisibility(View.VISIBLE);
    getViewDataBinding().generalInclude.emptyViewContainer.setVisibility(View.GONE);
    getViewDataBinding().generalInclude.internetErrorViewContainer
        .setVisibility(View.GONE);
    getViewDataBinding().generalInclude.reloadBtn.setVisibility(View.GONE);
  }
}