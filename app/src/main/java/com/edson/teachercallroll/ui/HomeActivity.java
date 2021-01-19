package com.edson.teachercallroll.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.edson.teachercallroll.R;

public class HomeActivity extends AppCompatActivity {

    TextView txtVLastName;
    TextView txtVFirstName;
    CardView crdVGenerateSheet;
    CardView crdVManageSheet;
    CardView crdVCreateTeacher;
    CardView crdVSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupComponents();
        setCompleteName();
    }

    private void setupComponents() {
        txtVLastName = findViewById(R.id.txtVLastName);
        txtVFirstName = findViewById(R.id.txtVFirstName);
        crdVGenerateSheet = findViewById(R.id.crdVGenerateSheet);
        crdVManageSheet = findViewById(R.id.crdVManageSheet);
        crdVCreateTeacher = findViewById(R.id.crdVCreateTeacher);
        crdVSettings = findViewById(R.id.crdVSettings);
    }

    public void startAssistsActivity(View view) {
        Intent intent = new Intent(view.getContext(), SheetsActivity.class);
        startActivity(intent);
    }

    public void startSelectGroupActivity(View view) {
        Intent intent = new Intent(view.getContext(), SelectGroupActivity.class);
        startActivity(intent);
    }

    public void startStudentsInSheetsActivity(View view) {
        Intent intent = new Intent(view.getContext(), StudentsInSheetActivity.class);
        startActivity(intent);
    }

    public void setCompleteName(){
        SharedPreferences shPref = getApplicationContext().getSharedPreferences("TeacherCallRoll_ShPref", 0);
        txtVLastName.setText(shPref.getString("last_name", null));
        txtVFirstName.setText(shPref.getString("first_name", null));
    }
}