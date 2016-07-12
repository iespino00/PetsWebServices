package com.iespino.pets.presentador;


import android.content.Context;

import com.iespino.pets.Adapter.Adapter;
import com.iespino.pets.DB.BaseDatos;
import com.iespino.pets.DB.ConstructorPets;
import com.iespino.pets.pojo.Pets;
import com.iespino.pets.vistaFragments.IDetalle;


import java.util.ArrayList;

public class DetallePresenter implements IDetallePresenter
{
    private IDetalle iDetalle;
    private  Context context;
    private ConstructorPets constructorPets;
    private ArrayList<Pets> items;
    private BaseDatos db;

    public DetallePresenter (IDetalle iDetalle, Context context)
    {
        this.iDetalle=iDetalle;
        this.context=context;

        top();

    }


    @Override
    public void mostrarMascotasRV()
    {
        iDetalle.inicializarAdaptadorRV(iDetalle.crearAdaptador(items));
        iDetalle.generarLinearLayoutVertical();
    }

    @Override
    public void top()
    {
        db = new BaseDatos(context);
        items = db.getTop();
        mostrarMascotasRV();
    }

}
