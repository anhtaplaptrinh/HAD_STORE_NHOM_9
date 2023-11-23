package com.example.had_store.Model;

import java.util.Date;

public class DonHang {
    private int maDon;
    private Date ngayLap;
    private String trangThaiDon,pTVanChuyen;
    private int maGio;
    private String maNv;

    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTrangThaiDon() {
        return trangThaiDon;
    }

    public void setTrangThaiDon(String trangThaiDon) {
        this.trangThaiDon = trangThaiDon;
    }

    public String getpTVanChuyen() {
        return pTVanChuyen;
    }

    public void setpTVanChuyen(String pTVanChuyen) {
        this.pTVanChuyen = pTVanChuyen;
    }

    public int getMaGio() {
        return maGio;
    }

    public void setMaGio(int maGio) {
        this.maGio = maGio;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public DonHang() {
    }

    public DonHang(int maDon, Date ngayLap, String trangThaiDon, String pTVanChuyen, int maGio, String maNv) {
        this.maDon = maDon;
        this.ngayLap = ngayLap;
        this.trangThaiDon = trangThaiDon;
        this.pTVanChuyen = pTVanChuyen;
        this.maGio = maGio;
        this.maNv = maNv;
    }
}
