package com.example.imagecapture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imagecapture.configuracion.ConfigDB;
import com.example.imagecapture.configuracion.CustomAdapter;
import com.example.imagecapture.configuracion.SQLiteConnection;
import com.example.imagecapture.configuracion.personas;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {

    SQLiteConnection conexion;
    ListView list;
    ArrayList<personas> listpersonas;

    public ActivityList(Context context) {
        super();
        conexion = new SQLiteConnection(context, ConfigDB.namebd, null, 1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list = (ListView) findViewById(R.id.Lista);

        ObtenerTabla();

        CustomAdapter apd = new CustomAdapter(this, listpersonas);
        list.setAdapter(apd);


    }

    private void ObtenerTabla() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        personas person;
        listpersonas = new ArrayList<>();

        Cursor cursor = db.rawQuery(ConfigDB.SelectTBPersonas, null);

        while (cursor.moveToNext()) {
            person = new personas();
            person.setId(cursor.getInt(0));
            person.setNombres(cursor.getString(1));
            person.setDescripcion(cursor.getString(2));
            listpersonas.add(person);
        }

        cursor.close();

    }

    private class CustomAdapter extends ArrayAdapter<personas> {
        private Context context;
        private ArrayList<personas> personasList;

        public CustomAdapter(Context context, ArrayList<personas> personasList) {
            super(context, 0, personasList);
            this.context = context;
            this.personasList = personasList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                rowView = inflater.inflate(R.layout.row_personas, parent, false);
            }

            personas person = personasList.get(position);

            ImageView imageView = rowView.findViewById(R.id.ImagePerson);
            TextView nameTextView = rowView.findViewById(R.id.PersonName);
            TextView descripTextView = rowView.findViewById(R.id.PersonDescrip);

            // Aquí puedes establecer los datos de la persona en los elementos de la vista
            // Por ejemplo:
            // imageView.setImageResource(R.drawable.persona_image);
            // nameTextView.setText(person.getNombres());
            // descripTextView.setText(person.getDescripcion());

            return rowView;
        }
    }
}