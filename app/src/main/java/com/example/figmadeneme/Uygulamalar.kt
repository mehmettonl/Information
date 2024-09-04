package com.example.figmadeneme

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.figmadeneme.databinding.ActivityUygulamalarBinding

class Uygulamalar : AppCompatActivity() {

    private lateinit var binding: ActivityUygulamalarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUygulamalarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Uygulamaları yükleme işlemini arka planda gerçekleştiriyoruz
        LoadAppsTask().execute()
    }

    private fun createAppLayout(appInfo: ApplicationInfo, pm: PackageManager): LinearLayout {
        // Ana layout oluşturma (ikon ve adı yatay olarak yan yana yerleştirmek için)
        val appLayout = LinearLayout(this)
        appLayout.orientation = LinearLayout.HORIZONTAL

        // ImageView oluşturma ve ikonu ayarlama
        val imageView = ImageView(this)
        val icon = appInfo.loadIcon(pm)
        imageView.setImageDrawable(icon)

        // Boyutları belirleme (128x128 piksel)
        val iconLayoutParams = LinearLayout.LayoutParams(128, 128)
        imageView.layoutParams = iconLayoutParams

        // TextView oluşturma ve sadece uygulama adını ayarlama
        val textView = TextView(this)
        val appName = appInfo.loadLabel(pm).toString()

        // TextView metni: Sadece uygulama adı
        textView.text = appName

        // TextView için LayoutParams ayarları (ikonun yanına yerleştirmek için)
        val textLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textLayoutParams.setMargins(16, 0, 0, 0) // İkon ve ad arasına boşluk eklemek için
        textView.layoutParams = textLayoutParams

        // ImageView ve TextView'i ana layout'a ekleme
        appLayout.addView(imageView)
        appLayout.addView(textView)
        return appLayout
    }

    private inner class LoadAppsTask : AsyncTask<Void, Void, List<ApplicationInfo>>() {

        override fun doInBackground(vararg params: Void?): List<ApplicationInfo> {
            val pm = packageManager
            return pm.getInstalledApplications(PackageManager.GET_META_DATA)
        }

        override fun onPostExecute(applications: List<ApplicationInfo>) {
            val pm = packageManager

            for (appInfo in applications) {
                val packageName = appInfo.packageName
                try {
                    val installerPackageName = pm.getInstallerPackageName(packageName)
                    if (installerPackageName == "com.android.vending") {

                        // Uygulama Google Play Store'dan yüklenmişse layout'u oluştur ve ekle
                        val appLayout = createAppLayout(appInfo, pm)
                        binding.linearLayoutIcons.addView(appLayout)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
