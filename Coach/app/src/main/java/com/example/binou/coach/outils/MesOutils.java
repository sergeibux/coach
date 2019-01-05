package com.example.binou.coach.outils;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.binou.coach.vue.CalculActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MesOutils {
    public static Date convertStringToDate(String uneDate){
        String expectedPattern = "EEE MMM dd hh:mm:ss 'GMT+00:00' yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Make a date from a string
     * @param uneDate
     * @param expectedPattern
     * @return
     */
    public static Date convertStringToDate(String uneDate, String expectedPattern){
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Make a string from a date
     * @param uneDate
     * @return
     */
    public static String convertDateToString (Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(uneDate);

    }


    /**
     * Show/hide keyboard
     * @TODO fix bug on launching app there's no keyboard.
     * @param oh
     * @param activity
     */
    public static void Keyboard(String oh, CalculActivity activity){

            InputMethodManager inputMethodManager;
            inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();

            if (oh == "hide") {
                if (view != null) {
                    try{
                        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    } catch (Exception e){
                    }
                }
            } else {
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
            }
    }

    public static String formatToDecimal(Float f){
        return String.format("%.01f", f);
    }
}
