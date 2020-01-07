package com.mina_mikhail.almatar_task.data.source.local.dp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import com.mina_mikhail.almatar_task.data.model.Movie;
import com.mina_mikhail.almatar_task.data.model.MovieDetails;
import com.mina_mikhail.almatar_task.data.source.local.dp.dao.MovieDetailsDao;
import com.mina_mikhail.almatar_task.data.source.local.dp.dao.PopularMoviesDao;
import com.mina_mikhail.almatar_task.utils.Constants;
import com.mina_mikhail.almatar_task.utils.Converters;

@Database(entities = { Movie.class, MovieDetails.class }, version = 1, exportSchema = false)
@TypeConverters({ Converters.class })
public abstract class AppDatabase
    extends RoomDatabase {

  private static volatile AppDatabase INSTANCE;

  public static synchronized AppDatabase getInstance(Context context) {
    if (INSTANCE == null) {
      INSTANCE = create(context);
    }
    return INSTANCE;
  }

  private static AppDatabase create(final Context context) {
    return Room.databaseBuilder(context
        , AppDatabase.class
        , Constants.DB_NAME)
        .build();
  }

  public static void destroyInstance() {
    if (INSTANCE != null) {
      INSTANCE.close();
      INSTANCE = null;
    }
  }

  public abstract PopularMoviesDao getPopularMoviesDao();

  public abstract MovieDetailsDao getMovieDetailsDao();
}