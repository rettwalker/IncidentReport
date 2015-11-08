package mobileproject.incidentreport.Activities;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import mobileproject.incidentreport.Entities.Officer;
import mobileproject.incidentreport.R;
import mobileproject.incidentreport.helpers.ConfigApp;
import mobileproject.incidentreport.helpers.OfficerAdapter;

public class OfficerList extends AppCompatActivity {
    private final String TAG = OfficerList.class.getSimpleName();
    private ArrayList<Officer> officers = new ArrayList<>();
    private OfficerAdapter officerArrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView officerlist = (ListView) findViewById(R.id.officerList);
        new getOfficers().execute();

        officerArrayAdapter = new OfficerAdapter(this, officers);
        officerlist.setAdapter(officerArrayAdapter);
        officerArrayAdapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }
    private class getOfficers extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(ConfigApp.database_url, ConfigApp.database_user, ConfigApp.database_pass);
                String queryString = "select * from tbl_officers";

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(queryString);

                while (rs.next()) {

                    Officer officer = new Officer();
                    officer.setOfficer_id(rs.getInt("officer_id"));
                    officer.setUsername(rs.getString("username"));
                    officers.add(officer);

                }
                con.close();

            }catch (Exception e){
                Log.e(TAG,e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            officerArrayAdapter.notifyDataSetChanged();
            Log.d(TAG,"DID the stuff");


        }
    }

}
