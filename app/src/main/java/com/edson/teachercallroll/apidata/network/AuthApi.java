package com.edson.teachercallroll.apidata.network;

import com.edson.teachercallroll.apidata.request.CreateSheetRequest;
import com.edson.teachercallroll.apidata.request.LoginRequest;
import com.edson.teachercallroll.model.StudentDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AuthApi {

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    Call<ResponseBody> userLogin(
            @Body LoginRequest body
    );

    @Headers("Content-Type: application/json")
    @GET("api/assistance-sheet/view/{idSheet}")
    Call<ResponseBody> getStudentInSheet(@Header("Authorization") String token,
                                         @Path("idSheet") long idSheet);

    @Headers("Content-Type: application/json")
    @POST("api/assistance-sheet/create")
    Call<ResponseBody> addAssistanceSheet(@Header("Authorization") String token, @Body CreateSheetRequest body);

    @FormUrlEncoded
    @GET("api/assistance-sheet/view/{idSheet}")
    Call<ResponseBody> checkAssistanceSheet(@Header("Authorization") String token,
                                            @Path("idSheet") long idSheet);

    @DELETE("api/assistance-sheet/delete/{id}/{identifierNumber}")
    Call<ResponseBody> deleteStudentFromAssistanceSheet(@Header("Authorization") String token,
                                                        @Path("id") long idSheet,
                                                        @Path("identifierNumber") int identifierNumber);

    @PUT("api/assistance-sheet/update/add-student/{idSheet}/{identifierNumber}")
    Call<ResponseBody> addStudentAssistanceSheet(@Header("Authorization") String token,
                                                 @Path("idSheet") long idSheet,
                                                 @Path("identifierNumber") int identifierNumber);

    //Not yet
    @GET("getFormations")
    Call<ResponseBody> getFormations();


}
