package com.example.had_store.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.had_store.DAO.SanPhamDao;
import com.example.had_store.Model.SanPham;
import com.example.had_store.R;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private List<SanPham> sanPhamList1;
    private List<SanPham> sanPhamList2;

    public SanPhamAdapter(@NonNull Context context, List<SanPham> sanPhamList) {
        super(context, 0,sanPhamList);
        this.sanPhamList1 =new ArrayList<>(sanPhamList);
        this.sanPhamList2 = new ArrayList<>(sanPhamList);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sanpham,parent,false);
        }
        ImageView imgDeleteSach = convertView.findViewById(R.id.imgDeleteSach);
        imgDeleteSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sanPhamdelete = getItem(position);
                handleDeleteSanPham(sanPhamdelete);
            }

            private void handleDeleteSanPham( final  SanPham sanPhamdelete) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("DELETE!");
                builder.setMessage("Bạn có muốn xoá k");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteSanPham(sanPhamdelete);
                    }

                    private void deleteSanPham(SanPham sanPham) {
                        SanPhamDao sanPhamDao = new SanPhamDao(getContext());
                        int relust = sanPhamDao.delete(sanPham.getTenSp());
                        if (relust > 0){
                            remove(sanPham);
                            notifyDataSetChanged();
                            Toast.makeText(getContext(), "Xoá Thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Xoá Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        SanPham sanPham = getItem(position);
        if (sanPham != null){
            TextView tvMaSp = convertView.findViewById(R.id.tvMaSp);
            TextView tvTenSp = convertView.findViewById(R.id.tvTenSp);
            TextView tvGiaSp = convertView.findViewById(R.id.tvGiaSp);
            TextView tvSoLuongSp = convertView.findViewById(R.id.tvSoLuongSp);
            TextView tvMaHang = convertView.findViewById(R.id.tvMaHang);

            tvMaSp.setText(String.valueOf("ID" +sanPham.getMasp()));
            tvTenSp.setText(sanPham.getTenSp());
            tvGiaSp.setText(sanPham.getGiaSp());
            tvSoLuongSp.setText(sanPham.getSoLuongSp());
            tvMaHang.setText(sanPham.getMaHang());

        }
        return convertView;
    }

}
