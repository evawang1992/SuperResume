package com.example.xiaochenwang.SuperResume.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;
import java.lang.*;
import java.util.UUID;

public class Education implements Parcelable{
    public String school, major;
    public List<String> courses;
    public Date startDate , endDate;
    public String id;

    public Education() {
        id = UUID.randomUUID().toString();
    }

    public Education(Parcel par) {
        id = par.readString();
        school = par.readString();
        major = par.readString();
        startDate = new Date(par.readLong());
        endDate = new Date(par.readLong());
        courses = par.createStringArrayList();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(school);
        parcel.writeString(major);
        parcel.writeLong(startDate.getTime());
        parcel.writeLong(endDate.getTime());
        parcel.writeStringList(courses);
    }

    public static final Creator<Education> CREATOR = new Creator<Education>() {
        @Override
        public Education createFromParcel(Parcel parcel) {
            return new Education(parcel);
        }

        @Override
        public Education[] newArray(int i) {
            return new Education[i];
        }
    };

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> c) {
        this.courses = c;

    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
