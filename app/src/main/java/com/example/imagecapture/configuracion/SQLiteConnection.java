package com.example.imagecapture.configuracion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteConnection extends SQLiteOpenHelper
{

    public SQLiteConnection(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, ConfigDB.namebd, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /* Creacion de objetos de base de datos*/
        sqLiteDatabase.execSQL(ConfigDB.CreateTBPesonas);  // Creando la tabla de personas en sqlite..
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL(ConfigDB.DropTBPersonas);
        onCreate(sqLiteDatabase);

    }

    public List<personas> getPersonas() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(ConfigDB.SelectTBPersonas, null);

        List<personas> dataSet = new ArrayList<>();
        while(cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(ConfigDB.id);
            int nombresIndex = cursor.getColumnIndex(ConfigDB.nombres);
            int descIndex = cursor.getColumnIndex(ConfigDB.descripcion);
            int fotoIndex = cursor.getColumnIndex(ConfigDB.foto);

            if (idIndex == -1 || nombresIndex == -1 || descIndex == -1 || fotoIndex == -1) {
                continue;   // Skip this row if any of the columns does not exist.
            }

            int id = cursor.getInt(idIndex);
            String nombres = cursor.getString(nombresIndex);
            String descripcion = cursor.getString(descIndex);
            String foto = cursor.getString(fotoIndex);

            personas person = new personas(id, nombres, descripcion, foto);
            dataSet.add(person);
        }

        cursor.close();
        db.close();

        return dataSet;
    }


}

