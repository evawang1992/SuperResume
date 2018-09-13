package com.example.xiaochenwang.SuperResume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xiaochenwang.SuperResume.R;
import com.example.xiaochenwang.SuperResume.model.Education;
import com.example.xiaochenwang.SuperResume.util.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EducationEditActivity extends AppCompatActivity {
    public static final String KEY_EDUCATION = "education";
    public static final String KEY_EDUCATION_ID = "education_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_education_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home) {

            finish();
            return true;
        }
        else if (item.getItemId() == R.id.action_save) {
            saveAndExit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAndExit() {
        Education data = new Education();
        if (data == null) {
            data = new Education();
        }
        data.setSchool(((EditText) findViewById(R.id.education_school_edit)).getText().toString());
        data.setMajor(((EditText) findViewById(R.id.education_major_edit)).getText().toString());
        data.setStartDate(DateUtils.stringToDate(
                ((TextView) findViewById(R.id.education_startdate_edit)).getText().toString()));
        data.setEndDate(DateUtils.stringToDate(
                ((TextView) findViewById(R.id.education_enddate_edit)).getText().toString()));
        data.courses = Arrays.asList(TextUtils.split(
                ((EditText) findViewById(R.id.education_courses_edit)).getText().toString(), "\n"));


        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EDUCATION, data);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }


}
