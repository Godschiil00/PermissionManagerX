package com.mirfatif.permissionmanagerx.ui.base;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import com.mirfatif.permissionmanagerx.util.Utils;

public class MyWebView extends WebView {

  public MyWebView(Context context, AttributeSet attrs) {
    super(context, attrs);
    Utils.onCreateLayout(this);
  }
}
