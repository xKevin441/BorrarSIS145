package com.edu.BorrarSIS145

import android.annotation.SuppressLint
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

class BdActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    var dbHandler: BaseDatos? = null
    var listTasks: List<Lugares> = ArrayList<Lugares>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bd)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonDBcreate = findViewById<Button>(R.id.buttonDBcreate)
        val buttonDBlist = findViewById<Button>(R.id.buttonDBlist)
        val buttonDBdelete = findViewById<Button>(R.id.buttonEliminar)

        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val editTextDescripcion = findViewById<EditText>(R.id.editTextDescrip)
        val editTextLatitud = findViewById<EditText>(R.id.editTextLatitud)
        val editTextLongitud = findViewById<EditText>(R.id.editTextLongitud)

        val textViewNombres = findViewById<TextView>(R.id.textViewNombres)

        dbHandler = BaseDatos(this)

        buttonDBcreate.setOnClickListener(){
            var success: Boolean = false
            val lugares: Lugares = Lugares()
            lugares.nombre = editTextNombre.text.toString()
            lugares.descripcion = editTextDescripcion.text.toString()
            lugares.latitud = editTextLatitud.text.toString().toFloat()
            lugares.longitud = editTextLongitud.text.toString().toFloat()
            success = dbHandler?.addLugar(lugares) as Boolean
        }

        buttonDBdelete.setOnClickListener(){
            dbHandler?.deleteAllLugares()
        }

        buttonDBlist.setOnClickListener {
            // Obtener la lista de lugares desde la base de datos
            listTasks = dbHandler?.lugar ?: emptyList()

            // Concatenar todos los detalles en una sola cadena
            val detallesConcatenados = listTasks.joinToString(separator = "\n\n") {
                "Nombre: ${it.nombre}\nDescripcion: ${it.descripcion}\nLatitud: ${it.latitud}\nLongitud: ${it.longitud}"
            }

            // Actualizar el contenido del TextView
            textViewNombres.text = detallesConcatenados
        }

       /* buttonDBlist.setOnClickListener(){
            listTasks = (dbHandler as BaseDatos).lugar

            Log.d("Datos","--->" + listTasks[0].nombre)
            for(lugares in listTasks){
                //Log.d("Datos","--->" + lugares.nombre)
                Toast.makeText(this@BdActivity, "-->"+lugares.nombre, Toast.LENGTH_SHORT).show()
                Toast.makeText(this@BdActivity, "-->"+lugares.descripcion, Toast.LENGTH_SHORT).show()
            }
        }*/
    }
}