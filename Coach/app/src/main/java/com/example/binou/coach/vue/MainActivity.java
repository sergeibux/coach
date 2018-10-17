package com.example.binou.coach.vue;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binou.coach.R;
import com.example.binou.coach.controleur.Controle;
import com.example.binou.coach.modele.Profil;

import java.util.Date;


public class MainActivity extends AppCompatActivity {


    private EditText txtPoids, txtTaille, txtAge;
    private RadioButton rdHomme, rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * initialisation de l'instance unique 'controle'
     *
     */
    private void init(){
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        controle = Controle.getInstance(this);

        ecouteCalcul();

        recupProfil();
    }

    /**
     * Permet de faire remonter les infos (désérialiser)
     */
    public void recupProfil(){
        if (controle.getTaille()!=null) {
            txtTaille.setText(controle.getTaille().toString());
            txtPoids.setText(controle.getPoids().toString());
            txtAge.setText(controle.getAge().toString());
            if (controle.getSexe() != null) {
                if (controle.getSexe() == 1) {
                    rdHomme.setChecked(true);
                } else if (controle.getSexe() == 0) {
                    rdFemme.setChecked(true);
                }
            }
            ((Button) findViewById(R.id.btnCalc)).performClick();
        }
    }

    /**
     * Lecture des champs,
     * test si les champs ne sont pas remplis,
     * volorisation du sexe,
     *
     */
    private void ecouteCalcul(){
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                int poids=0, taille=0, age=0;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch (Exception e){
                    // Laisser les variables à 0
                }
                rdHomme = (RadioButton)findViewById(R.id.rdHomme);
                int sexe = 0;
                if (rdHomme.isChecked()){
                    sexe = 1;
                }
                if ((poids * taille * age) == 0){
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs.\nMerci.", Toast.LENGTH_SHORT).show();
                } else {
                    afficheResult(poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * Affichage des résultats
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer taille, Integer age, int sexe){

        controle.creerProfil(poids, taille, age, sexe, this);

        String msg;
        float img;

        msg = controle.getMsg();
        img = controle.getImg();
        String sImg = String.format("%.01f",img);

        if (msg.equals("trop faible")){

            ((ImageView)findViewById(R.id.imgSmiley)).setImageResource(R.drawable.maigre);
            ((TextView)findViewById(R.id.lblIMG)).setText(sImg+" : IMG trop bas ...");
            ((TextView)findViewById(R.id.lblIMG)).setTextColor(Color.RED);

        }else if (msg.equals("trop élevé")){

            ((ImageView)findViewById(R.id.imgSmiley)).setImageResource(R.drawable.graisse);
            ((TextView)findViewById(R.id.lblIMG)).setText(sImg+" : IMG trop élevé !");
            ((TextView)findViewById(R.id.lblIMG)).setTextColor(Color.RED);

        }else {

            ((ImageView)findViewById(R.id.imgSmiley)).setImageResource(R.drawable.normal);
            ((TextView)findViewById(R.id.lblIMG)).setText(sImg+" : IMG bon.");
            ((TextView)findViewById(R.id.lblIMG)).setTextColor(Color.GREEN);

        }
    }
}
