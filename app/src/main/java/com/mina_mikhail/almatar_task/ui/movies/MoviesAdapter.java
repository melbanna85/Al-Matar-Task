package com.mina_mikhail.almatar_task.ui.movies;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.mina_mikhail.almatar_task.R;
import com.mina_mikhail.almatar_task.app.MyApplication;
import com.mina_mikhail.almatar_task.data.model.Movie;
import com.mina_mikhail.almatar_task.databinding.ItemMovieBinding;
import com.mina_mikhail.almatar_task.ui.base.BaseAdapter;
import com.mina_mikhail.almatar_task.ui.base.BaseViewHolder;
import java.util.List;

public class MoviesAdapter
    extends BaseAdapter<Movie> {

  private List<Movie> items;
  private MoviesListener listener;

  private int lastPosition = -1;

  MoviesAdapter(List<Movie> items) {
    super(items);
    this.items = items;
  }

  @Override
  public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
    ItemMovieBinding binding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.getContext()),
        R.layout.item_movie,
        parent,
        false);
    return new MoviesAdapter.ViewHolder(binding);
  }

  @Override
  public void onBindData(RecyclerView.ViewHolder holder, int position) {
    ViewHolder myHolder = (ViewHolder) holder;
    myHolder.onBind(position);

    // add anim to items
    setAnimation(holder.itemView, position);
  }

  // start animation
  private void setAnimation(View viewToAnimate, int position) {
    // If the bound view wasn't previously displayed on screen, it's animated
    if (position > lastPosition) {
      Animation animation = AnimationUtils.loadAnimation(MyApplication.getInstance(),
          (position > lastPosition) ? R.anim.translate : R.anim.slide_up);
      viewToAnimate.startAnimation(animation);
      lastPosition = position;
    }
  }

  // To solve the problem of fast scroll
  @Override
  public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
    super.onViewDetachedFromWindow(holder);
    ViewHolder myHolder = (ViewHolder) holder;
    myHolder.clearAnimation();
    myHolder.unbind();
  }

  public interface MoviesListener {
    void onMovieClicked(Movie movie);
  }

  void registerListener(MoviesListener listener) {
    this.listener = listener;
  }

  void unRegisterListener() {
    listener = null;
  }

  class ViewHolder
      extends BaseViewHolder {

    private final ItemMovieBinding itemBinding;

    ViewHolder(ItemMovieBinding itemBinding) {
      super(itemBinding.getRoot());
      this.itemBinding = itemBinding;
    }

    @Override
    public void onBind(int position) {
      itemBinding.setItem(items.get(position));
      itemBinding.setListener(listener);

      itemBinding.rateAmount.setRating(items.get(position).getVote_average());
    }

    @Override
    public void unbind() {// Don't forget to unbind
      if (itemBinding != null) {
        itemBinding.unbind();
      }
    }

    @Override
    public void clearAnimation() {
      itemView.clearAnimation();
    }
  }
}