package com.captechventures.transportdirectory

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.captechventures.transportdirectory.databinding.ActivityMainBinding

class TransportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView(this,
                R.layout.activity_main) as ActivityMainBinding

    }
}
