package com.rajendra.vacationtourapp.model;

public class NhaNghi {
    public NhaNghi() {
    }

    String IDNhaNghi;

    public NhaNghi(String IDNhaNghi, String Ten, String DiemDanhGia, String DiaChi, String SDT, String IdDiaDiem, String VitriMap) {
        this.IDNhaNghi = IDNhaNghi;
        this.Ten = Ten;
        this.DiemDanhGia = DiemDanhGia;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.IdDiaDiem = IdDiaDiem;
        this.VitriMap = VitriMap;
    }

    public String Ten;
    public String DiemDanhGia;
    public String DiaChi;
    public String SDT;
    public String IdDiaDiem;
    public String VitriMap;



}
