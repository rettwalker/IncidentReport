package mobileproject.incidentreport.Entities;

import android.net.Uri;

import org.json.JSONObject;

/**
 * Created by rettwalker on 10/29/15.
 */
public class Incident {
    private double lat;
    private double longit;
    private String timestamp;
    private String type;
    private String description;
    private Uri fileUri;
    private int userId;
    private int id;

    public Incident(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }
    public void setLongit(double longit){
        this.longit = longit;
    }

    public double getLongit() {
        return longit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setFileUri(Uri fileUri) {
        this.fileUri = this.fileUri;
    }

    public Uri getFileUri() {
        return fileUri;
    }

    public int getUserId() {
        return userId;
    }

    public void setUsername(int userId) {
        this.userId = userId;
    }
}
