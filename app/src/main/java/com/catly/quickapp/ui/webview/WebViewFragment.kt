package com.catly.quickapp.ui.webview

import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.quickapp.R
import com.example.quickapp.databinding.WebviewFragmentBinding


class WebViewFragment : Fragment() {
    private lateinit var binding: WebviewFragmentBinding
    private val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WebviewFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = binding.toolbar
        val webView = binding.webview

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24dp)
        toolbar.title = args.name
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        toolbar.setNavigationOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                requireActivity().onBackPressed()
            }
        }

        webView.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action and MotionEvent.ACTION_UP == 0 && webView.canGoBack()) {
                webView.goBack()
                return@OnKeyListener true
            }
            false
        })
        webView.loadUrl(args.htmlUrl)
    }
}