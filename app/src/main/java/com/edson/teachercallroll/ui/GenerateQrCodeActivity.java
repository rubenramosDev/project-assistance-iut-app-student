package com.edson.teachercallroll.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edson.teachercallroll.R;
import com.edson.teachercallroll.viewmodel.GenerateQrCodeViewModel;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQrCodeActivity extends AppCompatActivity {

    private ImageView imgVQRCode;
    private TextView txtVCreateSheetDate;
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    private GenerateQrCodeViewModel viewModel;
    private String idSheet = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr_code);
        setUpComponents();
        createQR();
    }

    public void setUpComponents() {
        viewModel = new ViewModelProvider(GenerateQrCodeActivity.this).get(GenerateQrCodeViewModel.class);
        imgVQRCode = findViewById(R.id.imgVQRCode);
        txtVCreateSheetDate = findViewById(R.id.txtVCreateSheetDate);
    }

    public void createQR() {
        try {
            Intent intent = getIntent();
            idSheet = intent.getStringExtra("idSheet");
//            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(intent.getStringExtra("feuilleDate"));
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            txtVCreateSheetDate.setText(intent.getStringExtra("dateSheet"));
            qrgEncoder = new QRGEncoder(idSheet, null, QRGContents.Type.TEXT, 500);
            qrgEncoder.setColorBlack(Color.parseColor("#264400"));
            bitmap = qrgEncoder.getBitmap();
            imgVQRCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkAssistanceSheet(View view) {
        SharedPreferences shPref = getApplicationContext().getSharedPreferences("TeacherCallRoll_ShPref", 0);
        String token = shPref.getString("auth_token", null);
        viewModel.getStudentList(token, Long.parseLong(idSheet)).observe(GenerateQrCodeActivity.this, (Observer<String>) jsonList -> {
            if (jsonList != null) {
                Intent intent = new Intent(GenerateQrCodeActivity.this, StudentsInSheetActivity.class);
                intent.putExtra("idSheet", Long.parseLong(idSheet));
                startActivity(intent);
            }
        });
    }
}