package com.example.had_store.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.had_store.Adapter.GioHangAdapter;
import com.example.had_store.DAO.GioHangDao;
import com.example.had_store.Model.GioHang;
import com.example.had_store.R;

import java.util.List;

public class Fragment_GioHang extends Fragment {

    private ListView lvGioHang;
    private GioHangAdapter gioHangAdapter;
    private GioHangDao gioHangDao;

    public Fragment_GioHang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__gio_hang, container, false);

        lvGioHang = view.findViewById(R.id.lvGioHang);

        gioHangDao = new GioHangDao(requireContext());
        gioHangDao.open();


        SharedPreferences preferences = requireContext().getSharedPreferences("user_prefs", requireContext().MODE_PRIVATE);
        String maKh = preferences.getString("maKh", "");

        if (!maKh.isEmpty()) {
            // If the user's ID is available, get the GioHang list for that user
            List<GioHang> gioHangList = gioHangDao.getGioHangsByMaKh(maKh);
            gioHangAdapter = new GioHangAdapter(requireContext(), gioHangList, maKh);
        } else {
            // If the user's ID is not available, get all GioHang items
            List<GioHang> gioHangList = gioHangDao.getAllGioHangs();
            gioHangAdapter = new GioHangAdapter(requireContext(), gioHangList,maKh);
        }
        lvGioHang.setAdapter(gioHangAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        gioHangDao.close(); // Close the database connection
    }


}
