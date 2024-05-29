package com.edu.BorrarSIS145

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(context: Context): SQLiteOpenHelper(context,BaseDatos.NOMBRE_BASE_DATOS,null,BaseDatos.VERSION_BASE_DATOS) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREAR_TABLA = "CREATE TABLE $NOMBRE_TABLA ($ID INT PRIMARY KEY AUTOINCREMENT,$NOMBRE TEXT,$DESCRIPCION TEXT,$LATITUD FLOAT,$LONGITUD FLOAT);"
        db?.execSQL(CREAR_TABLA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val BORRAR_TABLA = "DROP TABLE IF EXISTS $NOMBRE_TABLA"
        db?.execSQL(BORRAR_TABLA)
        onCreate(db)
    }

    fun addLugar(lugares: Lugares):Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NOMBRE,lugares.nombre)
        values.put(DESCRIPCION,lugares.descripcion)
        values.put(LATITUD,lugares.latitud)
        values.put(LONGITUD,lugares.longitud)
        val _success = db.insert(NOMBRE_TABLA,null,values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    @SuppressLint("Range")
    fun getLugar(_id: Int): Lugares{
        val lugares = Lugares()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $NOMBRE_TABLA WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor != null){
            cursor.moveToFirst()
            while(cursor.moveToNext()){
                lugares.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                lugares.nombre = cursor.getString(cursor.getColumnIndex(NOMBRE))
                lugares.descripcion = cursor.getString(cursor.getColumnIndex(DESCRIPCION))
                lugares.latitud =  cursor.getString(cursor.getColumnIndex(LATITUD)).toFloat()
                lugares.longitud = cursor.getString(cursor.getColumnIndex(LONGITUD)).toFloat()
            }
        }
        cursor.close()
        return lugares
    }

    val lugar: List<Lugares> @SuppressLint("Range")
    get(){
        val lugaresList = ArrayList<Lugares>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $NOMBRE_TABLA"
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor != null){
            cursor.moveToFirst()
            while(cursor.moveToNext()){
                val lugares = Lugares()
                lugares.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                lugares.nombre = cursor.getString(cursor.getColumnIndex(NOMBRE))
                lugares.descripcion = cursor.getString(cursor.getColumnIndex(DESCRIPCION))
                lugares.latitud = cursor.getString(cursor.getColumnIndex(LATITUD)).toFloat()
                lugares.longitud = cursor.getString(cursor.getColumnIndex(LONGITUD)).toFloat()
                lugaresList.add(lugares)
            }
        }
        cursor.close()
        return lugaresList
    }

    fun updateLugar(lugares: Lugares): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NOMBRE,lugares.nombre)
        values.put(DESCRIPCION,lugares.descripcion)
        values.put(LATITUD,lugares.latitud)
        values.put(LONGITUD,lugares.longitud)
        val _success = db.update(NOMBRE_TABLA,values,ID+"=?",arrayOf(lugares.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    fun deleteLugar(_id: Int): Boolean{
        val db = this.writableDatabase
        val _success = db.delete(NOMBRE_TABLA,ID+"=?",arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    fun deleteAllLugares(): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(NOMBRE_TABLA, null, null).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    companion object{
        private val VERSION_BASE_DATOS = 1
        private val NOMBRE_BASE_DATOS = "dbsis104"
        private val NOMBRE_TABLA = "lugares"
        private val ID = "id"
        private val NOMBRE = "nombre"
        private val DESCRIPCION = "descripcion"
        private val LATITUD = "latitud"
        private val LONGITUD = "longitud"
    }
}