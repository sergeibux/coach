package com.example.binou.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.binou.coach.R;
import com.example.binou.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.controle = Controle.getInstance(this);
        creerMenu();
    }

    public void creerMenu(){
        ecouteMenu((ImageButton)findViewById(R.id.imgBtn), CalculActivity.class);
        ecouteMenu((ImageButton)findViewById(R.id.histBtn), HistoActivity.class);
    }

    public void ecouteMenu(ImageButton imgBtn, final Class classe){
        imgBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
