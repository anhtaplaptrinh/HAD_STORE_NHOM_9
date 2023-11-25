package com.example.had_store.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.had_store.Adapter.SanPhamAdapter;
import com.example.had_store.DAO.SanPhamDao;
import com.example.had_store.Model.SanPham;
import com.example.had_store.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Fragment_SanPham extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton flb;
    ArrayList<SanPham> list = new ArrayList<>();
    SanPhamAdapter adapter;
    SanPhamDao sanPhamDao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__san_pham, container, false);
        recyclerView = view.findViewById(R.id.recviewsanpham);
        flb = view.findViewById(R.id.fabSanPham);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sanPhamDao = new SanPhamDao(getContext());
        list = (ArrayList<SanPham>) sanPhamDao.getAll();
        adapter = new SanPhamAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        flb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addsanpham();
            }
        });
        return  view;
    }
    public void  addsanpham(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view =inflater.inflate(R.layout.dialog_sanpham, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edMaSp = view.findViewById(R.id.edMaSp);
        EditText edTenSp = view.findViewById(R.id.edTenSp);
        EditText edGiaSp = view.findViewById(R.id.edGiaSp);
        EditText edSoLuongSp = view.findViewById(R.id.edSoLuongSp);
        EditText edMaHangSp = view.findViewById(R.id.edMaHangSp);
        EditText edAnhSp = view.findViewById(R.id.edAnhSp);
        Button btnSaveLS = view.findViewById(R.id.btnSaveLS);
        btnSaveLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String masp = edMaSp.getText().toString();
               String tensp = edTenSp.getText().toString();
               String giasp = edGiaSp.getText().toString();
               String soluongsp = edSoLuongSp.getText().toString();
               String mahangsp = edMaHangSp.getText().toString();
               String anhsp = edAnhSp.getText().toString();
               if (masp.trim().isEmpty() || tensp.trim().isEmpty() || giasp.trim().isEmpty() || soluongsp.trim().isEmpty() || mahangsp.trim().isEmpty() || anhsp.trim().isEmpty()){
                   Toast.makeText(getContext(), "k đc bỏ trống", Toast.LENGTH_SHORT).show();

               }else {
                   SanPham sp = new SanPham();
                   if (sanPhamDao.insert(sp)){
                       list.clear();
                       list.addAll(sanPhamDao.getAll());
                       adapter.notifyDataSetChanged();
                       dialog.dismiss();
                       Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                   }else {
                       Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                   }
               }
            }
        });
    }
}