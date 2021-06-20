package com.rajendra.vacationtourapp;

import java.io.Serializable;

public class UserOject implements Serializable {
    public UserOject() {
    }

    public UserOject(String hoTen, String loai, String ngaySinh, String anhDaiDien, String gMail, String sDt, String diaChi) {
        this.hoTen = hoTen;
        this.loai = loai;
        this.ngaySinh = ngaySinh;
        this.anhDaiDien = anhDaiDien;
        this.gMail = gMail;
        this.sDt = sDt;
        this.diaChi = diaChi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public String getgMail() {
        return gMail;
    }

    public void setgMail(String gMail) {
        this.gMail = gMail;
    }


    public String getsDt() {
        return sDt;
    }

    public void setsDt(String sDt) {
        this.sDt = sDt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    String hoTen;
    String loai;
    String ngaySinh;
    String anhDaiDien;
    String gMail;
    String sDt;
    String diaChi;
}
