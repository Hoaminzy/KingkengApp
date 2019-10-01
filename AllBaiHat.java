package com.Hoaminzf.fithou.mykengkingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


    public class AllBaiHat {

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

    }


