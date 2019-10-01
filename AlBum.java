package com.Hoaminzf.fithou.mykengkingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlBum implements Serializable {

        @SerializedName("PK_iMaAlBum")
        @Expose
        private String pKIMaAlBum;
        @SerializedName("sTenAlBum")
        @Expose
        private String sTenAlBum;
        @SerializedName("sTenCaSiAB")
        @Expose
        private String sTenCaSiAB;
        @SerializedName("sHinhAnhAB")
        @Expose
        private String sHinhAnhAB;

        public String getPKIMaAlBum() {
            return pKIMaAlBum;
        }

        public void setPKIMaAlBum(String pKIMaAlBum) {
            this.pKIMaAlBum = pKIMaAlBum;
        }

        public String getSTenAlBum() {
            return sTenAlBum;
        }

        public void setSTenAlBum(String sTenAlBum) {
            this.sTenAlBum = sTenAlBum;
        }

        public String getSTenCaSiAB() {
            return sTenCaSiAB;
        }

        public void setSTenCaSiAB(String sTenCaSiAB) {
            this.sTenCaSiAB = sTenCaSiAB;
        }

        public String getSHinhAnhAB() {
            return sHinhAnhAB;
        }

        public void setSHinhAnhAB(String sHinhAnhAB) {
            this.sHinhAnhAB = sHinhAnhAB;
        }

    }

