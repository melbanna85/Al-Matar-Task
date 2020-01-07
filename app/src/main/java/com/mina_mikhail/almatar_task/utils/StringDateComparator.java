package com.mina_mikhail.almatar_task.utils;

import com.mina_mikhail.almatar_task.data.model.Movie;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class StringDateComparator
    implements Comparator<Movie> {

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public int compare(Movie movie1, Movie movie2) {
    try {
      return dateFormat.parse(movie2.getRelease_date())
          .compareTo(dateFormat.parse(movie1.getRelease_date()));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return 0;
  }
}