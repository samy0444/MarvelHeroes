package com.oyelabs.marvelapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.oyelabs.marvelapp.R

class DisplayIformation : AppCompatActivity() {



    private  lateinit var image:ImageView
    private  lateinit var title: TextView
    private lateinit var description: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_iformation)

        //val  data: List<Result>
       val  Intentimage = intent.getStringExtra("image")
        val Intentdescription = intent.getStringExtra("description")
        val Intenttitle= intent.getStringExtra("name")

       image=  findViewById(R.id.image)
        title = findViewById(R.id.title)
        description=findViewById(R.id.detail)

        //Log.d("responsee", );
        Glide.with(this).load(Intentimage).into(image)
        title.text=Intenttitle
        description.text=Intentdescription
    }
}