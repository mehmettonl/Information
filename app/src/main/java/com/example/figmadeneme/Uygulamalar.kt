package com.example.figmadeneme

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
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

        val pm = packageManager
        val applications = pm.getInstalledApplications(PackageManager.GET_META_DATA)

        for (appInfo in applications) {
            val packageName = appInfo.packageName
            try {
                val installerPackageName = pm.getInstallerPackageName(packageName)
                if (installerPackageName == "com.android.vending") {
                    // Uygulama Google Play Store'dan yüklenmiş

                    // Ana layout oluşturma (ikon ve adı yatay olarak yan yana yerleştirmek için)
                    val appLayout = LinearLayout(this)
                    appLayout.orientation = LinearLayout.HORIZONTAL

                    // ImageView oluşturma ve ikonu ayarlama
                    val imageView = ImageView(this)
                    val icon = appInfo.loadIcon(pm)
                    imageView.setImageDrawable(icon)

                    // Boyutları belirleme (64x64 piksel)
                    val iconLayoutParams = LinearLayout.LayoutParams(128, 128)
                    imageView.layoutParams = iconLayoutParams

                    // TextView oluşturma ve uygulama adını ayarlama
                    val textView = TextView(this)
                    textView.text = appInfo.loadLabel(pm).toString()

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

                    // Ana layout'u ana LinearLayout'a ekleme
                    binding.linearLayoutIcons.addView(appLayout)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
