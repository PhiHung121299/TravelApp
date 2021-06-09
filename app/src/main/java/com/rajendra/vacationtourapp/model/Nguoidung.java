package com.rajendra.vacationtourapp.model;


import java.io.Serializable;

public class Nguoidung implements Serializable {
    public Nguoidung() {
    }

    String anhDaiDien, hoTen, loai, matKhau, ngaySinh, tenTaiKhoan;

    //   String tentaikhoan, matkhau, hoten, ngaysinh, diachi, gmail, sdt, loaitk, id;

    public Nguoidung(String anhDaiDien, String hoTen, String loai, String matKhau, String ngaySinh, String tenTaiKhoan) {
        this.anhDaiDien = anhDaiDien;
        this.hoTen = hoTen;
        this.loai = loai;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.tenTaiKhoan = tenTaiKhoan;
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

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }


}
