package com.iespino.pets.DB;


import android.content.ContentValues;
import android.content.Context;

import com.iespino.pets.R;
import com.iespino.pets.pojo.Pets;

import java.util.ArrayList;

public class ConstructorPets
{
    private Context context;
    public ConstructorPets(Context context)
    {
        this.context=context;
    }

    public ArrayList<Pets> obtenerDatos()
    { /*// Inicializar Pets
        ArrayList<Pets> items = new ArrayList<>();
        items.add(new Pets(R.drawable.pet1, "Speedy", 1));
        items.add(new Pets(R.drawable.pet2, "Pluto", 2));
        items.add(new Pets(R.drawable.pet3, "Doggy", 3));
        items.add(new Pets(R.drawable.pet4, "Hamsty", 4));
        items.add(new Pets(R.drawable.pet5, "Leoni", 5));
        items.add(new Pets(R.drawable.pet6, "TumTum", 6));
        items.add(new Pets(R.drawable.pet7, "Doberto",7));
       return items;*/

        BaseDatos db = new BaseDatos(context);
        cargaIniciarlPets(db);
        return db.getAllPets();
    }

    public void cargaIniciarlPets(BaseDatos db)
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TPETS_NOMBRE, "Speedy");
        contentValues.put(ConstantesDB.TPETS_LIKES, 0);
        contentValues.put(ConstantesDB.TPETS_FOTO, R.drawable.pet1);
        db.insertarPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TPETS_NOMBRE, "Pluto");
        contentValues.put(ConstantesDB.TPETS_LIKES, 0);
        contentValues.put(ConstantesDB.TPETS_FOTO, R.drawable.pet2);
        db.insertarPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TPETS_NOMBRE, "Doggy");
        contentValues.put(ConstantesDB.TPETS_LIKES, 0);
        contentValues.put(ConstantesDB.TPETS_FOTO, R.drawable.pet3);
        db.insertarPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TPETS_NOMBRE, "Hamsty");
        contentValues.put(ConstantesDB.TPETS_LIKES, 0);
        contentValues.put(ConstantesDB.TPETS_FOTO, R.drawable.pet4);
        db.insertarPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TPETS_NOMBRE, "Leoni");
        contentValues.put(ConstantesDB.TPETS_LIKES, 0);
        contentValues.put(ConstantesDB.TPETS_FOTO, R.drawable.pet5);
        db.insertarPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TPETS_NOMBRE, "TumTum");
        contentValues.put(ConstantesDB.TPETS_LIKES, 0);
        contentValues.put(ConstantesDB.TPETS_FOTO, R.drawable.pet6);
        db.insertarPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TPETS_NOMBRE, "Doberto");
        contentValues.put(ConstantesDB.TPETS_LIKES, 0);
        contentValues.put(ConstantesDB.TPETS_FOTO, R.drawable.pet7);
        db.insertarPet(contentValues);


    }
}
