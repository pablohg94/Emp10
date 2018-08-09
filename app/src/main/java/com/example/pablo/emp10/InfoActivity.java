package com.example.pablo.emp10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //obtenemos la informacion pasada por el intent
        Bundle b = this.getIntent().getExtras();

        //Comprobacion de fragments e incializacion
        if(savedInstanceState==null)
        {
            InfoFragment fragment = new InfoFragment();
            getFragmentManager().beginTransaction().add(android.R.id.content,fragment,fragment.getClass().getSimpleName()).commit();

            //enviamos al fragment la informacion obtenida por el intent
            fragment.setArguments(b);

        }
    }
}