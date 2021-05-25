package com.catly.quickapp.ui.webview

import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.quickapp.R
import kotlinx.android.synthetic.main.table_list_item.*
import kotlinx.android.synthetic.main.webview_fragment.*


class WebViewFragment: Fragment() {
    private val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.webview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24dp)
        toolbar.title = args.name
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        toolbar.setNavigationOnClickListener {
            if (webview.canGoBack()){
                webview.goBack()
            } else {
                requireActivity().onBackPressed()
            }
        }

        webview.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action and MotionEvent.ACTION_UP == 0 && webview.canGoBack()) {
                webview.goBack()
                return@OnKeyListener true
            }
            false
        })
        webview.loadUrl(args.htmlUrl)
    }
}