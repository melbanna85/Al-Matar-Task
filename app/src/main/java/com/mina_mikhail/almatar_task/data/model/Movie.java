package com.mina_mikhail.almatar_task.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.mina_mikhail.almatar_task.utils.Constants;
import com.mina_mikhail.almatar_task.utils.DateTimeUtils;

@Entity(tableName = "movies")
public class Movie {

  /**
   * vote_count : 1713
   * poster_path : /xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg
   * id : 419704
   * adult : false
   * title : Ad Astra
   * vote_average : 5
   * release_date : 2019-09-17
   */

  @PrimaryKey
  private int id;
  private int vote_count;
  private String poster_path;
  private String original_language;
  private boolean adult;
  private String title;
  private float vote_average;
  private String release_date;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getVote_count() {
    return vote_count;
  }

  public void setVote_count(int vote_count) {
    this.vote_count = vote_count;
  }

  public String getPoster_path() {
    if (!poster_path.contains(Constants.IMAGES_BASE_URL)) {
      return Constants.IMAGES_BASE_URL + poster_path;
    } else {
      return poster_path;
    }
  }

  public void setPoster_path(String poster_path) {
    this.poster_path = poster_path;
  }

  public String getOriginal_language() {
    return original_language.toUpperCase();
  }

  public void setOriginal_language(String original_language) {
    this.original_language = original_language;
  }

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public float getVote_average() {
    return vote_average / 2;
  }

  public void setVote_average(float vote_average) {
    this.vote_average = vote_average;
  }

  public String getRelease_date() {
    return release_date;
  }

  public String getReleaseDateCasted() {
    return DateTimeUtils.changeDateFormat(release_date, "yyyy-MM-dd", "dd MMM yyyy");
  }

  public void setRelease_date(String release_date) {
    this.release_date = release_date;
  }
}