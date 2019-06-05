package com.example.zhangshuai.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

public class DefTest {
    @IntDef({ Sex.female, Sex.man, Sex.unknown })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Sex {
        int female = 0;
        int man = 1;
        int unknown = 2;
    }

    static final int level1 = 1;
    static final int level2 = 2;
    static final int level3 = 3;
    static final int level4 = 4;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ level1, level2, level3, level4 })
    public @interface Level {
    }

    public void setSex(@Sex int sex) {
        System.out.println("sex" + sex);
    }

    public void setLevel(@Level int level) {
        System.out.println("level" + level);
    }

    public static void main(String[] args) {
        DefTest defTest = new DefTest();
        defTest.setSex(Sex.female);
        defTest.setLevel(level1);
    }
}
