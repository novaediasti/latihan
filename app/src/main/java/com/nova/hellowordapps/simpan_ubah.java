package com.nova.hellowordapps;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class simpan_ubah {
    CatatanSQLHelper helper;
    SQLiteDatabase database;
    public Utils(Context ctx) {
        helper = new CatatanSQLHelper(ctx);
        database = helper.getWritableDatabase();
    }
    @SuppressWarnings("static-access")
    public ArrayList<Catatan_Fungsi> loadData() {
        ArrayList<Catatan_Fungsi> currentData = new
                ArrayList<Catatan_Fungsi>();
        Cursor dataCursor = database.query(helper.TASKS_TABLE,
                new String[] {helper.TASK_ID, helper.TASK_TANGGAL,
                        helper.TASK_JAM,
                        helper.TASK_CATATAN, helper.TASK_COMPLETE},
                null, null, null, null,
                String.format("%s, %s", helper.TASK_COMPLETE,
                        helper.TASK_TANGGAL));
        dataCursor.moveToFirst();
        Catatan_Fungsi t;
        if ( !dataCursor.isAfterLast() ) {
            do {
                int id = dataCursor.getInt(0); // coloum ID
                String tanggal = dataCursor.getString(1); // coloum
                tanggal
                String jam = dataCursor.getString(2); // coloum jam
                String catatan = dataCursor.getString(3); // coloum
                catatan
                String boolValue = dataCursor.getString(4); //
                coloum complete
                boolean complete = Boolean.parseBoolean(boolValue);
                t = new Catatan_Fungsi();
                t.setId(id);
                t.setTanggal(tanggal);
                t.setJam(jam);
                t.setCatatan(catatan);
                t.setComplete(complete);
                currentData.add(t);
            } while(dataCursor.moveToNext());
        }
/*
while (dataCursor.moveToNext()) {
}
*/
        dataCursor.close();
        return currentData;
    }
    @SuppressWarnings("static-access")
    public void onSaveData(Catatan_Fungsi getCust) {
        assert (null != getCust);
        ContentValues values = new ContentValues();
        values.put(helper.TASK_TANGGAL, getCust.getTanggal());
        values.put(helper.TASK_JAM, getCust.getJam());
        values.put(helper.TASK_CATATAN, getCust.getCatatan());
        values.put(helper.TASK_COMPLETE, Boolean.toString(false));
        getCust.setId(database.insert(helper.TASKS_TABLE, null,
                values));
    }
    @SuppressWarnings("static-access")
    public void onUpdateData(Catatan_Fungsi getCust) {
        assert (null != getCust);
        ContentValues values = new ContentValues();
        values.put(helper.TASK_TANGGAL, getCust.getTanggal());
        values.put(helper.TASK_JAM, getCust.getJam());
        values.put(helper.TASK_CATATAN, getCust.getCatatan());
        values.put(helper.TASK_COMPLETE,
                Boolean.toString(getCust.isComplete()));
        long id = getCust.getId();
        String where = String.format("%s = %d", helper.TASK_ID, id);
        database.update(helper.TASKS_TABLE, values, where, null);
    }
    AlertDialog alert;
    public void onShowData(Catatan_Fungsi cust, Context ctx) {
        final Catatan_Fungsi thisCust = cust;
        alert = new AlertDialog.Builder(ctx).setIcon(R.drawable.icon)
                .setTitle("Display Data")
                .setMessage(" ------------ Customer -------------\n"
                        + "ID: "+thisCust.getId()+"\n"
                        + "Tanggal: "+thisCust.getTanggal()+"\n"
                        + "Jam: "+thisCust.getJam()+"\n"
                        + "Catatan: "+thisCust.getCatatan()+"\n")
                .setNegativeButton("Close", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int
                                    whichButton) {
                                alert.cancel();
                            }
                        }).create();
        alert.show();
    }
}