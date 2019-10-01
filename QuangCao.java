package com.Hoaminzf.fithou.mykengkingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuangCao implements Serializable {

@SerializedName("PK_iMaQuangCao")
@Expose
private String pKIMaQuangCao;
@SerializedName("sHinhAnhQC")
@Expose
private String sHinhAnhQC;
@SerializedName("sNoiDung")
@Expose
private String sNoiDung;
@SerializedName("FK_iMaBaiHat")
@Expose
private String fKIMaBaiHat;
@SerializedName("sTenBaiHat")
@Expose
private String sTenBaiHat;
@SerializedName("sHinhAnhBH")
@Expose
private String sHinhAnhBH;

public String getPKIMaQuangCao() {
return pKIMaQuangCao;
}

public void setPKIMaQuangCao(String pKIMaQuangCao) {
this.pKIMaQuangCao = pKIMaQuangCao;
}

public String getSHinhAnhQC() {
return sHinhAnhQC;
}

public void setSHinhAnhQC(String sHinhAnhQC) {
this.sHinhAnhQC = sHinhAnhQC;
}

public String getSNoiDung() {
return sNoiDung;
}

public void setSNoiDung(String sNoiDung) {
this.sNoiDung = sNoiDung;
}

public String getFKIMaBaiHat() {
return fKIMaBaiHat;
}

public void setFKIMaBaiHat(String fKIMaBaiHat) {
this.fKIMaBaiHat = fKIMaBaiHat;
}

public String getSTenBaiHat() {
return sTenBaiHat;
}

public void setSTenBaiHat(String sTenBaiHat) {
this.sTenBaiHat = sTenBaiHat;
}

public String getSHinhAnhBH() {
return sHinhAnhBH;
}

public void setSHinhAnhBH(String sHinhAnhBH) {
this.sHinhAnhBH = sHinhAnhBH;
}

}