package com.Hoaminzf.fithou.mykengkingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChuDe implements Serializable {
    @SerializedName("PK_iMaChuDe")
    @Expose
    private String pKIMaChuDe;
    @SerializedName("sTenChuDe")
    @Expose
    private String sTenChuDe;
    @SerializedName("sHinhAnhCD")
    @Expose
    private String sHinhAnhCD;

    public String getPKIMaChuDe() {
        return pKIMaChuDe;
    }

    public void setPKIMaChuDe(String pKIMaChuDe) {
        this.pKIMaChuDe = pKIMaChuDe;
    }

    public String getSTenChuDe() {
        return sTenChuDe;
    }

    public void setSTenChuDe(String sTenChuDe) {
        this.sTenChuDe = sTenChuDe;
    }

    public String getSHinhAnhCD() {
        return sHinhAnhCD;
    }

    public void setSHinhAnhCD(String sHinhAnhCD) {
        this.sHinhAnhCD = sHinhAnhCD;
    }

}
