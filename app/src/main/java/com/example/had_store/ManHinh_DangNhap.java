package com.example.had_store;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.had_store.DAO.NhanVienDao;
import com.example.had_store.Model.NhanVien;

public class ManHinh_DangNhap extends AppCompatActivity {

    private EditText edMaNv, edMatKhauNv;
    private CheckBox chkRememberPass;
    private Button btnLogin, btnCancel;

    private NhanVienDao nhanVienDao;

    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_MA_NV = "maNv";
    private static final String KEY_MAT_KHAU = "matKhau";
    private static final String KEY_REMEMBER_PASS = "rememberPass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);

        // Initialize views
        edMaNv = findViewById(R.id.edMaNvDn);
        edMatKhauNv = findViewById(R.id.edMatKhauNvDn);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnDangKyDn);

        // Initialize DAO
        nhanVienDao = new NhanVienDao(this);

        // Restore saved login information
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean rememberPass = prefs.getBoolean(KEY_REMEMBER_PASS, false);
        if (rememberPass) {
            String savedMaNv = prefs.getString(KEY_MA_NV, "");
            String savedMatKhau = prefs.getString(KEY_MAT_KHAU, "");

            edMaNv.setText(savedMaNv);
            edMatKhauNv.setText(savedMatKhau);
            chkRememberPass.setChecked(true);
        }

        // Set click listener for the login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // Set click listener for the cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinh_DangNhap.this, ManHinh_DangKy.class);
                startActivity(intent);

                finish();            }
        });

        // Set click listener for rememberPass checkbox
        chkRememberPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLoginInfo();
            }
        });
    }

    private void login() {
        String maNv = edMaNv.getText().toString().trim();
        String matKhau = edMatKhauNv.getText().toString().trim();

        if (maNv.isEmpty() || matKhau.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        int result = nhanVienDao.checkLogin(maNv, matKhau);

        if (result == 1) {
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ManHinh_DangNhap.this, ManHinh_TrangChu.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(this, "Sai mã nhân viên hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveLoginInfo() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (chkRememberPass.isChecked()) {
            String maNv = edMaNv.getText().toString().trim();
            String matKhau = edMatKhauNv.getText().toString().trim();

            editor.putString(KEY_MA_NV, maNv);
            editor.putString(KEY_MAT_KHAU, matKhau);
            editor.putBoolean(KEY_REMEMBER_PASS, true);
        } else {
            editor.remove(KEY_MA_NV);
            editor.remove(KEY_MAT_KHAU);
            editor.remove(KEY_REMEMBER_PASS);
        }

        editor.apply();
    }
}
