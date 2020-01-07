package com.mina_mikhail.almatar_task.ui.movie_details;

import android.arch.lifecycle.LiveData;
import com.mina_mikhail.almatar_task.app.MyApplication;
import com.mina_mikhail.almatar_task.data.model.MovieDetails;
import com.mina_mikhail.almatar_task.data.model.RemoteDataSource;
import com.mina_mikhail.almatar_task.data.repo.MovieDetailsRepository;
import com.mina_mikhail.almatar_task.data.source.local.dp.AppDatabase;
import com.mina_mikhail.almatar_task.ui.base.BaseViewModel;
import com.mina_mikhail.almatar_task.utils.SingleLiveEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MovieDetailsViewModel
    extends BaseViewModel {

  @Inject
  MovieDetailsRepository repository;

  private RemoteDataSource<MovieDetails> movie;
  private int movieID;

  private SingleLiveEvent<List<String>> onPosterClicked;
  private SingleLiveEvent<List<String>> onBackdropClicked;

  public MovieDetailsViewModel() {
    MyApplication.getInstance().getAppComponent().inject(this);

    // DaggerAppComponent.create().inject(this);
    movie = new RemoteDataSource<>();

    onPosterClicked = new SingleLiveEvent<>();
    onBackdropClicked = new SingleLiveEvent<>();
  }

  void getMovieDetails(int movieID) {
    this.movieID = movieID;
    movie = repository.getMovieDetails(movieID);
  }

  RemoteDataSource<MovieDetails> getMovieDetailsData() {
    return movie;
  }

  public void onPosterClicked(String posterPath, String backdropPath) {
    List<String> images = new ArrayList<>();
    images.add(posterPath);
    images.add(backdropPath);
    onPosterClicked.setValue(images);
  }

  public void onBackdropClicked(String posterPath, String backdropPath) {
    List<String> images = new ArrayList<>();
    images.add(posterPath);
    images.add(backdropPath);
    onBackdropClicked.setValue(images);
  }

  LiveData<List<String>> onPosterClicked() {
    return onPosterClicked;
  }

  LiveData<List<String>> onBackdropClicked() {
    return onBackdropClicked;
  }

  @Override
  protected void onCleared() {
    AppDatabase.destroyInstance();
    repository.destroyInstance(movieID);
    super.onCleared();
  }
}