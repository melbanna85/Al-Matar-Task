package com.mina_mikhail.almatar_task.ui.splash;

import android.arch.lifecycle.ViewModelProviders;
import com.mina_mikhail.almatar_task.R;
import com.mina_mikhail.almatar_task.databinding.ActivitySplashBinding;
import com.mina_mikhail.almatar_task.ui.base.BaseActivity;
import com.mina_mikhail.almatar_task.ui.main.MainActivity;

public class SplashActivity
    extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

  private SplashViewModel mViewModel;

  @Override
  public int getBindingVariable() {
    return com.mina_mikhail.almatar_task.BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_splash;
  }

  @Override
  public SplashViewModel getViewModel() {
    return mViewModel;
  }

  @Override
  protected void setUpViewModel() {
    mViewModel = new SplashViewModel();
    mViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
    getViewDataBinding().setViewModel(getViewModel());
    initBaseObservables();
  }

  @Override
  protected void setUpViews() {

  }

  @Override
  protected void setUpObservables() {
    getViewModel().shouldStartApp().observe(this, v -> MainActivity.open(this));
  }
}