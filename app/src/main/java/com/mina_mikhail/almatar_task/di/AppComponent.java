package com.mina_mikhail.almatar_task.di;

import com.mina_mikhail.almatar_task.data.repo.MovieDetailsRepository;
import com.mina_mikhail.almatar_task.data.repo.PopularMoviesRepository;
import com.mina_mikhail.almatar_task.ui.movie_details.MovieDetailsViewModel;
import com.mina_mikhail.almatar_task.ui.movies.MoviesViewModel;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { MyApplicationModule.class, RepositoryModule.class })
public interface AppComponent {

  PopularMoviesRepository getMoviesRepository();

  MovieDetailsRepository getMovieDetailsRepository();

  void inject(MoviesViewModel moviesViewModel);

  void inject(MovieDetailsViewModel movieDetailsViewModel);
}