package com.example.binou.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.binou.coach.R;
import com.example.binou.coach.controleur.Controle;
import com.example.binou.coach.modele.Profil;

import java.util.ArrayList;
import java.util.Collections;

public class HistoActivity extends AppCompatActivity {

    private Controle controle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        this.controle = Controle.getInstance(null);
        ecouteRetourAccueil();
        creerListe();
    }

        private void ecouteRetourAccueil(){
            ((ImageButton)findViewById(R.id.imgBtnRetourHisto)).setOnClickListener(new ImageButton.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HistoActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
        }


    private void creerListe(){
        ArrayList<Profil> liste = controle.getLesProfils();
        Collections.sort(liste, Collections.<Profil>reverseOrder());
        Log.d("null?", "maybe");
        if (liste != null){
            Log.d("null?", "noop");
            ListView listView = (ListView)findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(HistoActivity.this, liste);
            listView.setAdapter(adapter);
        }
    }

    /**
     * Demande à l'activity Calcul d'afficher un profil
     * @param profil
     */
    public void afficheProfil(Profil profil){
        Controle.setProfil(profil);
        Intent intent = new Intent(HistoActivity.this, CalculActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
