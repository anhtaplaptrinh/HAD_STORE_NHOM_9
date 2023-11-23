package com.example.had_store.Model;

public class DonHangChiTiet {
    private int maDonCt,soLuong;
    private String diaChiDhct;
    private int maSp,maKh;

    public DonHangChiTiet() {
    }

    @Override
    public String toString() {
        return "DonHangChiTiet{" +
                "maDonCt=" + maDonCt +
                ", soLuong=" + soLuong +
                ", diaChiDhct='" + diaChiDhct + '\'' +
                ", maSp=" + maSp +
                ", maKh=" + maKh +
                '}';
    }

    public int getMaDonCt() {
        return maDonCt;
    }

    public void setMaDonCt(int maDonCt) {
        this.maDonCt = maDonCt;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDiaChiDhct() {
        return diaChiDhct;
    }

    public void setDiaChiDhct(String diaChiDhct) {
        this.diaChiDhct = diaChiDhct;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    public DonHangChiTiet(int maDonCt, int soLuong, String diaChiDhct, int maSp, int maKh) {
        this.maDonCt = maDonCt;
        this.soLuong = soLuong;
        this.diaChiDhct = diaChiDhct;
        this.maSp = maSp;
        this.maKh = maKh;
    }
}
