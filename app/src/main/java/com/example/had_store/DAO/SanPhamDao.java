package com.example.had_store.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.had_store.Database.DbHelper;
import com.example.had_store.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {
    private SQLiteDatabase db;
    private DbHelper dbHelper;
    public SanPhamDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insert(SanPham sanPham){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        values.put("maSp",sanPham.getMasp());
        values.put("tenSp",sanPham.getTenSp());
        values.put("giaSp",sanPham.getGiaSp());
        values.put("soLuongSp",sanPham.getSoLuongSp());
        values.put("maHang",sanPham.getMaHang());
        values.put("anhSp",sanPham.getAnhSp());
        values.put("trangThaiSp",sanPham.getTtSp());
       long row = db.insert("SanPham", null,values);
       return (row > 0);
    }

    public int update(SanPham sanPham){
        ContentValues values = new ContentValues();
        values.put("maSp",sanPham.getMasp());
        values.put("tenSp",sanPham.getTenSp());
        values.put("giaSp",sanPham.getGiaSp());
        values.put("soLuongSp",sanPham.getSoLuongSp());
        values.put("maHang",sanPham.getMaHang());
        values.put("anhSp",sanPham.getAnhSp());
        values.put("trangThaiSp",sanPham.getTtSp());
        return  db.update("SanPham",values,"maSp=?",new String[]{sanPham.getTenSp()});

    }
    public boolean delete(String id){

         db.delete("SanPham","maSp=?", new String[]{String.valueOf(id)});

        return true;
    }

    public List<SanPham> getDATA(String sql, String... selectionArgs) {
        List<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            SanPham sanPham = new SanPham();
            sanPham.setMasp(cursor.getInt(cursor.getColumnIndex("maSp")));
            sanPham.setTenSp(cursor.getString(cursor.getColumnIndex("tenSp")));
            sanPham.setGiaSp(cursor.getInt(cursor.getColumnIndex("giaSp")));
            sanPham.setMaHang(cursor.getInt(cursor.getColumnIndex("maHang")));
            sanPham.setSoLuongSp(cursor.getInt(cursor.getColumnIndex("soLuongSp")));
            sanPham.setTtSp(cursor.getString(cursor.getColumnIndex("trangThaiSp")));
            sanPham.setAnhSp(cursor.getString(cursor.getColumnIndex("anhSp")));
            list.add(sanPham);
        }
        cursor.close();
        return list;
    }

    public List<SanPham> getAll(){
        String sql = "SELECT * FROM SanPham";
        return  getDATA(sql);
    }
//public ArrayList<SanPham> getsanphamall(){
//    ArrayList<SanPham> list = new ArrayList();
////     db.getReadableDatabase();
//    Cursor cursor = db.rawQuery("select sp.maSp, sp.tenSp, sp.giaSp, sp.soLuongSp,sp.maHang , lsp.maHang from SanPham sp, HangSanPham lsp where sp.maHang = lsp.maHang",null);
//    if (cursor.getCount()!=0){
//        cursor.moveToFirst();
//        do {
//            list.add(new SanPham(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5)));
//        }while (cursor.moveToNext());
//    }
//    return list;
//}

}
