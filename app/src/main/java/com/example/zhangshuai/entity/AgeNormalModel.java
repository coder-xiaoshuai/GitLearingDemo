package com.example.zhangshuai.entity;

public class AgeNormalModel extends BaseAgeModel {
    public int age;
    public boolean isSelected;

    public AgeNormalModel(int age, boolean isSelected) {
        super(BaseAgeModel.AGE_ITEM_TYPE_NORMAL);
        this.age = age;
        this.isSelected = isSelected;
    }
}
