package com.example.devicesdata.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.devicesdata.R;
import com.example.devicesdata.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private WebView webView;
    private EditText editText;
    private Button confirmButton;
    private LinearLayout rightContainer;
    private boolean isFullWidth = true; // 记录 WebView 宽度状态

    private InPutDataView dataView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataManager.getInstance().home = this;
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // 获取 WebView
        webView = rootView.findViewById(R.id.webView);
        DataManager.getInstance().webview = webView;
        // ...

        // 获取输入框、确定按钮和右侧容器
        rightContainer = rootView.findViewById(R.id.rightContainer);

        // 启用 JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 设置 WebView 客户端以在应用内打开链接
        webView.setWebViewClient(new WebViewClient());

        // 加载特定的页面
        String url =  DataManager.getInstance().getURL(); // 在这里替换为你要加载的网址
        webView.loadUrl(url);


        rightContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果需要处理 LinearLayout 的点击事件，在这里处理
                hideKeyboard();
            }
        });


        return rootView;
    }

    public void onClick() {
        if (isFullWidth) {
            // 当 WebView 宽度已占据全部时，切换为占据一半宽度
            ViewGroup.LayoutParams webViewParams = webView.getLayoutParams();
            webViewParams.width = getResources().getDisplayMetrics().widthPixels / 2;
            webView.setLayoutParams(webViewParams);

            ViewGroup.LayoutParams rightContainerParams = rightContainer.getLayoutParams();
            rightContainerParams.width = getResources().getDisplayMetrics().widthPixels / 2;
            rightContainer.setLayoutParams(rightContainerParams);
            rightContainer.setVisibility(View.VISIBLE);
        } else {
            // 当 WebView 宽度占据一半时，切换为占据全部宽度
            ViewGroup.LayoutParams webViewParams = webView.getLayoutParams();
            webViewParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            webView.setLayoutParams(webViewParams);

            ViewGroup.LayoutParams rightContainerParams = rightContainer.getLayoutParams();
            rightContainerParams.width = 0;
            rightContainer.setLayoutParams(rightContainerParams);
            rightContainer.setVisibility(View.GONE);
        }
        isFullWidth = !isFullWidth; // 切换状态
    }

    public void handleOverflowMenuItemClick() {
        // 在这里处理从 Activity 传递过来的溢出菜单点击事件
        // 添加您希望在 HomeFragment 中执行的代码逻辑
        Log.d("111111","home 处理事件");
        onClick();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // 在 Fragment 中隐藏键盘的方法
    private void hideKeyboard() {
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        dataView = view.findViewById(R.id.shebeiview);
//        dataView.config();
//    }
}