package com.example.had_store.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.had_store.Adapter.SanPhamAdapter;
import com.example.had_store.DAO.SanPhamDao;
import com.example.had_store.Model.SanPham;
import com.example.had_store.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Fragment_SanPham extends Fragment {
    private ListView lvSanPham;
    private ArrayList<SanPham> sanPhamArrayList;
    private SanPhamDao sanPhamDao;
    private SanPhamAdapter sanPhamAdapter;
    private SanPham sanPham;
    private FloatingActionButton fabSanPham;
    private Handler handler = new Handler();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view =  inflater.inflate(R.layout.fragment__san_pham, container, false);
      lvSanPham = view.findViewById(R.id.lvSanPham);
      fabSanPham = view.findViewById(R.id.fabSanPham);
      sanPhamDao = new SanPhamDao(getActivity());
    fabSanPham.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            add();
        }
    });
      return view;
    }
    public void add(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view =inflater.inflate(R.layout.dialog_sanpham, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edMaSp =view.findViewById(R.id.edMaSp);
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
                    Toast.makeText(getContext(), "K đc bỏ trống", Toast.LENGTH_SHORT).show();

                }else {
//                    SanPham sp = new SanPham(masp,tensp,giasp,soluongsp,mahangsp,anhsp);
                }
            }
        });
    }
}