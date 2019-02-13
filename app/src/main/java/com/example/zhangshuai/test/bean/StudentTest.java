package com.example.zhangshuai.test.bean;

public class StudentTest {
    public static void main(String[] args){
        Student student = new Student();
        try {
            Student student1 = (Student) student.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

}
