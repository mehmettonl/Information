package com.example.figmadeneme

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.app.ActivityManager
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import com.example.figmadeneme.databinding.ActivityMainBinding // Import ettiğinizden emin olun
import com.example.figmadeneme.databinding.ActivityUcuncuSayfaBinding

class UcuncuSayfa : AppCompatActivity() {
    private lateinit var binding: ActivityUcuncuSayfaBinding

    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUcuncuSayfaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewSystem.setOnClickListener({
            val intent =Intent(this,BilgiEkrani::class.java)
            startActivity(intent)
        })

        // SensorManager'ı al
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Tüm sensörleri al
        val sensorList: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

        // Sensörlerin sayısını ve isimlerini göster
        val sensorInfo = StringBuilder()
        sensorInfo.append("Sensor Number: ${sensorList.size}\n\n")
        for (sensor in sensorList) {
            sensorInfo.append("Sensor: ${sensor.name} - Tür: ${sensor.type}\n")

            binding.textViewSensorNumber.text = "${sensorInfo}"
            binding.textViewSensors.text = "Sensors${sensorList}"



        }
    }
}