package com.example.clasesdesis145

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BDActivity : AppCompatActivity() {

    var dbHandler:BaseDatos?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bdactivity)
        val buttoncrear= findViewById<Button>(R.id.button_crear)
        val buttonlistar=findViewById<Button>(R.id.button_listar)
        //val Texto=findViewById<EditText>(R.id.listas)
        val textViewLugares = findViewById<TextView>(R.id.texto_box)
        val nombre=findViewById<EditText>(R.id.text_nombre)
        val descripcion=findViewById<EditText>(R.id.text_descripcion)
        val latitud=findViewById<EditText>(R.id.text_latitud)
        val longitud=findViewById<EditText>(R.id.text_longitud)
        val eliminar = findViewById<Button>(R.id.button_eliminar)


        buttoncrear.setOnClickListener(){
            dbHandler = BaseDatos(this)
            var success: Boolean = false
            val lugares: Lugares = Lugares()
            lugares.nombre = nombre.text.toString()
            lugares.descripcion = descripcion.text.toString()
            lugares.latitud =latitud.text.toString().toDouble()
            lugares.longitud = longitud.text.toString().toDouble()
            success = dbHandler?.addLugar(lugares) as Boolean

        }

        buttonlistar.setOnClickListener() {
            val listTasks = dbHandler?.lugar ?: emptyList()

            if (listTasks.isNotEmpty()) {
                val lugaresTexto = StringBuilder()
                for (lugares in listTasks) {
                    Log.d("Datos", "---> ${lugares.nombre}")
                    lugaresTexto.append("Nombre: ${lugares.nombre}\n")
                    lugaresTexto.append("Descripción: ${lugares.descripcion}\n")
                    lugaresTexto.append("Latitud: ${lugares.latitud}\n")
                    lugaresTexto.append("Longitud: ${lugares.longitud}\n\n")
                }
                textViewLugares.text = lugaresTexto.toString()
            } else {
                Toast.makeText(this, "No hay lugares para mostrar", Toast.LENGTH_LONG).show()
                textViewLugares.text = ""
            }


        }
        eliminar.setOnClickListener {
            val deleted = dbHandler?.deleteAllLugares() // Aquí pasas el ID del lugar que quieres eliminar
            if (deleted == true) {
                Toast.makeText(this, "Lugar eliminado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al eliminar el lugar", Toast.LENGTH_SHORT).show()
            }
        }


    }
}