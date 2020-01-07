package com.mina_mikhail.almatar_task.ui.movies;

import com.mina_mikhail.almatar_task.app.MyApplication;
import com.mina_mikhail.almatar_task.data.model.Movie;
import com.mina_mikhail.almatar_task.data.model.RemoteDataSource;
import com.mina_mikhail.almatar_task.data.repo.PopularMoviesRepository;
import com.mina_mikhail.almatar_task.data.source.local.dp.AppDatabase;
import com.mina_mikhail.almatar_task.ui.base.BaseViewModel;
import java.util.List;
import javax.inject.Inject;

public class MoviesViewModel
    extends BaseViewModel {

  @Inject
  PopularMoviesRepository repository;

  private RemoteDataSource<List<Movie>> movies;

  public MoviesViewModel() {
    MyApplication.getInstance().getAppComponent().inject(this);

    // DaggerAppComponent.create().inject(this);
    movies = new RemoteDataSource<>();
  }

  void getMovies(String sortBy) {
    movies = repository.getMovies(sortBy);
  }

  RemoteDataSource<List<Movie>> getMoviesData() {
    return movies;
  }

  @Override
  protected void onCleared() {
    AppDatabase.destroyInstance();
    repository.destroyInstance();
    super.onCleared();
  }
}