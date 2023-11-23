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

import com.example.had_store.DAO.HangSanPhamDao;
import com.example.had_store.Model.HangSanPham;
import com.example.had_store.R;

import java.util.ArrayList;

public class HangSanPhamAdapter extends RecyclerView.Adapter<HangSanPhamAdapter.viewholder> {
    private final Context context;
    private final ArrayList<HangSanPham> list;
    HangSanPhamDao hangSanPhamDao;

    public HangSanPhamAdapter(Context context, ArrayList<HangSanPham> list) {
        this.context = context;
        this.list = list;
        hangSanPhamDao = new HangSanPhamDao(context);
    }

    @NonNull
    @Override
    public HangSanPhamAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hangsanpham, null);

        return new viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HangSanPhamAdapter.viewholder holder, int position) {
        holder.tvMaHang.setText(String.valueOf(list.get(position).getMaHang()));
        holder.tvTenHang.setText(list.get(position).getTenHang());
        holder.tvDiaChiHang.setText(list.get(position).getDiaChiHang());
        HangSanPham hssp = list.get(position);

        holder.imgDeleteHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CẢNH BÁO");
                builder.setMessage("BẠN CÓ MUỐN XOÁ K");
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (hangSanPhamDao.delete(hssp.getMaHang())) {
                            list.clear();
                            list.addAll(hangSanPhamDao.selectAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "xoá thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "xoá thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "không có", Toast.LENGTH_SHORT).show();
                    }
                });

                // You need to call show() to display the AlertDialog
                builder.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewholder extends RecyclerView.ViewHolder{
    TextView tvMaHang,tvTenHang,tvDiaChiHang;
    ImageView imgDeleteHang;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvMaHang = itemView.findViewById(R.id.tvMaHang);
            tvTenHang = itemView.findViewById(R.id.tvTenHang);
            tvDiaChiHang = itemView.findViewById(R.id.tvDiaChiHang);
            imgDeleteHang = itemView.findViewById(R.id.imgDeleteHang);
        }
    }
}
