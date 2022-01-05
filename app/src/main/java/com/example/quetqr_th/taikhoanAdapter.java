package com.example.quetqr_th;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class taikhoanAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<taikhoan> taikhoanList;

    public taikhoanAdapter(Context context, int layout, List<taikhoan> taikhoanList) {
        this.context = context;
        this.layout = layout;
        this.taikhoanList = taikhoanList;
    }

    @Override
    public int getCount() {
        return taikhoanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtten;
        ImageView imagehinh;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtten = (TextView) view.findViewById(R.id.textviewname);
            holder.imagehinh = (ImageView) view.findViewById(R.id.imageviewanh);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();

        }

        taikhoan taiKhoan = taikhoanList.get(i);
        holder.txtten.setText(taiKhoan.getUsername());
        //chuyen byte sang bitmao
        byte[] hinhanh = taiKhoan.getAnh();
        if (hinhanh != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            holder.imagehinh.setImageBitmap(bitmap);
        }


        return view;
    }
}

