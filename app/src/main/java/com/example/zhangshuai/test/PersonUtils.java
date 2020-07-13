package com.example.zhangshuai.test;

import com.example.zhangshuai.test.bean.Person;

public class PersonUtils {

    public static boolean isSameAge(Person person1, Person person2) {

        return person1 != null && person2 != null && person1.getAge() == person2.getAge();
    }

    public static boolean isSameName(Person person1, Person person2) {
        return person1 != null && person2 != null && person1.getName().equals(person2.getName());
    }
}
