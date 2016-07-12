package com.iespino.pets.DB;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iespino.pets.pojo.Pets;
import com.iespino.pets.vistaFragments.RecyclerViewFragment;

import java.util.ArrayList;


public class BaseDatos extends SQLiteOpenHelper
{
    private  Context context;
    private String [] ids;


    public BaseDatos(Context context)
    {
        super(context, ConstantesDB.NOMBRE_DB, null, ConstantesDB.VERSION_DB);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String queryCreaTPETS = "create table "+ConstantesDB.NOMBRE_TPETS+" ("+ConstantesDB.TPETS_ID+" integer primary key unique," +
                ConstantesDB.TPETS_NOMBRE+" text, " +
                ConstantesDB.TPETS_LIKES+" integer, " +
                ConstantesDB.TPETS_FOTO+" integer) ";
        db.execSQL(queryCreaTPETS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST" + ConstantesDB.NOMBRE_TPETS);
        onCreate(db);

    }

    public ArrayList<Pets> getAllPets()
    {
        ArrayList<Pets> petsTotal = new ArrayList<>();
        String query ="select * from "+ConstantesDB.NOMBRE_TPETS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext())
        {
          /*  Pets petActual = new Pets();
            petActual.setId(registros.getInt(0));
            petActual.setNombre(registros.getString(1));
            petActual.setLikes(registros.getInt(2));
            petActual.setMascota(registros.getInt(3));

            petsTotal.add(petActual);*/
        }
        db.close();
        return petsTotal;
    }

    public void insertarPet(ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDB.NOMBRE_TPETS, null, contentValues);
        db.close();
    }


    public void updateLike(Pets pet)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ids= new String[]{String.valueOf(pet.getId())};
        db.update(ConstantesDB.NOMBRE_TPETS, obtenerPetLikeado(pet), ConstantesDB.TPETS_ID + " = ? ", ids);
        db.close();
    }

    private ContentValues obtenerPetLikeado(Pets pet){
        ContentValues petLikeado= new  ContentValues();
    //    petLikeado.put(ConstantesDB.TPETS_NOMBRE,pet.getNombre());
        petLikeado.put(ConstantesDB.TPETS_LIKES, pet.getLikes());
    //    petLikeado.put(ConstantesDB.TPETS_FOTO, pet.getMascota());

        return  petLikeado;
    }

    public void crearPreferencia(Context c)
    {
        SharedPreferences PreferenciaPets= c.getSharedPreferences("PreferenciasDBPets", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= PreferenciaPets.edit();
        String valor= "si";
        editor.putString("DB", valor);
        editor.commit();
    }

    public String cargarPreferencias(Context c)
    {
        RecyclerViewFragment rvf = new RecyclerViewFragment();

        SharedPreferences PreferenciaPets= c.getSharedPreferences("PreferenciasDBPets", Context.MODE_PRIVATE);
        rvf.statusPreferencia=PreferenciaPets.getString("DB", "no");

        String var = rvf.statusPreferencia;

        return var;

    }


    //OBTENER LOS PRIMEROS 5

    public  ArrayList <Pets> getTop()
    {
        ArrayList<Pets> topPets = new ArrayList<>();
        String query ="select * from "+ConstantesDB.NOMBRE_TPETS+" order by "+ConstantesDB.TPETS_LIKES+ " desc limit 5";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext())
        {
         /*   Pets petActual = new Pets();
            petActual.setId(registros.getInt(0));
            petActual.setNombre(registros.getString(1));
            petActual.setLikes(registros.getInt(2));
            petActual.setMascota(registros.getInt(3));

            topPets.add(petActual);*/
        }
        db.close();
        return topPets;
    }


}
