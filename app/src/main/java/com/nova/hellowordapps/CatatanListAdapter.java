package com.nova.hellowordapps;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
public class CatatanListAdapter extends BaseAdapter {
    ArrayList<Catatan_Fungsi> cust;
    Context context;
    public CatatanListAdapter(Context context, ArrayList<Catatan_Fungsi>
            custs) {
        super();
        this.cust = custs;
        this.context = context;
    }
    public int getCount() {
        return cust.size();
    }
    public Catatan_Fungsi getItem(int position) {
        return (null == cust) ? null : cust.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHolder {
        public CheckedTextView tanggalView;
        public TextView idView;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        View vi = convertView;
        if (null == convertView) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = infalInflater.inflate(R.layout.listitem, null);
            holder = new ViewHolder();
            holder.tanggalView = (CheckedTextView)
                    vi.findViewById(R.id.txt_tanggal);
            holder.idView = (TextView)
                    vi.findViewById(R.id.txt_id);
            vi.setTag(holder);
        }
        else
            holder = (ViewHolder) vi.getTag();
        String txtTanggal =
                cust.get(position).getTanggal()+"_"+cust.get(position).getJam();
        String txtId = String.valueOf(cust.get(position).getId());
        boolean check = cust.get(position).isComplete();
        holder.tanggalView.setText(txtTanggal);
        holder.tanggalView.setChecked(check);
        holder.idView.setText(txtId);
        return vi;
    }
    public void forceReload() {
        notifyDataSetChanged();
    }
    public void toggleDataCompleteAtPosition(int position) {
        Catatan_Fungsi cust = getItem(position);
        cust.toggleComplete();
        notifyDataSetChanged();
    }
    public Long[] removeCheckedCatatan() {
        ArrayList<Catatan_Fungsi> completedTasks = new
                ArrayList<Catatan_Fungsi>();
        ArrayList<Long> completedIds = new ArrayList<Long>();
        for (Catatan_Fungsi dtCust : cust) {
            if (dtCust.isComplete()) {
                completedTasks.add(dtCust);
                completedIds.add(dtCust.getId());
            }
        }
        cust.removeAll(completedTasks);
        notifyDataSetChanged();
        return completedIds.toArray(new Long[]{});
    }
    public Catatan_Fungsi getCheckedCatatan() {
        Catatan_Fungsi newCust = new Catatan_Fungsi();
        for (Catatan_Fungsi dtCust : cust) {
            if (dtCust.isComplete()) {
                newCust = dtCust; break;
            }
        }
        return newCust;
    }
}