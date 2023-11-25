package com.example.had_store.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
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

import com.example.had_store.Adapter.HangSanPhamAdapter;
import com.example.had_store.DAO.HangSanPhamDao;
import com.example.had_store.Model.HangSanPham;
import com.example.had_store.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_HangSanPham extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ArrayList<HangSanPham> list = new ArrayList<>();
    HangSanPhamAdapter adapter;
    HangSanPhamDao hspDao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment__hang_san_pham, container, false);
        recyclerView = view.findViewById(R.id.recviewhangsanpham);
        floatingActionButton =view.findViewById(R.id.fabHangSanPham);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        hspDao = new HangSanPhamDao(getContext());
        list =hspDao.selectAll();
        adapter = new HangSanPhamAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        return view;
    }
    public void  add(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view =inflater.inflate(R.layout.dialog_hangsanpham, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edMaHang = view.findViewById(R.id.edMaHang);
        EditText edTenHang = view.findViewById(R.id.edTenHang);
        EditText edDiaChiHang = view.findViewById(R.id.edDiaChiHang);
        EditText edAnhHang = view.findViewById(R.id.edAnhHang);
        Button btnSaveLS = view.findViewById(R.id.btnSaveLS);
        btnSaveLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maHang = edMaHang.getText().toString();
                String tenhang = edTenHang.getText().toString();
                String diachihang = edDiaChiHang.getText().toString();
                String anhHang = edAnhHang.getText().toString();
                if (maHang.trim().isEmpty() || tenhang.trim().isEmpty() || diachihang.trim().isEmpty() || anhHang.trim().isEmpty() ){
                    Toast.makeText(getContext(), "k đc bỏ trống", Toast.LENGTH_SHORT).show();

                }else {
                    HangSanPham hspp = new HangSanPham();
                    if (hspDao.insert(hspp)) {
                        list.clear();
                        list.addAll(hspDao.selectAll());
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