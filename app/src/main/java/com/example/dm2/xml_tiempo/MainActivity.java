package com.example.dm2.xml_tiempo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    private TextView max;
    private TextView min;
    private TextView nombre;

    private Web web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = new Web();
        nombre=findViewById(R.id.nombre);
        max=findViewById(R.id.max);
        min=findViewById(R.id.min);



        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://www.aemet.es/xml/municipios/localidad_01059.xml");
        //No funciona correctamente con el XML de AEMET



    }

    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {
        protected Boolean doInBackground(String... params) {
            RssParserSax saxparser = new RssParserSax(params[0]);
            //Log.i("------------>>>", "URL: "+params[0]);
            web = saxparser.parse();
            //Log.i("aa", "Tamaño noticias: "+noticias.size());
            return true;
        }

        protected void onPostExecute(Boolean result) {
            nombre.setText(web.getNombre());
            max.setText(web.getMax());
            min.setText(web.getMin());

        }
    }



}
