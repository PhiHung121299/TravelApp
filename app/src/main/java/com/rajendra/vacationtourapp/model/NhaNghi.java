package com.rajendra.vacationtourapp.model;

import android.content.Intent;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class NhaNghi implements Serializable {
    public String diaChi;
    public Float diemDanhGia;

    public NhaNghi() {
    }

    public String hinhAnh;
    public Integer iDDiaDiem;
    public String sdt;
    public String ten;
    public String vitriMap;

    public String getTongQuan() {
        return tongQuan;
    }

    public void setTongQuan(String tongQuan) {
        this.tongQuan = tongQuan;
    }

    public String tongQuan;

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Float getDiemDanhGia() {
        return diemDanhGia;
    }

    public void setDiemDanhGia(Float diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Integer getiDDiaDiem() {
        return iDDiaDiem;
    }

    public void setiDDiaDiem(Integer iDDiaDiem) {
        this.iDDiaDiem = iDDiaDiem;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getVitriMap() {
        return vitriMap;
    }

    public void setVitriMap(String vitriMap) {
        this.vitriMap = vitriMap;
    }


    public NhaNghi(String diaChi, Float diemDanhGia, String hinhAnh, Integer iDDiaDiem, String sdt, String ten, String vitriMap, String tongQuan ){
        this.diaChi = diaChi;
        this.diemDanhGia = diemDanhGia;
        this.hinhAnh = hinhAnh;
        this.iDDiaDiem = iDDiaDiem;
        this.sdt = sdt;
        this.ten = ten;
        this.vitriMap = vitriMap;
        this.tongQuan = tongQuan;
    }


}