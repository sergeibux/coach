package com.example.binou.coach.modele;

import android.util.Log;

import com.example.binou.coach.controleur.Controle;
import com.example.binou.coach.outils.AccesHTTP;
import com.example.binou.coach.outils.AsyncResponse;
import com.example.binou.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class AccesDistant implements AsyncResponse {
    private static final String SERVERADDR = "http://192.168.1.22/Coach/serveurCoach.php";
//    private static final String SERVERADDR = "http://localhost/Coach/serveurCoach.php";
    private Controle controle;

    public AccesDistant(){
        controle.getInstance(null);
    }

    @Override
    public void processFinish(String output) {
        String[] message = output.split("%");
//        Log.d("Serveur : ", "**************"+output);

        if(message.length > 1){
            if(message[0].equals("enreg")){
                Log.d("enreg : ", message[1]);
            }else{

                if (message[0].equals("tous")){
                    Log.d("tous : ", message[1]);
                    try {
                        ArrayList<Profil> lesProfils = new ArrayList<Profil>();
                        JSONArray infos = new JSONArray(message[1]);
                        for (int i=0; i<infos.length(); i++) {
                            JSONObject info = new JSONObject(infos.get(i).toString());
                            Integer iPoids = info.getInt("poids");
                            Integer iTaille = info.getInt("taille");
                            Integer iAge = info.getInt("age");
                            Integer iSexe = info.getInt("sexe");
                            Date dDate = MesOutils.convertStringToDate(info.getString("datemesure"), "yyyy-MM-dd hh:mm:ss");
                            Profil profil = new Profil(dDate, iPoids, iTaille, iAge, iSexe);
                            lesProfils.add(profil);
                        }
                        controle.setLesProfils(lesProfils);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("Erreur : ", message[1]);
                }
            }
        }else{
            Log.d("Nothing...", "Nothing to do ::: "+message[0]);
        }
    }

    public void envoi(String operation, JSONArray lesdonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesDonnees", lesdonneesJSON.toString());
        Log.d ("????", "operation : >"+operation+"<\n lesDonnees : >"+lesdonneesJSON.toString()+"< \n serveraddr : "+SERVERADDR);
        accesDonnees.execute(SERVERADDR);
    }
}
