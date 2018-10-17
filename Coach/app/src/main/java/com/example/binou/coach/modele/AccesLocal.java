package com.example.binou.coach.modele;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;

import com.example.binou.coach.outils.MesOutils;
import com.example.binou.coach.outils.MySQLiteOpenHelper;

import java.sql.SQLData;
import java.util.Date;

import static com.example.binou.coach.outils.MesOutils.convertStringToDate;

/**
 *
 * Contrôle d'accès à la base de données locale
 */
public class AccesLocal {

    // propriétés
    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBd;
    private SQLiteDatabase bd;
    private String  req;

    public AccesLocal(Context context) {
        this.accesBd = new MySQLiteOpenHelper(context, nomBase, versionBase);
    }

    public void Ajout(Profil profil){
        this.bd = accesBd.getWritableDatabase();
        req = "insert into profil (dateMesure, poids, taille, age, sexe) values ";
        req += "(\""+profil.getDateMesure()+"\","+profil.getiPoids()+","+profil.getiTaille()+","+profil.getiAge()+","+profil.getiSexe()+")";
        bd.execSQL(req);
    }

    public Profil RecupDernier(){
        Profil profil = null;
        bd = accesBd.getReadableDatabase();
        req = "select * from profil";
        Cursor cursor = bd.rawQuery(req, null);
        Date date;
        Integer poids, taille, age, sexe;


        cursor.moveToLast();

        if (!cursor.isAfterLast()) {
            date = MesOutils.convertStringToDate(cursor.getString(0));
            poids = cursor.getInt(1);
            taille = cursor.getInt(2);
            age = cursor.getInt(3);
            sexe = cursor.getInt(4);

            profil = new Profil(date, poids, taille, age, sexe);
        }
        cursor.close();

        return profil;
    }

}

