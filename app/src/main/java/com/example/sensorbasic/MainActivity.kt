package com.example.sensorbasic

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var  manager: SensorManager
    private lateinit var listener: SensorEventListener
    private lateinit var list: List<Sensor>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager  = getSystemService(SENSOR_SERVICE) as SensorManager
        list = manager.getSensorList(Sensor.TYPE_ACCELEROMETER)

        listener = object : SensorEventListener{
            override fun onSensorChanged(event: SensorEvent) {
                Toast.makeText(
                    this@MainActivity,
                    "加速度：${event.values[0]}",Toast.LENGTH_SHORT
                ).show()
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
    }

    override fun onResume() {
        super.onResume()

        if(list.isNotEmpty()){
            manager.registerListener(
                listener,list[0],
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onPause() {
        super.onPause()

        manager.unregisterListener(listener,list[0])
    }
}