package com.mina_mikhail.almatar_task.data.source.remote.response;

import com.mina_mikhail.almatar_task.data.model.Movie;
import java.util.List;

public class PopularMoviesResponse
    extends BaseResponse {

  private List<Movie> results;

  public List<Movie> getResults() {
    return results;
  }
}
