package com.example.pablo.emp10;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.net.ConnectException;

public class MainFragment extends Fragment{

    //Conjunto de variables necesarias para la ejecucion del programa
    private Button madrid, barcelona, malaga, valencia;
    protected OkHttpClient client = new OkHttpClient();
    protected String stringResponse, url="";

    //Metodo para la creacion de la vista
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.main_fragment, container, false);

        //Asingamos las variables a los elementos del layout
        madrid = (Button)view.findViewById(R.id.Madrid);
        barcelona = (Button)view.findViewById(R.id.Barcelona);
        valencia = (Button)view.findViewById(R.id.Valencia);
        malaga = (Button)view.findViewById(R.id.Malaga);

        //Asignamos un listener al boton de madrid
        madrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Establecemos su URL y lanzamos el asynctask
                url="http://api.openweathermap.org/data/2.5/weather?q=madrid&APPID=ca2ea04168eea27d6b30c8422f0f2144";
                downloadInfo di = new downloadInfo();
                di.execute();
            }
        });

        //Asignamos un listener al boton de barcelona
        barcelona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Establecemos su URL y lanzamos el asynctask
                url="http://api.openweathermap.org/data/2.5/weather?q=barcelona&APPID=ca2ea04168eea27d6b30c8422f0f2144";
                downloadInfo di = new downloadInfo();
                di.execute();
            }
        });

        //Asignamos un listener al boton de valencia
        valencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Establecemos su URL y lanzamos el asynctask
                url="http://api.openweathermap.org/data/2.5/weather?q=valencia&APPID=ca2ea04168eea27d6b30c8422f0f2144";
                downloadInfo di = new downloadInfo();
                di.execute();
            }
        });

        //Asignamos un listener al boton de malaga
        malaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Establecemos su URL y lanzamos el asynctask
                url="http://api.openweathermap.org/data/2.5/weather?q=malaga&APPID=ca2ea04168eea27d6b30c8422f0f2144";
                downloadInfo di = new downloadInfo();
                di.execute();
            }
        });

        return view;
    }

    //Asynctask para la conexion con el servidor
    private class downloadInfo extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            //Creamos la peticion al servidor
            Request.Builder builder = new Request.Builder().addHeader("Accept","application/json");
            builder.url(url);
            Request request = builder.build();

            try
            {
                //solicitamos la respuesta del servidor
                Response response = client.newCall(request).execute();
                ResponseBody res=response.body();
                //comprobamos la respuesta
                stringResponse=res.string();
                System.out.println("DEBUGX: " +stringResponse);

                //obtenenmos los json necesarios
                JSONObject json = new JSONObject(stringResponse);
                JSONObject cord = json.getJSONObject("coord");
                JSONObject main = json.getJSONObject("main");

                //comprobamos la respuesta del servidor
                if(json.getInt("cod")==200)
                {
                    //al ser la respuesta correcta, metemos en un bundle los datos que nos interesan para pasarselos al siguiente activity
                    String nombre = json.getString("name");
                    Double lat = cord.getDouble("lat");
                    Double lon = cord.getDouble("lon");
                    Double temp = main.getDouble("temp");
                    Double pressure = main.getDouble("pressure");
                    Double humidity = main.getDouble("humidity");

                    temp = temp-273;

                    Bundle b = new Bundle();
                    b.putString("ciudad",nombre);
                    b.putDouble("lat",lat);
                    b.putDouble("lon",lon);
                    b.putDouble("temp",temp);
                    b.putDouble("pressure",pressure);
                    b.putDouble("humidity",humidity);

                    Intent i = new Intent(getActivity(),InfoActivity.class);
                    i.putExtras(b);

                    startActivity(i);
                }
                else
                {
                    //Al ser la respuesta negativa, mostramos un mensaje por pantalla
                    Toast.makeText(getActivity(),"No se ha encontrado la ubicacion", Toast.LENGTH_SHORT).show();
                }


            //Conjunto de posibles excepciones que pueden darse al trabajar con el JSON
            }catch (JSONException excpjson){
                excpjson.printStackTrace();
                System.out.println("FALLO1");
                return  null;

            }catch (ConnectException noConect){
                noConect.printStackTrace();
                System.out.println("FALLO2");

            }catch (Exception e){
                e.printStackTrace();
                System.out.println("FALLO3");
            }
            return null;
        }

    }

}
