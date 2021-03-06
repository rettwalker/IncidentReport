package mobileproject.incidentreport.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import mobileproject.incidentreport.R;
import mobileproject.incidentreport.helpers.ConfigApp;

public class MainActivity extends AppCompatActivity {
    private static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = this.getSharedPreferences(ConfigApp.USER_LOGIN_PREF, Context.MODE_PRIVATE);
        Intent intent;
        if(sharedPreferences.getBoolean("isLoggedIn",false)){
            if(sharedPreferences.getString("TYPE",null).equals("user")){
                intent = new Intent(this,User_Menu.class);

            }else{
                if(sharedPreferences.getString("TYPE",null).equals("dispatch")){
                    intent = new Intent(this,IncidentList.class);

                }else{
                    intent = new Intent(this,Officer_Menu.class);
                }
            }
        }else{
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);



        finish();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void toUserMenu(View view){
        Intent intent = new Intent(this, User_Menu.class);
        startActivity(intent);
    }
    public void toOfficerMenu(View view){
        Intent intent = new Intent(this, Officer_Menu.class);
        startActivity(intent);
    }
}
