package com.nova.hellowordapps;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
public class DatabaseApps extends Activity {
    ArrayList<Catatan_Fungsi> currentData;
    SQLiteDatabase database;
    CatatanListAdapter adapter;
    ListView list;
    CatatanSQLHelper helper;
    Catatan_Fungsi cust;
    Button btnSubmit, btnCancel;
    TextView txtTitle;
    EditText dtTanggal, dtJam, dtCatatan;
    simpan_ubah util;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        util = new simpan_ubah(this);
        list = (ListView) findViewById(R.id.list_data);
        CatatanSQLHelper helper = new CatatanSQLHelper(this);
        database = helper.getWritableDatabase();
        currentData = new ArrayList<Catatan_Fungsi>();
// ---- load data ----
        currentData = util.loadData();
        adapter = new CatatanListAdapter(this, currentData);
        list.setAdapter(adapter);
        list.setEmptyView(findViewById(R.id.list_empty));
        list.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                adapter.toggleDataCompleteAtPosition(position);
            }
        });
        list.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View v,
                                           int position, long id) {
                Catatan_Fungsi c = adapter.getItem(position);
                util.onShowData(c, DatabaseApps.this);
                return false;
            }
        });
// set button click
        onButtonClick();
    }
    // ----------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        adapter.forceReload();
    }
    // -----------------------------------------------
    public void onButtonClick() {
        Button btnAdd = (Button) findViewById(R.id.add_button);
        btnAdd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                onCreateWidgetData(1, new Catatan_Fungsi()); }
        });
        Button btnUpdate = (Button) findViewById(R.id.update_button);
        btnUpdate.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Catatan_Fungsi c = adapter.getCheckedCatatan();
                if (!c.getTanggal().equals(""))
                    onCreateWidgetData(2, c);
                else {
                    Toast.makeText(DatabaseApps.this, "Harus centang
                            satu",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        Button btnDelete = (Button) findViewById(R.id.delete_button);
        btnDelete.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Catatan_Fungsi c = adapter.getCheckedCatatan();
                onDeleteData(c.getId());
            }
        });
        Button btnExit = (Button) findViewById(R.id.exit_button);
        btnExit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
    public void onCreateWidgetData(int param, final Catatan_Fungsi getCust)
    {
        switch(param) {
// add data new
            case 1:
                widgetAdd();
                break;
// update existing data
            case 2:
                widgetUpdate(getCust);
                break;
        }
    }
    public void widgetAdd() {
        setContentView(R.layout.inputdata);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtTitle.setText("Tambah Catatan");
        btnSubmit = (Button) findViewById(R.id.submit_button);
        btnSubmit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dtTanggal = (EditText) findViewById(R.id.data_tanggal);
                dtJam = (EditText) findViewById(R.id.data_jam);
                dtCatatan = (EditText) findViewById(R.id.data_catatan);
                if (dtTanggal.getText().length()<1
                        || dtJam.getText().length()<1
                        || dtCatatan.getText().length()<1) {
                    Toast.makeText(DatabaseApps.this, "Periksa Masukan
                            Data",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    cust = new Catatan_Fungsi();
                    cust.setTanggal(dtTanggal.getText().toString());
                    cust.setJam(dtJam.getText().toString());
                    cust.setCatatan(dtCatatan.getText().toString());
                    cust.setComplete(false);
                    util.onSaveData(cust);
                    onCancel();
                }
            }
        });
        btnCancel = (Button) findViewById(R.id.cancel_button);
        btnCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                onCancel();
            }
        });
    }
    public void widgetUpdate(final Catatan_Fungsi getCust) {
        setContentView(R.layout.inputdata);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtTitle.setText("Ubah Catatan");
        dtTanggal = (EditText) findViewById(R.id.data_tanggal);
        dtTanggal.setText(getCust.getTanggal());
        dtJam = (EditText) findViewById(R.id.data_jam);
        dtJam.setText(getCust.getJam());
        dtCatatan = (EditText) findViewById(R.id.data_catatan);
        dtCatatan.setText(getCust.getCatatan());
        btnSubmit = (Button) findViewById(R.id.submit_button);
        btnSubmit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dtTanggal = (EditText) findViewById(R.id.data_tanggal);
                dtJam = (EditText) findViewById(R.id.data_jam);
                dtCatatan = (EditText) findViewById(R.id.data_catatan);
                if (dtTanggal.getText().length()<1
                        || dtJam.getText().length()<1
                        || dtCatatan.getText().length()<1) {
                    Toast.makeText(DatabaseApps.this, "Periksa Masukan
                            Data", Toast.LENGTH_SHORT);
                }
                else {
                    getCust.setTanggal(dtTanggal.getText().toString());
                    getCust.setJam(dtJam.getText().toString());
                    getCust.setCatatan(dtCatatan.getText().toString());
                    util.onUpdateData(getCust); onCancel();
                }
            }
        });
        btnCancel = (Button) findViewById(R.id.cancel_button);
        btnCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                onCancel();
            }
        });
    }
    public void onDeleteData(long id) {
// Long[] ids = adapter.removeCheckedCustomer();
// deleteData(ids);
        deleteData(new Long[]{id});
        currentData = util.loadData();
        adapter = new CatatanListAdapter(this, currentData);
        list.setAdapter(adapter);
    }
    @SuppressWarnings("static-access")
    public void deleteData(Long[] ids) {
        StringBuffer idList = new StringBuffer();
        for (int i =0; i< ids.length; i++) {
            idList.append(ids[i]);
            if (i < ids.length -1 ) {
                idList.append(",");
            }
        }
        String where = String.format("%s in (%s)", helper.TASK_ID,
                idList);
        database.delete(helper.TASKS_TABLE, where, null);
    }
    public void onCancel() {
        Intent newIntent = new Intent().setClass(DatabaseApps.this,
                DatabaseApps.class);
        startActivity(newIntent);
        finish();
    }
// -----------------------------------------------
}