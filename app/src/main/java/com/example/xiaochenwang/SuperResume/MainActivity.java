package com.example.xiaochenwang.SuperResume;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiaochenwang.SuperResume.model.Education;
import com.example.xiaochenwang.SuperResume.util.DateUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static List<Education> educations;
    private static final int REQ_CODE_EDIT_EDUCATION = 100;
    private static final int REQ_CODE_EDIT_EXPERIENCE = 101;
    private static final int REQ_CODE_EDIT_PROJECT = 102;
    private static final int REQ_CODE_EDIT_BASIC_INFO = 103;

    private static final String MODEL_EDUCATIONS = "educations";
    private static final String MODEL_EXPERIENCES = "experiences";
    private static final String MODEL_PROJECTS = "projects";
    private static final String MODEL_BASIC_INFO = "basic_info";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQ_CODE_EDIT_EDUCATION:
                    String educationId = data.getStringExtra(EducationEditActivity.KEY_EDUCATION_ID);
                    if (educationId == null) {
                        Education education = data.getParcelableExtra(EducationEditActivity.KEY_EDUCATION);
                        educations.add(education);
                        setupEducations();
                    }

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        educations = new ArrayList<>();

        Education ed1 = new Education();
        ed1.setSchool("cmu");
        ed1.setMajor("cs");
        ed1.setStartDate(DateUtils.stringToDate("06/1992"));
        ed1.setEndDate(DateUtils.stringToDate("08/2012"));
        List<String> list = new ArrayList<>();
        list.add("cs algorithm");
        list.add("architecture");
        ed1.setCourses(list);

        educations.add(ed1);

        setupEducations();

        ImageButton addEducationBtn = (ImageButton) findViewById(R.id.add_education_btn);
        addEducationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDIT_EDUCATION);
            }
        });

    }



    private void setupEducations() {
        LinearLayout educationsLayout = (LinearLayout) findViewById(R.id.education_list);
        educationsLayout.removeAllViews();
        for (Education education : educations) {
            View educationView = getLayoutInflater().inflate(R.layout.education_item,null);
            setupEducation(educationView, education);
            educationView.findViewById(R.id.education_edit_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                    startActivityForResult(intent, 1);
                }
            });
            educationsLayout.addView(educationView);
        }
    }

    private void setupEducation(View educationView, Education education) {
        String dateString = DateUtils.dateToString(education.getStartDate()) + " ~ " + DateUtils.dateToString(education.getEndDate());
        ((TextView)educationView.findViewById(R.id.education_title)).setText(
                education.getSchool() +" " + education.getMajor() +" " + dateString);
        ((TextView) educationView.findViewById(R.id.education_courses)).setText(
                formatItems(education.getCourses()));
    }

    public static String formatItems(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item: items) {
            sb.append(' ').append('-').append(' ').append(item).append('\n');
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


}
