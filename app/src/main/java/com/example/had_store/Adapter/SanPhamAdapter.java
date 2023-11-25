package com.example.had_store.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.had_store.DAO.SanPhamDao;
import com.example.had_store.Model.SanPham;
import com.example.had_store.R;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.viewholder> {
    private final Context context;
    private final ArrayList<SanPham> list;
    SanPhamDao sanPhamDao;

    public SanPhamAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
        sanPhamDao = new SanPhamDao(context);
    }

    @NonNull
    @Override
    public SanPhamAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham, null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.viewholder holder, int position) {
        holder.tvMaSp.setText(String.valueOf(list.get(position).getMasp()));
        holder.tvTenSp.setText(list.get(position).getTenSp());
        holder.tvGiaSp.setText(String.valueOf(list.get(position).getGiaSp()));
        holder.tvMota.setText(list.get(position).getMota());
        holder.tvSoLuongSp.setText(String.valueOf(list.get(position).getSoLuongSp()));
        holder.imgAnhSp.setText(String.valueOf(list.get(position).getAnhSp()));
        holder.tvMaHang.setText(String.valueOf(list.get(position).getMaHang()));
        SanPham sp = list.get(position);
        holder.imgDeleteSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CẢNH BÁO");
                builder.setMessage("BẠN CÓ MUỐN XOÁ K");
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (sanPhamDao.delete(String.valueOf(sp.getMasp()))){
                            list.clear();
                            list.addAll(sanPhamDao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xoá ok", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Xoá k đc", Toast.LENGTH_SHORT).show();

                        }
                    }
                });builder.setNegativeButton("KHông", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "không có", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  class viewholder extends RecyclerView.ViewHolder{
        TextView tvMaSp,tvTenSp,tvGiaSp,tvSoLuongSp,tvMaHang,imgAnhSp,tvMota;
        ImageView imgDeleteSach;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvMaSp = itemView.findViewById(R.id.tvMaSp);
            tvTenSp = itemView.findViewById(R.id.tvTenSp);
            tvGiaSp = itemView.findViewById(R.id.tvGiaSp);
            tvSoLuongSp = itemView.findViewById(R.id.tvSoLuongSp);
            tvMaHang = itemView.findViewById(R.id.tvMaHang);
            imgAnhSp = itemView.findViewById(R.id.imgAnhSp);
            tvMota = itemView.findViewById(R.id.tvMota);
            imgDeleteSach = itemView.findViewById(R.id.imgDeleteSach);
        }
    }
}
