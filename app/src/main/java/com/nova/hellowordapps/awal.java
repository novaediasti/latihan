package com.nova.hellowordapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
public class awal extends Activity {
    Menu myMenu = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.awal);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    { super.onCreateOptionsMenu(menu);
        this.myMenu = menu;
        addRegularMenuItems(menu);
        return true;
    }
    private void addRegularMenuItems(Menu menu){
        int base=Menu.FIRST; // value is 1
        MenuItem pilih = menu.add(base,base,base,"CatatanKu");
        pilih.setIcon(R.drawable.catatan1);
        MenuItem info = menu.add(base,base+1,base+1,"Info");
        info.setIcon(R.drawable.info);
        MenuItem help = menu.add(base,base+2,base+2,"Help");
        help.setIcon(R.drawable.help);
        MenuItem exit = menu.add(base,base+3,base+3,"Exit");
        exit.setIcon(R.drawable.exit);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            startActivity(new Intent(awal.this,DatabaseApps.class));
        }
        else if (item.getItemId() == 2) {
            startActivity(new Intent(awal.this,info.class));
        }
        else if (item.getItemId() == 3) {
            startActivity(new Intent(awal.this,help.class));
        }
        else if (item.getItemId() == 4) {
            finish();
        }
        return true;
    }
}