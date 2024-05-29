package com.edu.BorrarSIS145

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button_informacion = findViewById<Button>(R.id.button_informacion)
        val button_calculadora = findViewById<Button>(R.id.button_calculadora)
        val button_bd = findViewById<Button>(R.id.button_bd)
        val button_mapa = findViewById<Button>(R.id.button_mapa)
        val button_salir = findViewById<Button>(R.id.button_salir)

        button_informacion.setOnClickListener(){
            val intent = Intent(this@MainActivity, InformacionActivity2::class.java)
            startActivity(intent)
        }

        button_calculadora.setOnClickListener(){
            val intent = Intent(this@MainActivity, CalculadoraActivity::class.java)
            startActivity(intent)
        }

        button_bd.setOnClickListener(){
            val intent = Intent(this@MainActivity, BdActivity::class.java)
            startActivity(intent)
        }

        button_mapa.setOnClickListener(){
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            startActivity(intent)
        }

        button_salir.setOnClickListener(){
            finish();
        }
    }
}