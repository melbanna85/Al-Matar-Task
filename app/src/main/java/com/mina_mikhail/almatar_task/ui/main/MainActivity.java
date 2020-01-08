package com.mina_mikhail.almatar_task.ui.main;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import com.mina_mikhail.almatar_task.BR;
import com.mina_mikhail.almatar_task.R;
import com.mina_mikhail.almatar_task.databinding.ActivityMainBinding;
import com.mina_mikhail.almatar_task.ui.base.BaseActivity;

public class MainActivity
    extends BaseActivity<ActivityMainBinding, MainViewModel> {

  public static void open(Activity activity) {
    Intent intent = new Intent(activity, MainActivity.class);
    activity.startActivity(intent);
  }

  private MainViewModel mViewModel;

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  public MainViewModel getViewModel() {
    return mViewModel;
  }

  @Override
  protected void setUpViewModel() {
    mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    getViewDataBinding().setViewModel(getViewModel());
    initBaseObservables();
  }

  @Override
  protected void setUpViews() {

  }

  @Override
  protected void setUpObservables() {

  }
}