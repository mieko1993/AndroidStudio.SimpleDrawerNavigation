package com.example.miekonagatome.drawernavigation;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    //Variables globales
    private String[] opciones;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private CharSequence tituloDeLaSeccion;
    private CharSequence tituloDeLaApp;
    private android.support.v4.app.ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        opciones = new String[]{"Opcion1", "Opcion 2", "Opcion 3", "Opcion 4"};
        drawerLayout = (DrawerLayout) findViewById(R.id.menuPrincipal);
        listView = (ListView) findViewById(R.id.menuIzquierdo);

        //Adaptador
        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));

        //Acomodar fragmentos
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                if (position == 0) {
                    fragment = new Fragmento1();
                }
                if (position == 1) {
                    fragment = new Fragmento2();
                }
                if (position == 2) {
                    fragment = new Fragmento3();
                }
                if (position == 3) {
                    fragment = new Fragmento4();
                }

                //Cargar fragmentos
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.menuFragmentos, fragment).commit();

                //Cuando se presione cualquier opcion del menu cambiar titulos
                listView.setItemChecked(position, true);
                tituloDeLaSeccion = opciones[position];
                getSupportActionBar().setTitle(tituloDeLaSeccion);
                drawerLayout.closeDrawer(listView);

            }
        });

        //Referencia de los titulos
        tituloDeLaSeccion = getTitle();
        tituloDeLaApp = getTitle();

        drawerToggle=new android.support.v4.app.ActionBarDrawerToggle(this,
                drawerLayout,R.drawable.ic_action_navigation_menu,
                R.string.abierto,R.string.cerrado){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ActivityCompat.invalidateOptionsMenu(MainActivity.this);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ActivityCompat.invalidateOptionsMenu(MainActivity.this);
            }
        };
        //resetear cuando se presionan los diferentes menus
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle saveInstanceState) {
        super.onPostCreate(saveInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
