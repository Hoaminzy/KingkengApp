package com.Hoaminzf.fithou.mykengkingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayList implements Serializable {

    @SerializedName("PK_iMaPlayList")
    @Expose
    private String pKIMaPlayList;
    @SerializedName("sTenPlayList")
    @Expose
    private String sTenPlayList;
    @SerializedName("sHinhAnhPL")
    @Expose
    private String sHinhAnhPL;
    @SerializedName("sHinhIcon")
    @Expose
    private String sHinhIcon;

    public String getPKIMaPlayList() {
        return pKIMaPlayList;
    }

    public void setPKIMaPlayList(String pKIMaPlayList) {
        this.pKIMaPlayList = pKIMaPlayList;
    }

    public String getSTenPlayList() {
        return sTenPlayList;
    }

    public void setSTenPlayList(String sTenPlayList) {
        this.sTenPlayList = sTenPlayList;
    }

    public String getSHinhAnhPL() {
        return sHinhAnhPL;
    }

    public void setSHinhAnhPL(String sHinhAnhPL) {
        this.sHinhAnhPL = sHinhAnhPL;
    }

    public String getSHinhIcon() {
        return sHinhIcon;
    }

    public void setSHinhIcon(String sHinhIcon) {
        this.sHinhIcon = sHinhIcon;
    }

}