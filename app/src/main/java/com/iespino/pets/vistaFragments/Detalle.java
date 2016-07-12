package com.iespino.pets.vistaFragments;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iespino.pets.Adapter.Adapter;
import com.iespino.pets.R;
import com.iespino.pets.pojo.Pets;
import com.iespino.pets.presentador.DetallePresenter;
import com.iespino.pets.presentador.IDetallePresenter;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;

public class Detalle extends AppCompatActivity implements IDetalle
{
    private TextView txtbar;
    private ImageView imgTop;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private IDetallePresenter presentador;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Toolbar ActionBar= (Toolbar)findViewById(R.id.ActionBar);
        setSupportActionBar(ActionBar);

        txtbar = (TextView) findViewById(R.id.txtbar);
        txtbar.setText("TOP MASCOTAS");

        imgTop = (ImageView) findViewById(R.id.imgtop);
        imgTop.setVisibility(View.INVISIBLE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.cat_footprint_48);

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.rvDetalle);
        recycler.setHasFixedSize(false);


       presentador = new DetallePresenter(Detalle.this,Detalle.this);


    }

    @Override
    public void generarLinearLayoutVertical()
    {
        //  recycler.setHasFixedSize(true)
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
    }

    @Override
    public Adapter crearAdaptador(ArrayList<Pets> items)
    {
        // Crear un nuevo adaptador
        Adapter adapter = new Adapter(items, this);
        return adapter;

    }

    @Override
    public void inicializarAdaptadorRV(Adapter adapter)
    {
        recycler.setAdapter(adapter);
    }


}
