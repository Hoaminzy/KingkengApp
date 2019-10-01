package com.Hoaminzf.fithou.mykengkingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHat implements Parcelable {

@SerializedName("PK_iMaBaiHat")
@Expose
private String pKIMaBaiHat;
@SerializedName("sTenBaiHat")
@Expose
private String sTenBaiHat;
@SerializedName("sHinhAnhBH")
@Expose
private String sHinhAnhBH;
@SerializedName("sCaSi")
@Expose
private String sCaSi;
@SerializedName("sLinkBH")
@Expose
private String sLinkBH;
@SerializedName("sLuotThich")
@Expose
private String sLuotThich;

    protected BaiHat(Parcel in) {
        pKIMaBaiHat = in.readString();
        sTenBaiHat = in.readString();
        sHinhAnhBH = in.readString();
        sCaSi = in.readString();
        sLinkBH = in.readString();
        sLuotThich = in.readString();
    }

    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };

    public String getPKIMaBaiHat() {
return pKIMaBaiHat;
}

public void setPKIMaBaiHat(String pKIMaBaiHat) {
this.pKIMaBaiHat = pKIMaBaiHat;
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

public String getSCaSi() {
return sCaSi;
}

public void setSCaSi(String sCaSi) {
this.sCaSi = sCaSi;
}

public String getSLinkBH() {
return sLinkBH;
}

public void setSLinkBH(String sLinkBH) {
this.sLinkBH = sLinkBH;
}

public String getSLuotThich() {
return sLuotThich;
}

public void setSLuotThich(String sLuotThich) {
this.sLuotThich = sLuotThich;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pKIMaBaiHat);
        dest.writeString(sTenBaiHat);
        dest.writeString(sHinhAnhBH);
        dest.writeString(sCaSi);
        dest.writeString(sLinkBH);
        dest.writeString(sLuotThich);
    }
}