package com.rajendra.vacationtourapp.model;


import java.io.Serializable;

public class Nguoidung implements Serializable {


    String tentaikhoan, matkhau, hoten, ngaysinh, diachi, gmail, sdt, loaitk, id;

    public Nguoidung(String id_nguoidung, String tentaikhoan, String matkhau, String hoten, String ngaysinh, String gmail, String sdt, String diachi, String loaitk) {
        this.id = id_nguoidung;
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.gmail = gmail;
        this.sdt = sdt;
        this.loaitk = loaitk;
    }


    public Nguoidung(String tentaikhoan, String hoten, String ngaysinh, String gmail, String sdt, String diachi, String loaitk) {

        this.tentaikhoan = tentaikhoan;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.gmail = gmail;
        this.sdt = sdt;
        this.loaitk = loaitk;
    }

    public Nguoidung(String tentaikhoan, String matkhau, String hoten, String ngaysinh, String gmail, String diachi, String sdt, String loaitk) {

        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.gmail = gmail;
        this.sdt = sdt;
        this.loaitk = loaitk;
    }

    public String getLoaitk() {
        return loaitk;
    }

    public void setLoaitk(String loaitk) {
        this.loaitk = loaitk;
    }

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "Nguoidung{" +
                ", tentaikhoan='" + tentaikhoan + '\'' +
                ", matkhau='" + matkhau + '\'' +
                ", hoten='" + hoten + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", diachi='" + diachi + '\'' +
                ", gmail='" + gmail + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }


}
