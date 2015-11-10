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
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParsePush;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import mobileproject.incidentreport.Entities.Incident;
import mobileproject.incidentreport.Entities.Officer;
import mobileproject.incidentreport.R;
import mobileproject.incidentreport.helpers.ConfigApp;
import mobileproject.incidentreport.helpers.OfficerAdapter;

public class DispatchToOfficer extends AppCompatActivity {
    private final String TAG = DispatchToOfficer.class.getSimpleName();
    private ArrayList<Officer> officers = new ArrayList<>();
    private OfficerAdapter officerArrayAdapter;
    private Incident incident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_to_officer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setIncident();
        final ListView officerlist = (ListView) findViewById(R.id.disToOffList);
        new getOfficers().execute();

        officerArrayAdapter = new OfficerAdapter(this, officers);
        officerlist.setAdapter(officerArrayAdapter);
        officerArrayAdapter.notifyDataSetChanged();
        officerlist.setClickable(true);
        officerlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Officer officer = (Officer) officerlist.getItemAtPosition(position);
                ParsePush push = new ParsePush();
                push.setChannel(officer.getUsername());
                JSONObject incidentOb = null;
                JSONObject incidentUs = null;
                try {
                    incidentOb = new JSONObject().put("incident_id", incident.getId())
                    .put("type","toOfficer")
                    .put("strAddress",incident.getStreetAddress())
                    .put("catType",incident.getType());
                    incidentUs = new JSONObject().put("type", "toUser");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                push.setData(incidentOb);
                //push.sendInBackground();
                ParsePush userPush = new ParsePush();
                userPush.setChannel(incident.getUsername());
                userPush.setData(incidentUs);
                //userPush.sendInBackground();
                Toast notifyOfficer = Toast.makeText(getBaseContext(), "Officer Notified and User Notified" , Toast.LENGTH_LONG);
                notifyOfficer.show();
                finish();

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setIncident(){
        TextView type = (TextView) findViewById(R.id.type);
        TextView address = (TextView) findViewById(R.id.incident_address);
        Intent intent = getIntent();
        incident = (Incident) intent.getExtras().getSerializable("INCIDENT");
        address.setText(incident.getStreetAddress());
        type.setText(incident.getType());

    }

    private class getOfficers extends AsyncTask<Void, Void, Void> {

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
                //query=" INSERT INTO tbl_officer_responds_incident VALUES(NULL,NULL,'"+current_incident.getId()+",NULL);";
                //st.executeUpdate(query);
                con.close();

            }catch (Exception e){
                Log.e(TAG, e.toString());
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
