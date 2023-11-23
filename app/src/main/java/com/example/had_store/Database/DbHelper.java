package com.example.had_store.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "Hqwewqe";
    static  final  int dbVersion=16;
    public DbHelper(Context context){ super(context,dbName,null,dbVersion);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL("create table NhanVien (" +
                "maNv Text primary key ," +
                "tenNv TEXT not null," +
                "matKhauNv TEXT not null," +
                "soNv integer not null," +
                "emailNv TEXT not null," +
                "anhNv text not null)");
        sqLiteDatabase.execSQL("insert into NhanVien values " +
                "('NV01','Nguyễn Tuấn ','123',0366691234,'dungntph@fpt.edu.vn','https://i.pinimg.com/236x/74/20/58/742058099ee4749d2da5d820396870eb.jpg')");

        sqLiteDatabase.execSQL("create table KhachHang (" +
                "maKh text primary key," +
                "tenKh TEXT not null," +
                "matKhauKh TEXT not null," +
                "soKh integer not null," +
                "emailKh TEXT not null," +
                "diaChiKh TEXT not null," +
                "anhKh text not null)");
        sqLiteDatabase.execSQL("insert into KhachHang values " +
                "('KH01','Nguyễn Dũng','123',0366691234,'dungnt67@fpt.edu.vn','Nho Quan - Ninh Binh','lnkanh')," +
                "('KH02','Dung Tuan','123',0366691234,'wqeeer7@fpt.edu.vn','Dong Da - Ha Noi','lnkanh')");


        sqLiteDatabase.execSQL("create table HangSanPham (" +
                "maHang integer primary key autoincrement," +
                "tenHang TEXT not null," +
                "diaChiHang TEXT not null," +
                "anhHang text not null)");
        sqLiteDatabase.execSQL("insert into HangSanPham values " +
                "(1,'APPLE','Nam Tu Liem - Ha Noi','linkanh')," +
                "(2,'SAMSUNG','Nho Quan - Ninh Binh','linkanh')," +
                "(3,'XIAOMI','Nam Tu Liem - Ha Noi','linkanh')," +
                "(4,'VIVO','Nam Tu Liem - Ha Noi','linkanh')," +
                "(5,'OPPO','Nam Tu Liem - Ha Noi','linkanh')");

        sqLiteDatabase.execSQL("create table SanPham (" +
                "maSp integer primary key autoincrement," +
                "tenSp text not null, " +
                "giaSp integer not null," +
                "soLuongSp integer not null," +
                "trangThaiSp text not null," +//có sale hay không
                "maHang integer references HangSanPham(maHang), " +
                "anhSp text not null )");
        sqLiteDatabase.execSQL("insert into SanPham values " +
                "(1,'IPHONE 15 PRO MAX',35000000,123,'sale30%',1,'linkanh')," +
                "(2,'VIVO X NOTE',20000000,12,'Khong',4,'linkanh')," +
                "(3,'IPHONE 13 PRO MAX',20000000,23,'sale30%',1,'linkanh')," +
                "(4,'IPHONE 15 PRO ',30000000,79,'khong',1,'linkanh')," +
                "(5,'SAMSUNG GALAXY S23',15000000,68,'sale30%',2,'link anh')");

        sqLiteDatabase.execSQL("create table GioHang (" +
                "maGio integer primary key autoincrement," +
                "soLuong integer not null," +
                "diaChiGio text not null," +
                "maSp integer references SanPham(maSp), " +
                "maKh text references KhachHang(maKh) )");
        sqLiteDatabase.execSQL("insert into GioHang values " +
                "(1,4,'Ha Noi',1,'KH01')," +
                "(2,5,'Ninh Binh',2,'KH01')," +
                "(3,2,'Ha Noi',1,'KH01')," +
                "(4,1,'Ha Noi',1,'KH01')," +
                "(5,5,'Ninh Binh',2,'KH01')");

        sqLiteDatabase.execSQL("create table DonHang (" +
                "maDon integer primary key autoincrement," +
                "ngayLap date not null," +
                "trangThaiDon text not null," +
                "pTVanChuyen text not null," +
                "maGio integer references GioHang(maGio), " +
                "maNv text references NhanVien(maNv) )");
        sqLiteDatabase.execSQL("insert into DonHang values " +
                "(1,'20/09/2023','Dang doi don vi van chuyen','Van chuyen tan nha',2,'NV01')," +
                "(2,'20/09/2023','Dang van chuyen','Hoa Toc',3,'NV01')," +
                "(3,'20/09/2023','Da goi hang','Khong',1,'NV01')," +
                "(4,'20/09/2023','Dang doi don vi van chuyen','Van chuyen tan nha',5,'NV01')," +
                "(5,'20/09/2024','Dang van chuyen','Van chuyen tan nha',4,'NV01')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists SanPham");
        sqLiteDatabase.execSQL("drop table if exists HangSanPham");
        sqLiteDatabase.execSQL("drop table if exists DonHang");
        sqLiteDatabase.execSQL("drop table if exists GioHang");
        sqLiteDatabase.execSQL("drop table if exists NhanVien");
        sqLiteDatabase.execSQL("drop table if exists KhachHang");
        onCreate(sqLiteDatabase);
    }
}
