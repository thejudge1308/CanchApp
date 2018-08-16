package com.example.patin.usuariocanchas.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.patin.usuariocanchas.Fragment.ContactFragment;
import com.example.patin.usuariocanchas.Fragment.NotifyFragment;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.example.patin.usuariocanchas.Fragment.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private LinearLayoutCompat content;
    String correoUser;
    String keyUser;
    Bundle bundle = new Bundle();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        /**
         * allows to change view using the buttom menu
         * @param item
         * @return current view to show
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setHomeViewFragment();
                    return true;
                case R.id.navigation_notifications:
                    setNotifyViewFragment();
                    return true;
                case R.id.navigation_contacts:
                    setContactViewFragment();
                    return  true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //linearLayout del contenido
         this.content = findViewById(R.id.content_linearlayout_homeactivity);
        //Menu de abajo
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Menu lateral
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View v = navigationView.getHeaderView(0);
        TextView usertext = v.findViewById(R.id.username_lateral_nav);
        usertext.setText(SingletonUser.getInstance().getNickname());

        //el correo  y la llave del usuario la pasare como parametro a cada fragment a traves de bunlde
        correoUser=SingletonUser.getInstance().getEmail();
        keyUser=SingletonUser.getInstance().getId();
        bundle.putString("correoUser",this.correoUser);
        bundle.putString("keyUser",keyUser);

        TextView scoretext = v.findViewById(R.id.calification_lateral_nav);
        scoretext.setText(SingletonUser.getInstance().getScore()+"");
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Listenners of lateral menu
     * @param item
     * @return activity
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            //Open my perfil activity
            Intent i =new Intent(HomeActivity.this,MyPerfilActivity.class);
            HomeActivity.this.startActivity(i);
        } else if (id == R.id.nav_history) {
            Intent i =new Intent(HomeActivity.this,MyHistoryActivity.class);
            HomeActivity.this.startActivity(i);
        } else if (id == R.id.nav_equipos) {
            Intent i =new Intent(HomeActivity.this,EquipoActivity.class);
            HomeActivity.this.startActivity(i);
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_close_sesion) {
            FirebaseAuth.getInstance().signOut();
            this.finish();
            Intent i = new Intent(HomeActivity.this,LoginActivity.class);
            HomeActivity.this.startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*private void getUserKey(){
        DatabaseReference user;
        user = FirebaseDatabase.getInstance().getReference("usuario");
        keyUser=user.child("email").equalTo(correoUser).;

    }*/

    private void setHomeViewFragment(){



        Fragment f;
        FragmentManager fm = getFragmentManager();
        //fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = fm.beginTransaction();
        f=new HomeFragment();
        f.setArguments(bundle);
        ft.replace(R.id.frangment_content,f);
        ft.disallowAddToBackStack();
        ft.commit();


    }

    private void setNotifyViewFragment(){
        Fragment f;
        FragmentManager fm = getFragmentManager();
        //fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = fm.beginTransaction();
        f=new NotifyFragment();
        f.setArguments(bundle);
        ft.replace(R.id.frangment_content,f);
        ft.disallowAddToBackStack();
        ft.commit();
    }

    private void setContactViewFragment(){
        Fragment f;
        FragmentManager fm = getFragmentManager();
        //fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = fm.beginTransaction();
        f=new ContactFragment();
        f.setArguments(bundle);
        ft.replace(R.id.frangment_content,f);
        ft.disallowAddToBackStack();
        ft.commit();
    }

}
