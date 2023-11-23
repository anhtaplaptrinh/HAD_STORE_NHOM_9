package com.example.had_store.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return view;
    }
}