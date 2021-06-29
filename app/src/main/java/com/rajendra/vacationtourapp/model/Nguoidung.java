package com.rajendra.vacationtourapp.model;


import java.io.Serializable;

public class Nguoidung implements Serializable {
    public Nguoidung() {
    }


    String anhDaiDien, hoTen, loai, matKhau, ngaySinh, gMail;

    //   String tentaikhoan, matkhau, hoten, ngaysinh, diachi, gmail, sdt, loaitk, id;

    public Nguoidung(String anhDaiDien, String hoTen, String loai, String matKhau, String ngaySinh, String gMail) {
        this.anhDaiDien = anhDaiDien;
        this.hoTen = hoTen;
        this.loai = loai;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.gMail = gMail;

    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
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

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getgMail() {
        return gMail;
    }

    public void setgMail(String gMail) {
        this.gMail = gMail;
    }


}
