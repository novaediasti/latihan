package com.nova.hellowordapps;

import android.app.Activity;
public class Catatan_Fungsi extends Activity{
    long id;
    String tanggal;
    String jam;
    String catatan;
    boolean complete;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTanggal() {
        return tanggal;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public String getJam() {
        return jam;
    }
    public void setJam(String jam) {
        this.jam = jam;
    }
    public String getCatatan() {
        return catatan;
    }
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
    public boolean isComplete() {
        return complete;
    }
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    // -------------------------------------------
    public void toggleComplete() {
        complete = !complete;
    }
}











