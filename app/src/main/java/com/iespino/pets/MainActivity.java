package com.iespino.pets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.iespino.pets.Adapter.PageAdapter;
import com.iespino.pets.vistaFragments.Detalle;
import com.iespino.pets.vistaFragments.PerfilFragment;
import com.iespino.pets.vistaFragments.RecyclerViewFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private TextView txtbar;
    private Toolbar ActionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Bundle bundle;
    private SharedPreferences preferenciaID;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        bundle= getIntent().getExtras();
        if (bundle!= null)
        {
            preferenciaID = getSharedPreferences("IdSearch", Context.MODE_PRIVATE);
            editor = preferenciaID.edit();
            editor.putString("id",bundle.getString("id"));
            editor.commit();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar = (Toolbar) findViewById(R.id.ActionBar);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();

            if (ActionBar !=null)
            {
                setSupportActionBar(ActionBar);
            }
        getSupportActionBar().setIcon(R.drawable.cat_footprint_48);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        txtbar = (TextView) findViewById(R.id.txtbar);
        txtbar.setText("MIS MASCOTAS");

    }

    public void verDetalle(View v)
    {
        Intent abrir= new Intent(this,Detalle.class);
        startActivity(abrir);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.mContacto:
                Intent intent_contacto = new Intent(this, Contacto.class);
                startActivity(intent_contacto);
                break;

            case R.id.mAbout:
                Intent intent_about = new Intent(this,About.class);
                startActivity(intent_about);
                break;

            case R.id.mconfigCuenta:
                Intent intent_config_cuenta = new Intent(this,config_cuenta.class);
                startActivity(intent_config_cuenta);
                finish();
                break;

        }
                return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragments ()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    private void setUpViewPager()
    {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_profile);
    }




}