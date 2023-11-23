package com.example.had_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.had_store.Fragment.Fragment_DonHang;
import com.example.had_store.Fragment.Fragment_HangSanPham;
import com.example.had_store.Fragment.Fragment_KhachHang;
import com.example.had_store.Fragment.Fragment_NhanVien;
import com.example.had_store.Fragment.Fragment_SanPham;
import com.google.android.material.navigation.NavigationView;

public class ManHinh_TrangChu extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    View mHeaderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_trang_chu);
        drawerLayout= findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        mHeaderView = nav.getHeaderView(0);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setTitle("Quản lý Sản Phẩm");
        Fragment_SanPham pm = new Fragment_SanPham();
        replaceFrg(pm);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.quanlysanpham){
                    setTitle("Quản lý sản phẩm ");
                    Fragment_SanPham sp = new Fragment_SanPham ();
                    replaceFrg(sp);
                }
                if (item.getItemId()==R.id.quanlynhanvien){
                    setTitle("Quản lý nhân viên ");
                    Fragment_NhanVien nv = new Fragment_NhanVien ();
                    replaceFrg(nv);
                }
                if (item.getItemId()==R.id.quanlydonhang){
                    setTitle("Quản lý đơn hàng");
                    Fragment_DonHang dh = new Fragment_DonHang ();
                    replaceFrg(dh);
                }

                if (item.getItemId()==R.id.quanlykhachhang){
                    setTitle("Quản lý khách hàng");
                    Fragment_KhachHang kh = new Fragment_KhachHang ();
                    replaceFrg(kh);
                }
                if (item.getItemId()==R.id.quanlyhangsanpham){
                    setTitle("Quản lý hãng sản phẩm ");
                    Fragment_HangSanPham hsp = new Fragment_HangSanPham ();
                    replaceFrg(hsp);
                }

                if (item.getItemId()==R.id.doimatkhau){
                    setTitle("Đổi mật khẩu");
                    Fragment_SanPham dp = new Fragment_SanPham ();
                    replaceFrg(dp);
                }
                else if (item.getItemId()==R.id.smnn){
                    setTitle("Top 10 sách mượn nhiều nhất ");
                    Fragment_SanPham top = new Fragment_SanPham();
                    replaceFrg(top);
                }


                else if (item.getItemId()==R.id.dangxuat) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ManHinh_TrangChu.this);
                    builder.setTitle("Đăng xuất");
                    builder.setMessage("Bạn có muốn đăng xuất không?");
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(ManHinh_TrangChu.this, ManHinh_Chao.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(ManHinh_TrangChu.this, "Log Out successful", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();

                }
                return true;
            }
        });
    }
    public void replaceFrg(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmNav, frg).commit();
    }
}