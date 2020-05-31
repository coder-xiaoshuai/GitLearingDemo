package com.example.zhangshuai.entity;

public class BaseAgeModel {
//    public AgeItemType itemType;
    public static final int AGE_ITEM_TYPE_NORMAL = 1;
    public static final int AGE_ITEM_TYPE_RANGE = 2;
    public static final int AGE_ITEM_TYPE_PLACEHOLDER = 3;

    public int itemType;


    public BaseAgeModel(int itemType) {
//        this.itemType = itemType;
        this.itemType = itemType;
    }

    public enum AgeItemType {
        NORMAL, AGE_RANGE, PLACEHOLDER
    }
}
