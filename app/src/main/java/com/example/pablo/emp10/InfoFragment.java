package com.example.pablo.emp10;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {

    //Conjunto de variables necesarias para la ejecucion del programa
    private TextView ciudad, latitud, longitud, temperatura, presion, humedad;
    private String ciudadM;
    private double latitudM, longitudM, temperaturaM, presionM, humedadM;

    //Metodo para la creacion de la vista
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.info_fragment, container, false);

        //capturamos la informacion pasada por el intent
        ciudadM = getArguments().getString("ciudad");
        latitudM = getArguments().getDouble("lat");
        longitudM = getArguments().getDouble("lon");
        temperaturaM = getArguments().getDouble("temp");
        presionM = getArguments().getDouble("pressure");
        humedadM = getArguments().getDouble("humidity");

        //Asingamos las variables a los elementos del layout
        ciudad = (TextView)view.findViewById(R.id.Ciudad);
        latitud = (TextView)view.findViewById(R.id.Latitud);
        longitud = (TextView)view.findViewById(R.id.Longitud);
        temperatura = (TextView)view.findViewById(R.id.Temperatura);
        presion = (TextView)view.findViewById(R.id.Presion);
        humedad = (TextView)view.findViewById(R.id.Humedad);

        //establecemos los valores que queremos mostrar
        ciudad.setText("Ciudad: "+ciudadM);
        latitud.setText("Latitud: "+latitudM);
        longitud.setText("Longitud: "+longitudM);
        temperatura.setText("Temperatura: "+temperaturaM+"ºC");
        presion.setText("Presion: "+presionM+"mb");
        humedad.setText("Humedad: "+humedadM+"%");

        return view;
    }

}
