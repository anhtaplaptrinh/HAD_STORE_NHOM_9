package com.example.had_store.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.had_store.Adapter.DonHangAdapter;
import com.example.had_store.Adapter.GioHangAdapter;
import com.example.had_store.DAO.DonHangDao;
import com.example.had_store.Model.DonHang;
import com.example.had_store.Model.GioHang;
import com.example.had_store.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Fragment_DonHang extends Fragment {

    private ListView lvDonHang;
    private ArrayList<DonHang> donHangList;
    private DonHangDao donHangDao;
    private DonHangAdapter donHangAdapter;
    private DonHang selectedDonHang;
    private SearchView timkiemDh;
    private final long INTERVAL_SECONDS = 10;
    private final long DELIVERY_TIME_SECONDS = 20;
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__don_hang, container, false);

        lvDonHang = view.findViewById(R.id.lvDonHang);
        donHangDao = new DonHangDao(getActivity());
        timkiemDh = view.findViewById(R.id.timkiemDh);

        timkiemDh.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                donHangAdapter.getFilter().filter(newText);
                return true;
            }
        });
        scheduleStatusUpdateTask();
        capNhatDonHangListView();



        return view;
    }

    private void scheduleStatusUpdateTask() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateStatusForPendingOrders();
                scheduleStatusUpdateTask();
            }
        }, TimeUnit.SECONDS.toMillis(INTERVAL_SECONDS));
    }

    private void updateStatusForPendingOrders() {

        List<DonHang> pendingOrders = donHangDao.getPendingOrders();

        for (DonHang order : pendingOrders) {
            if (isTimeToMoveToShipping(order.getNgayLap())) {
                if (!order.getTrangThaiDon().equals("Đang vận chuyển")) {
                    order.setTrangThaiDon("Đang vận chuyển");

                    donHangDao.update(order);
                }
            }
        }

        capNhatDonHangListView();
    }

    private boolean isTimeToMoveToShipping(Date ngayLap) {
        long currentTime = System.currentTimeMillis();
        long orderTime = ngayLap.getTime();
        long timeDifference = currentTime - orderTime;

        return timeDifference >= TimeUnit.SECONDS.toMillis(INTERVAL_SECONDS)
                && timeDifference < TimeUnit.SECONDS.toMillis(DELIVERY_TIME_SECONDS);
    }


    private void capNhatDonHangListView() {
        Bundle args = getArguments();
        if (args != null && args.containsKey("MA_KH")) {
            String maKh = args.getString("MA_KH");
            donHangList = new ArrayList<>(donHangDao.getDonHangsByMaKh(maKh));
            donHangAdapter = new DonHangAdapter(getActivity(), donHangList);
            lvDonHang.setAdapter(donHangAdapter);
        } else {
            donHangList = new ArrayList<>(donHangDao.getAllDonHang());
            donHangAdapter = new DonHangAdapter(getActivity(), donHangList);
            lvDonHang.setAdapter(donHangAdapter);
        }

    }


}
