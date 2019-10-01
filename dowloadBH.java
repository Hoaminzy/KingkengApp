package com.Hoaminzf.fithou.mykengkingapp.Model;

public class dowloadBH   {
    private int id;
    private String tenbaihat,linkbaihat,anh;

    public dowloadBH(int id, String tenbaihat, String linkbaihat, String anh) {
        this.id = id;
        this.tenbaihat = tenbaihat;
        this.linkbaihat = linkbaihat;
        this.anh = anh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }

    public String getLinkbaihat() {
        return linkbaihat;
    }

    public void setLinkbaihat(String linkbaihat) {
        this.linkbaihat = linkbaihat;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }


}
