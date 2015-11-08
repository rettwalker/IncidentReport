package mobileproject.incidentreport.Entities;

import android.net.Uri;

import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by rettwalker on 10/29/15.
 */
public class Incident implements Serializable {
    private double lat;
    private double longit;
    private Timestamp timestamp;
    private String type;
    private String description;
    private Uri fileUri;
    private int userId;
    private int id;
    private String streetAddress;
    private int respond_id;
    private String respondOfficer_id;
    private Timestamp respondTimestamp;

    public Incident(){

    }



    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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

    public void setTimestamp(Timestamp timestamp){
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
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

    public int getRespond_id() {
        return respond_id;
    }

    public void setRespond_id(int respond_id) {
        this.respond_id = respond_id;
    }

    public String getRespondOfficer_id() {
        return respondOfficer_id;
    }

    public void setRespondOfficer_id(String respondOfficer_id) {
        this.respondOfficer_id = respondOfficer_id;
    }

    public Timestamp getRespondTimestamp() {
        return respondTimestamp;
    }

    public void setRespondTimestamp(Timestamp respondTimestamp) {
        this.respondTimestamp = respondTimestamp;
    }
}
