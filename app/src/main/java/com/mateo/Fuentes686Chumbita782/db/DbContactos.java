package com.mateo.Fuentes686Chumbita782.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mateo.Fuentes686Chumbita782.clases.Contacto;

import java.util.ArrayList;

public class DbContactos extends DbHelper {
    Context context;

    public DbContactos(Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String nombre, String apellido, String telefono, String direccion, String genero, String color) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("telefono", telefono); // Cambiado de "numero" a "telefono"
            values.put("direccion", direccion);
            values.put("genero", genero);
            values.put("color", color); // Cambiado de "colorng" a "color"

            id = db.insert(TABLE_CONTACTOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }


    public ArrayList<Contacto> mostrarContactos() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Contacto> listaContactos = new ArrayList<>();
        Contacto contacto = null;
        Cursor cursorContactos =  null;

        cursorContactos = db.rawQuery("SELECT * FROM "+TABLE_CONTACTOS,null);
        if(cursorContactos.moveToFirst()){
            do{
                contacto = new Contacto();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setApellido(cursorContactos.getString(2));
                contacto.setNumero(cursorContactos.getString(3));
                contacto.setDireccion(cursorContactos.getString(4));
                contacto.setGenero(cursorContactos.getString(5));
                contacto.setColor(cursorContactos.getString(6));
                listaContactos.add(contacto);
            }while(cursorContactos.moveToNext());

        }

        cursorContactos.close();
        return listaContactos;

    }



    public Contacto verContacto(int id) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contacto contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM "+TABLE_CONTACTOS+" WHERE id = "+ id+" LIMIT 1",null);
        if(cursorContactos.moveToFirst()){

                contacto = new Contacto();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setApellido(cursorContactos.getString(2));
                contacto.setNumero(cursorContactos.getString(3));
                contacto.setDireccion(cursorContactos.getString(4));
                contacto.setGenero(cursorContactos.getString(5));
                contacto.setColor(cursorContactos.getString(6));

        }

        cursorContactos.close();
        return contacto;

    }



    public boolean EditarContacto(int id,String nombre, String apellido, String telefono, String direccion, String genero, String color) {
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE "+TABLE_CONTACTOS+" SET nombre = '"+nombre+"', apellido = '"+apellido+"', telefono = '"+telefono+"', direccion = '"+direccion+"', genero = '"+genero+"', color = '"+color+"' WHERE id = "+id);
             correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto= false;
        }finally {
            db.close();
        }
        return correcto;
    }



    public boolean EliminarContacto(int id) {
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM "+TABLE_CONTACTOS+" WHERE id = '"+id+"'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto= false;
        }finally {
            db.close();
        }
        return correcto;
    }
}
