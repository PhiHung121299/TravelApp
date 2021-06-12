package com.rajendra.vacationtourapp;

import java.io.Serializable;

public class UserOject implements Serializable {
    public UserOject() {
    }

    public UserOject(String hoTen, String loai, String ngaySinh, String anhDaiDien,String gMail) {
        this.hoTen = hoTen;
        this.loai = loai;
        this.ngaySinh = ngaySinh;
        this.anhDaiDien = anhDaiDien;
        this.gMail = gMail;
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

    String hoTen, loai, ngaySinh,anhDaiDien,gMail;
}
