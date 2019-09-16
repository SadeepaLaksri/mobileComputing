package mobile.computing.sensorapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {


    var mSensorManager: SensorManager? = null
    var mAccelerometer: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.let {
            mAccelerometer = it
        }

        mSensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)?.let {
            mAccelerometer = it
        }

        mSensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)?.let {
            mAccelerometer = it
        }

        startButton.setOnClickListener {
            onResume()
        }

        endButton.setOnClickListener {
            onPause()
        }
    }


    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
//        when(p0?.Sensor?.)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        when(p0?.sensor?.type){
            Sensor.TYPE_ACCELEROMETER ->{
                aX.text = p0.values[0].toString()
                aY.text = p0.values[1].toString()
                aZ.text = p0.values[2].toString()
            }
            Sensor.TYPE_GYROSCOPE ->{
                gX.text = p0.values[0].toString()
                gY.text = p0.values[1].toString()
                gZ.text = p0.values[2].toString()
            }

            Sensor.TYPE_STEP_DETECTOR ->{
                nS.text = p0.values[0].toString()
            }

        }

    }

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(this)
    }

}
