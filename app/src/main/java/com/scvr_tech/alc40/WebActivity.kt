package com.scvr_tech.alc40

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.View.SCROLLBARS_INSIDE_OVERLAY
import android.webkit.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_web.*
import android.webkit.WebView
import android.webkit.WebChromeClient

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        webViewSettings()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSettings() {
        val webSettings = web_view.settings
        webSettings.javaScriptEnabled = true
        // Improve performance
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webSettings.setAppCacheEnabled(true)
        web_view.scrollBarStyle = SCROLLBARS_INSIDE_OVERLAY
        webSettings.domStorageEnabled = true
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        webSettings.useWideViewPort = true
        web_view.webViewClient = CustomClient(this)
        web_view.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100) {
                    swipe_refresh.isRefreshing = false
                } else {
                    progress_bar.visibility = View.VISIBLE
                    progress_bar.progress = newProgress
                    swipe_refresh.isRefreshing = true
                }
            }

            override fun onReceivedTitle(view: WebView, title: String) {
                supportActionBar!!.title = view.title
            }
        }

        web_view.loadUrl("https://andela.com/alc/")
    }

    class CustomClient constructor(private val activity: Activity) : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString()
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        // noInspection SimplifiableIfStatement
        if(id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
