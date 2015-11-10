package mobileproject.incidentreport.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import mobileproject.incidentreport.Entities.Incident;
import mobileproject.incidentreport.R;
import mobileproject.incidentreport.helpers.ConfigApp;
import mobileproject.incidentreport.helpers.IncidentAdapter;


public class IncidentList extends AppCompatActivity {
    private final String TAG = OfficerList.class.getSimpleName();
    private ArrayList<Incident> incidents = new ArrayList<>();
    private IncidentAdapter incidentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView incidentlist = (ListView) findViewById(R.id.incidentList);
        new getIncidents().execute();

        incidentAdapter = new IncidentAdapter(this, incidents);
        incidentlist.setAdapter(incidentAdapter);
        incidentAdapter.notifyDataSetChanged();
        incidentlist.setClickable(true);
        incidentlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Incident incident =(Incident) incidentlist.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(),DispatchToOfficer.class);
                intent.putExtra("INCIDENT",incident);
                startActivity(intent);

            }
        });



    }

    private class getIncidents extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(ConfigApp.database_url, ConfigApp.database_user, ConfigApp.database_pass);
                String queryString = "SELECT tbl_incidents.*, cat_type, tbl_officer_responds_incident.*\n" +
                        "FROM tbl_incidents\n" +
                        "INNER JOIN tbl_incident_cat\n" +
                        "ON tbl_incidents.incident_id=tbl_incident_cat.incident_id\n" +
                        "INNER JOIN tbl_catogories\n" +
                        "ON tbl_incident_cat.catogories_id=tbl_catogories.catogories_id\n" +
                        "INNER JOIN tbl_officer_responds_incident\n" +
                        "ON tbl_incidents.incident_id=tbl_officer_responds_incident.incident_id;";

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(queryString);

                while (rs.next()) {
                    Incident incident = new Incident();
                    incident.setId(rs.getInt("incident_id"));
                    incident.setLongit(rs.getFloat("longitude"));
                    incident.setLat(rs.getFloat("latitude"));
                    incident.setDescription(rs.getString("description"));
                    incident.setType(rs.getString("cat_type"));
                    incident.setRespond_id(rs.getInt("respond_id"));
                    incident.setRespondOfficer_id(rs.getString("officer_id"));
                    if(rs.wasNull()) {
                        incident.setRespondOfficer_id("true");
                    }
                    incident.setRespondTimestamp(rs.getTimestamp("respondTime"));

                    incidents.add(incident);
                }
                con.close();

            }catch (Exception e){
                Log.e(TAG, e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            incidentAdapter.notifyDataSetChanged();
            Log.d(TAG,"DID the stuff");


        }
    }
}
