package com.edson.teachercallroll.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.edson.teachercallroll.apidata.network.RetroClient;
import com.edson.teachercallroll.model.FormationDto;
import com.edson.teachercallroll.model.StudentDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerateQrCodeViewModel extends ViewModel {

    MutableLiveData<List<StudentDto>> studentList;
    MutableLiveData<String> studentJsonList = new MutableLiveData<>();
    boolean flag;


    public MutableLiveData<String> getStudentList(String token, long idSheet) {
        Call<ResponseBody> call = RetroClient.getInstance().getApi().getStudentInSheet(token, idSheet);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JsonParser parser = new JsonParser();
                    if (response.isSuccessful()) {
//                        Type formationsListType = new TypeToken<ArrayList<StudentDto>>() {
//                        }.getType();
//                        Gson gson = new Gson();
//                        studentList = gson.fromJson(response.body().string(), formationsListType);
                        studentJsonList.postValue(response.body().string());
                        Log.i("JSONCALL", response.body().string());
//                            message.postValue("Ok !");//response.message() ?
                    } else {
                        JsonObject jso = (JsonObject) parser.parseString(response.errorBody().string());
//                            message.postValue(jso.get("message").getAsString());//errorBody
                        studentJsonList.postValue(null);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return studentJsonList;
    }

//    public boolean checkSheet(String token, String id) {
//        Call<ResponseBody> call = RetroClient.getInstance().getApi().checkAssistanceSheet(token);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    flag = true;
//                } else {
//                    flag = false;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                flag = false;
//            }
//        });
//        return flag;
//    }
}
