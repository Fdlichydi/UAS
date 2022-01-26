package com.example.uas_fadli_19100104;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<ModelData>arrayList;
    private Object TextView;

    public ItemAdapter(Context context, ArrayList<ModelData>arrayList){
        super();
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount(){
        return  this.arrayList.size();
    }
    @Override
    public Object getItem(int position){
        return arrayList.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @SuppressLint({"ViewHolder","InflateParams"})
    @Override
    public  View getView(int position, View convertView,final ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert  layoutInflater!=null;
        convertView=layoutInflater.inflate(R.layout.daftar_todo,null);
        TextView nama; TextView=convertView.findViewById(R.id.title);
        TextView tanggal; TextView=convertView.findViewById(R.id.dateTitle);
        TextView waktu; TextView=convertView.findViewById(R.id.timeTitle);
        final ImageView dellImageView=convertView.findViewById(R.id.delete);
        dellImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int pos=(int) view.getTag();
                deleteItem (pos);
            }

            private void deleteItem(int pos) {
            }
        });

        ModelData modelData =arrayList.get(position);
        BreakIterator titleTextView = null;
        titleTextView.setText(modelData.getTitle());
        BreakIterator dateTextView = null;
        dateTextView.setText(modelData.getDate());
        BreakIterator timeTextView = null;
        timeTextView.setText(modelData.getTime());
        return convertView;
    }
    //menghapus tugas dari database
    private void deleteItemFromDb(int id){
        DatabaseHelper databaseHelper=new DatabaseHelper(context);
        try {
            databaseHelper.deleteData(id);
            toastMsg("Tugas dihapus");
        } catch (Exception e){
            e.printStackTrace();
            toastMsg("Oppsss... Ada kesalahan saat menghapus");
        }
    }
    //metode pesan toast
    private void toastMsg(String msg){
        Toast t=Toast.makeText(context,msg, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }
}


