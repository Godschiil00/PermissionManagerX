package com.mirfatif.permissionmanagerx.main;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener;
import com.mirfatif.permissionmanagerx.R;
import com.mirfatif.permissionmanagerx.databinding.ActivityMainBinding;
import com.mirfatif.permissionmanagerx.main.FeedbackDialogFrag.FeedbackType;
import com.mirfatif.permissionmanagerx.main.fwk.MainActivity;
import com.mirfatif.permissionmanagerx.prefs.MySettings;

class Feedback {

  private final MainActivity mA;
  private final ActivityMainBinding mB;

  Feedback(MainActivity activity) {
    mA = activity;
    mB = mA.getRootView();
  }

  // Resuming visibility and alpha of the Feedback container after it's
  // swiped away is buggy. So we show the container only once per Activity launch.
  private boolean mFeedbackSwiped = false;

  void askForFeedback() {
    if (!mFeedbackSwiped && MySettings.INSTANCE.shouldAskForFeedback()) {
      mB.movCont.feedbackCont.setVisibility(View.VISIBLE);
    }

    if (mB.movCont.feedbackCont.getVisibility() != View.VISIBLE) {
      return;
    }

    mB.movCont.likingAppYesButton.setOnClickListener(v -> showDialog(true));
    mB.movCont.likingAppNoButton.setOnClickListener(v -> showDialog(false));

    SwipeDismissBehavior<View> dismissBehavior = new SwipeDismissBehavior<>();
    dismissBehavior.setListener(new FeedbackDismissListener());
    ((LayoutParams) mB.movCont.feedbackCont.getLayoutParams()).setBehavior(dismissBehavior);

    Animation anim = AnimationUtils.loadAnimation(mA, R.anim.shake);
    mB.movCont.feedbackCont.postDelayed(() -> mB.movCont.feedbackCont.startAnimation(anim), 1000);
  }

  private void showDialog(boolean isYes) {
    @FeedbackType int type = isYes ? FeedbackType.POSITIVE : FeedbackType.NEGATIVE;
    FeedbackDialogFrag.show(type, mA.getSupportFragmentManager());
    mB.movCont.feedbackCont.setVisibility(View.GONE);
  }

  private class FeedbackDismissListener implements OnDismissListener {

    @Override
    public void onDismiss(View view) {
      mB.movCont.feedbackCont.setVisibility(View.GONE);
      mFeedbackSwiped = true;
    }

    @Override
    public void onDragStateChanged(int state) {}
  }
}
