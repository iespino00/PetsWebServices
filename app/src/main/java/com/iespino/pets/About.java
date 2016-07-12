package com.iespino.pets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends AppCompatActivity
{
    private TextView txtbar;
    ImageView imgTop;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        txtbar=(TextView) findViewById(R.id.txtbar);
        txtbar.setText("Acerca de");

        imgTop = (ImageView) findViewById(R.id.imgtop);
        imgTop.setVisibility(View.INVISIBLE);



        Toolbar ActionBar= (Toolbar)findViewById(R.id.ActionBar);
        setSupportActionBar(ActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.cat_footprint_48);
    }


}
