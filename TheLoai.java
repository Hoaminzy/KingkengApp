package com.Hoaminzf.fithou.mykengkingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TheLoai implements Serializable {
    @SerializedName("PK_iMaTheLoai")
    @Expose
    private String pKIMaTheLoai;
    @SerializedName("FK_iMaChuDe")
    @Expose
    private String fKIMaChuDe;
    @SerializedName("sTenTheLoai")
    @Expose
    private String sTenTheLoai;
    @SerializedName("sHinhAnhTL")
    @Expose
    private String sHinhAnhTL;

    public String getPKIMaTheLoai() {
        return pKIMaTheLoai;
    }

    public void setPKIMaTheLoai(String pKIMaTheLoai) {
        this.pKIMaTheLoai = pKIMaTheLoai;
    }

    public String getFKIMaChuDe() {
        return fKIMaChuDe;
    }

    public void setFKIMaChuDe(String fKIMaChuDe) {
        this.fKIMaChuDe = fKIMaChuDe;
    }

    public String getSTenTheLoai() {
        return sTenTheLoai;
    }

    public void setSTenTheLoai(String sTenTheLoai) {
        this.sTenTheLoai = sTenTheLoai;
    }

    public String getSHinhAnhTL() {
        return sHinhAnhTL;
    }

    public void setSHinhAnhTL(String sHinhAnhTL) {
        this.sHinhAnhTL = sHinhAnhTL;
    }
    }

