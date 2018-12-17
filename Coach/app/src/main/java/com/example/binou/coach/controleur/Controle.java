package com.example.binou.coach.controleur;

import android.content.Context;

import com.example.binou.coach.modele.AccesDistant;
import com.example.binou.coach.modele.AccesLocal;
import com.example.binou.coach.modele.Profil;
import com.example.binou.coach.vue.MainActivity;

import org.json.JSONArray;

import java.util.Date;

import static com.example.binou.coach.outils.Serializer.deSerialize;

public final class Controle {
    // instance unique de la classe
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofile";
//    public static AccesLocal accesLocal;
    public static AccesDistant accesDistant;
    public static Context context;

    // constructeur
    private Controle() {
        super();
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
            accesDistant.envoi("dernier", new JSONArray());
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

        profil = new Profil(new Date(), iPoids, iTaille, iAge, iSexe);
//        accesLocal.Ajouter(profil);
        accesDistant.envoi("enreg", profil.convertToJSONARRAY());
    }

    public static void recupSerialize(Context context){
        profil = (Profil)deSerialize(nomFic, context);

    }


    /**
     *
     * @return IMG
     */
    public float getImg(){
        return profil.getfImg();
    }

    /**
     *
     * @return message
     */
    public String getMsg(){
        return profil.getStrMsg();

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
        ((MainActivity)context).recupProfil();
    }
}
