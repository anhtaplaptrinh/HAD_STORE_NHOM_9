package com.example.had_store.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.had_store.Database.DbHelper;
import com.example.had_store.Model.HangSanPham;

import java.util.ArrayList;

public class HangSanPhamDao {
    private final DbHelper dbHelper;

    public HangSanPhamDao(Context context) {
      dbHelper = new DbHelper(context);
    }
    public ArrayList<HangSanPham> selectAll(){
ArrayList<HangSanPham> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from HangSanPham", null);
            if (cursor.getCount() > 0){
                cursor.moveToNext();
                while (!cursor.isAfterLast()){
                    HangSanPham hsp = new HangSanPham();
                    hsp.setMaHang(cursor.getInt(0));
                    hsp.setTenHang(cursor.getString(1));
                    hsp.setDiaChiHang(cursor.getString(2));
                    hsp.setAnhHang(cursor.getString(3));
                    list.add(hsp);
                    cursor.moveToNext();
                }
            }
        }catch (Exception ex){

        }
        return list;
    }
    public boolean insert(HangSanPham hsp){
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("maHang", hsp.getMaHang());
        values.put("tenHang",hsp.getTenHang());
        values.put("diachiHang",hsp.getDiaChiHang());
        values.put("AnhHang",hsp.getAnhHang());
        long row = db.insert("HangSanPham", null, values);
        return (row > 0);
    }
    public boolean update(HangSanPham hsp){
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("maHang", hsp.getMaHang());
        values.put("tenHang",hsp.getTenHang());
        values.put("diachiHang",hsp.getDiaChiHang());
//        values.put("AnhHang",hsp.getAnhHang());
        long row = db.update("HangSanPham",values,"MaHang=?", new String[]{String.valueOf(hsp.getMaHang())});
        return (row > 0);
    }
    public  boolean delete(int mahang){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("HangSanPham","MaHang=?", new String[]{String.valueOf(mahang)});
        return (row > 0);
    }

}
