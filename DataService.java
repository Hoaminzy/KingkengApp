package com.Hoaminzf.fithou.mykengkingapp.Service;

import com.Hoaminzf.fithou.mykengkingapp.Model.AlBum;
import com.Hoaminzf.fithou.mykengkingapp.Model.BaiHat;
import com.Hoaminzf.fithou.mykengkingapp.Model.ChuDe;
import com.Hoaminzf.fithou.mykengkingapp.Model.PlayList;
import com.Hoaminzf.fithou.mykengkingapp.Model.QuangCao;
import com.Hoaminzf.fithou.mykengkingapp.Model.TheLoai;
import com.Hoaminzf.fithou.mykengkingapp.Model.TheLoaiTrongNgay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("songbaner.php")
    Call<List<QuangCao>> GetDataBanner();
    @GET("playlistforcurrent.php")
    Call<List<PlayList>> GetPlayListCurrentDay();
     @GET("chudevatheloai.php")
     Call<TheLoaiTrongNgay> GetTheLoaiTrongNgay();
    @GET("theloaitheongay.php")
    Call<List<TheLoai>> GetTheLoai();
    @GET("chudetheongay.php")
    Call<List<ChuDe>> GetChuDe();
    @GET("album.php")
    Call<List<AlBum>> GetAlbumHot();
    @GET("baihatyeuthich.php")
    Call<List<BaiHat>> GetBaiHatHot();
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>>  GetDanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>>  GetDanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);
    @GET(value = "danhsachcacplaylist.php")
    Call<List<PlayList>> GetDanhsachcacPlayList();
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>>  GetDanhsachbaihatthetheloai(@Field("idtheloai") String idtheloai);
    @POST("tatcaccacchude.php")
    Call<List<ChuDe>> GetAllChuDe();
    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>>  GetTheloaitheochude(@Field("idchude") String idchude);
    @GET("tatcaAlbum.php")
    Call<List<AlBum>> GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>>  GetDanhSachBaiHatTheoAlBlum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String>  UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>>  GetSearchBaiHat(@Field("tukhoa") String tukhoa);
    @GET("allbaihat.php")
    Call<List<BaiHat>> GetAllBaiHat();

}
