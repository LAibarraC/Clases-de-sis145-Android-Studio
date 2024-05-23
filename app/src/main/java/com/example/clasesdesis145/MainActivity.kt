package com.example.clasesdesis145

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val button_inf = findViewById<Button>(R.id.button_inf)
        val button_calculadora= findViewById<Button>(R.id.button_cal)
        val button_bd = findViewById<Button>(R.id.button_bd)
        val button_mapa = findViewById<Button>(R.id.button_mapa)
        //val button_P = findViewById<Button>(R.id.button_P)
        val button_salir = findViewById<Button>(R.id.button_salir)


        button_inf.setOnClickListener(){
            val intento = Intent(this@MainActivity,informacionActivity::class.java)
            startActivity(intento)
        }
        button_calculadora.setOnClickListener(){
            val intento = Intent(this@MainActivity,calculadoraActivity::class.java)
            startActivity(intento)
        }

        button_bd.setOnClickListener(){
            val intento = Intent(this@MainActivity,BDActivity::class.java)
            startActivity(intento)
        }

        //button_P.setOnClickListener(){
            ///val intento = Intent(this@MainActivity,ProductosActivity::class.java)
            //startActivity(intento)
       // }

        button_salir.setOnClickListener(){
            finishAffinity()
        }
        button_mapa.setOnClickListener() {

            val intento= Intent(this@MainActivity,MapsActivity::class.java)
            startActivity(intento)
        }



    }
}