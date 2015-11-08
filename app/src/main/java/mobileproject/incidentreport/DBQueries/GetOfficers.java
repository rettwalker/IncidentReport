package mobileproject.incidentreport.DBQueries;


import android.os.AsyncTask;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import mobileproject.incidentreport.Entities.Officer;

import mobileproject.incidentreport.helpers.ConfigApp;


/**
 * Created by rettwalker on 11/8/15.
 */

public class GetOfficers extends AsyncTask<Void, Void, Void> {
    private ArrayList<Officer> officers = new ArrayList<>();

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

            }
            return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }
}

