package mobileproject.incidentreport.helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mobileproject.incidentreport.Entities.Incident;
import mobileproject.incidentreport.R;

/**
 * Created by rettwalker on 11/8/15.
 */
public class IncidentAdapter extends ArrayAdapter<Incident> {
    private List<Address> addresses;
    private Geocoder geocoder;


    public IncidentAdapter(Context context, ArrayList<Incident> incidents){
        super(context,0,incidents);
         geocoder = new Geocoder(context, Locale.getDefault());

    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        Incident incident = getItem(pos);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_incident, parent, false);
        }
        if(!incident.getRespondOfficer_id().equals("true")){
            convertView.setBackgroundResource(R.color.aluminum);
        }

        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView address = (TextView) convertView.findViewById(R.id.incident_address);

        double lat = incident.getLat();
        double lng = incident.getLongit();
        String strAdd="";
        try {
            strAdd=getAddress(lat,lng);
        } catch (IOException e) {
            e.printStackTrace();
        }
        incident.setStreetAddress(strAdd);

        type.setText(incident.getType());
        address.setText(incident.getStreetAddress());

        //TextView username = (TextView) convertView.findViewById(R.id.firstLine);
        //username.setText(incident.getUsername());
        return convertView;
    }

    private String getAddress(double lat, double lng) throws IOException {
        addresses = geocoder.getFromLocation(lat, lng, 1);
         return addresses.get(0).getAddressLine(0)+"\n"+
                addresses.get(0).getLocality()+" "+addresses.get(0).getAdminArea()+
                " "+addresses.get(0).getPostalCode();

    }

}
