package com.example.binou.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.binou.coach.modele.AccesDistant;
import com.example.binou.coach.modele.Profil;
import com.example.binou.coach.outils.Serializer;
import com.example.binou.coach.vue.CalculActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

import static com.example.binou.coach.outils.Serializer.deSerialize;

public final class Controle {
    // instance unique de la classe
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofile";
//    public static AccesLocal accesLocal;
    public static AccesDistant accesDistant;
    private static Context context;
    private ArrayList<Profil> lesProfils = new ArrayList<Profil>();


    // constructeur
    private Controle() {
        super();
    }


    public ArrayList<Profil> getLesProfils() {
        return getInstance(context).lesProfils;
    }

    public void setLesProfils(ArrayList<Profil> lesProfils) {

        this.lesProfils = lesProfils;
    }

    /**
     * demande de suppression d'un profil
     * @param profil
     */
    public void delProfil(Profil profil){
        accesDistant.envoi("del", profil.convertToJSONARRAY());
        lesProfils.remove(profil);
    }
    /**
     * Ne permettre qu'une instance
     * @return l'instance de controle pour l'affichage
     */
    public final static Controle getInstance(Context context){
        if (context != null) {
            Controle.context = context;
        }

        if (instance==null) {
            Controle.instance = new Controle();
//            accesLocal = new AccesLocal(context);
            accesDistant = new AccesDistant();
            Log.d("Controle", "getInstance.");
            accesDistant.envoi("tous", new JSONArray());
//            recupSerialize(context);
        }
        return Controle.instance;
    }

    /**
     * Création de profil
     * @param iPoids
     * @param iTaille
     * @param iAge
     * @param iSexe
     * @param context
     */
    public void creerProfil (Integer iPoids, Integer iTaille, Integer iAge, Integer iSexe, Context context){

        Profil unProfil = new Profil(new Date(), iPoids, iTaille, iAge, iSexe);
        lesProfils.add(unProfil);
//        accesLocal.Ajouter(profil);
        accesDistant.envoi("enreg", unProfil.convertToJSONARRAY());
    }

    public static void recupSerialize(Context context){
        profil = (Profil)Serializer.deSerialize(nomFic, context);

    }


    /**
     *
     * @return IMG
     */
    public Float getImg(){
        if (lesProfils.size()==0){
            return null;
        }else {
            return lesProfils.get(lesProfils.size() - 1).getfImg();
        }
    }

    /**
     *
     * @return message
     */
    public String getMsg(){
        if (lesProfils.size()==0){
            return null;
        }else {
            return lesProfils.get(lesProfils.size()-1).getStrMsg();
        }
    }

    /**
     *
     * @return poids
     */
    public Integer getPoids(){
        if (profil == null){
            return null;
        } else {
            return profil.getiPoids();
        }
    }

    /**
     *
     * @return taille
     */
    public Integer getTaille(){
        if (profil == null){
            return null;
        } else {
            return profil.getiTaille();
        }
    }

    /**
     *
     * @return age
     */
    public Integer getAge(){
        if (profil == null){
            return null;
        } else {
            return profil.getiAge();
        }
    }

    /**
     *
     * @return sexe
     */
    public Integer getSexe(){
        if (profil == null){
            return null;
        } else {
            return profil.getiSexe();
        }
    }

    public static void setProfil(Profil profil) {
        Controle.profil = profil;
//        ((CalculActivity)context).recupProfil();
    }
}
