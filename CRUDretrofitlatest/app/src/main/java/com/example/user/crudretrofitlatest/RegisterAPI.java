package com.example.user.crudretrofitlatest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by user on 6/17/2017.
 */

public interface RegisterAPI {

    @FormUrlEncoded
    @POST("delete.php")
    Call<Value> hapus(@Field("npm") String npm);

    @FormUrlEncoded
    @POST("update.php")
    Call<Value> ubah (@Field("npm") String npm,
                      @Field("nama") String nama,
                      @Field("kelas") String kelas,
                      @Field("sesi") String sesi);

    @FormUrlEncoded
    @POST("insert.php")
    Call<Value> daftar(@Field("npm") String npm,
                       @Field("nama") String nama,
                       @Field("kelas") String kelas,
                       @Field("sesi") String sesi);

    @GET("read.php")
    Call<Value> view();


}