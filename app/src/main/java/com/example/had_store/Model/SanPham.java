package com.example.had_store.Model;

public class SanPham {
   private int Masp,maHang,GiaSp,SoLuongSp;
   private String TenSp,anhSp,ttSp;

    public SanPham() {
    }

    public SanPham(int masp, int maHang, int giaSp, int soLuongSp, String tenSp) {
        Masp = masp;
        this.maHang = maHang;
        GiaSp = giaSp;
        SoLuongSp = soLuongSp;
        TenSp = tenSp;
    }

    public SanPham(int masp, int maHang, int giaSp, int soLuongSp, String tenSp, String anhSp, String ttSp) {
        this.Masp = masp;
        this.maHang = maHang;
        this.GiaSp = giaSp;
        this.SoLuongSp = soLuongSp;
        this.TenSp = tenSp;
        this.anhSp = anhSp;
        this.ttSp = ttSp;
    }

    public int getMasp() {
        return Masp;
    }

    public void setMasp(int masp) {
        this.Masp = masp;
    }

    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public int getGiaSp() {
        return GiaSp;
    }

    public void setGiaSp(int giaSp) {
        this.GiaSp = giaSp;
    }

    public int getSoLuongSp() {
        return SoLuongSp;
    }

    public void setSoLuongSp(int soLuongSp) {
        this.SoLuongSp = soLuongSp;
    }

    public String getTenSp() {
        return TenSp;
    }

    public void setTenSp(String tenSp) {
        this.TenSp = tenSp;
    }

    public String getAnhSp() {
        return anhSp;
    }

    public void setAnhSp(String anhSp) {
        this.anhSp = anhSp;
    }

    public String getTtSp() {
        return ttSp;
    }

    public void setTtSp(String ttSp) {
        this.ttSp = ttSp;
    }
}
