package com.example.pablo.emp10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Comprobacion de fragments e incializacion
        if(savedInstanceState==null)
        {
            MainFragment fragment = new MainFragment();
            getFragmentManager().beginTransaction().add(android.R.id.content,fragment,fragment.getClass().getSimpleName()).commit();
        }
    }
}
