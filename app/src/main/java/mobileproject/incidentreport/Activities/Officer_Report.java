package mobileproject.incidentreport.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import mobileproject.incidentreport.Entities.Incident;
import mobileproject.incidentreport.R;

public class Officer_Report extends AppCompatActivity {
    private final String TAG = Officer_Report.class.getSimpleName();
    private static int incident_id = 0;
    private Incident incident;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer__report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            if(intent.getStringExtra("from").equalsIgnoreCase("noti")){
                incident_id = intent.getIntExtra("incident_id",0);
                Log.i(TAG,"incident_id = "+incident_id);
            }else if(intent.getStringExtra("from").equalsIgnoreCase("inciList")) {
                Bundle temp = intent.getBundleExtra("incident");
                incident = (Incident) temp.getSerializable("incident");
                Log.i(TAG, "intent extras"+intent.getExtras().toString());
            }

        }

    }

}
