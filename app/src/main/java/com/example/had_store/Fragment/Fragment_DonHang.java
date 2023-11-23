package com.example.had_store.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.had_store.Adapter.DonHangAdapter;
import com.example.had_store.DAO.DonHangDao;
import com.example.had_store.Model.DonHang;
import com.example.had_store.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment_DonHang extends Fragment {

    private ListView lvDonHang;
    private ArrayList<DonHang> donHangList;
    private DonHangDao donHangDao;
    private DonHangAdapter donHangAdapter;
    private DonHang selectedDonHang;
    private FloatingActionButton fabDonHang;
    private SearchView timkiemDh;
    private final int INTERVAL = 10000; // 10 minutes in milliseconds
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__don_hang, container, false);

        lvDonHang = view.findViewById(R.id.lvDonHang);
        fabDonHang = view.findViewById(R.id.fabDonHang);
        donHangDao = new DonHangDao(getActivity());
        timkiemDh = view.findViewById(R.id.timkiemDh);

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

        capNhatDonHangListView();
        scheduleStatusUpdateTask();

        fabDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDonHangDialog(0, null);
            }
        });

        lvDonHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDonHang = donHangList.get(i);
                openDonHangDialog(1, selectedDonHang);
            }
        });

        return view;
    }

    private void scheduleStatusUpdateTask() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateStatusForPendingOrders();
                scheduleStatusUpdateTask();
            }
        }, INTERVAL);
    }

    private void updateStatusForPendingOrders() {
        List<DonHang> pendingOrders = donHangDao.getPendingOrders();

        for (DonHang order : pendingOrders) {
            if (isTimeToMoveToShipping(order.getNgayLap())) {
                if ("Vận chuyển tận nơi".equals(order.getpTVanChuyen())) {
                    // If delivery method is "Tận nơi", update status to "Đang Vận Chuyển"
                    order.setTrangThaiDon("Đang Vận Chuyển");
                } else {
                    // If delivery method is not "Tận nơi", update status to "Đã Đóng Gói"
                    order.setTrangThaiDon("Đã Đóng Gói");
                }
                donHangDao.update(order);
            }
        }

        capNhatDonHangListView();
    }

    private boolean isTimeToMoveToShipping(Date ngayLap) {
        long currentTime = System.currentTimeMillis();
        long orderTime = ngayLap.getTime();
        long timeDifference = currentTime - orderTime;

        return timeDifference >= INTERVAL;
    }

    private void capNhatDonHangListView() {
        if (donHangAdapter == null) {
            donHangList = new ArrayList<>(donHangDao.getAllDonHang());
            donHangAdapter = new DonHangAdapter(getActivity(), donHangList);
            lvDonHang.setAdapter(donHangAdapter);
        } else {
            donHangList.clear();
            donHangList.addAll(donHangDao.getAllDonHang());
            donHangAdapter.notifyDataSetChanged();
        }
    }

    private void openDonHangDialog(final int type, final DonHang selectedDonHang) {
        final Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.dialog_donhang);

        final EditText edMaDon = dialog.findViewById(R.id.edMaDon);
        final EditText edNgayLap = dialog.findViewById(R.id.edNgayLap);
        final EditText edTrangThaiDon = dialog.findViewById(R.id.edTrangThaiDon);
        final Spinner edPTVanChuyen = dialog.findViewById(R.id.edPTVanChuyen);

        final EditText edMaDonCtDh = dialog.findViewById(R.id.edMaDonCtDh);
        final EditText edMaNvDh = dialog.findViewById(R.id.edMaNvDh);
        edMaDon.setEnabled(type == 0);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.phuong_thuc_van_chuyen, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spinnerAdapter != null) {
            edPTVanChuyen.setAdapter(spinnerAdapter);
        }

        if (type == 1 && selectedDonHang != null) {
            edMaDon.setText(String.valueOf(selectedDonHang.getMaDon()));
            edNgayLap.setText(String.valueOf(selectedDonHang.getNgayLap()));
            edTrangThaiDon.setText(selectedDonHang.getTrangThaiDon());

            int position = -1;
            if (selectedDonHang.getpTVanChuyen() != null) {
                position = spinnerAdapter.getPosition(selectedDonHang.getpTVanChuyen());
            }
            if (position >= 0) {
                edPTVanChuyen.setSelection(position);
            }

            edMaDonCtDh.setText(String.valueOf(selectedDonHang.getMaGio()));
            edMaNvDh.setText(selectedDonHang.getMaNv());
        }

        Button btnSaveDh = dialog.findViewById(R.id.btnSaveDh);
        btnSaveDh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DonHang donHang = new DonHang();
                String ngayLapString = edNgayLap.getText().toString();

                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date ngayLapDate = dateFormat.parse(ngayLapString);
                    donHang.setNgayLap(ngayLapDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                donHang.setTrangThaiDon(edTrangThaiDon.getText().toString());

                String selectedSpinnerItem = edPTVanChuyen.getSelectedItem().toString();
                donHang.setpTVanChuyen(selectedSpinnerItem);

                donHang.setMaGio(Integer.parseInt(edMaDonCtDh.getText().toString()));
                donHang.setMaNv(edMaNvDh.getText().toString());

                if (type == 0) {
                    // Insert new DonHang
                    long result = donHangDao.insert(donHang);
                    if (result > 0) {
                        Toast.makeText(requireContext(), "Thêm đơn hàng thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Thêm đơn hàng thất bại ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Update existing DonHang
                    donHang.setMaDon(selectedDonHang.getMaDon());
                    int result = donHangDao.update(donHang);
                    if (result > 0) {
                        Toast.makeText(requireContext(), "Cập nhật đơn hàng thành công", Toast.LENGTH_SHORT).show();

                        // Update the item in the list
                        int position = donHangList.indexOf(selectedDonHang);
                        if (position != -1) {
                            donHangList.set(position, donHang);
                            donHangAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(requireContext(), "Cập nhật đơn hàng thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                // Refresh the DonHang list view
                capNhatDonHangListView();

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Cancel button click listener
        Button btnCancelDh = dialog.findViewById(R.id.btnCancelDh);
        btnCancelDh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }


}
