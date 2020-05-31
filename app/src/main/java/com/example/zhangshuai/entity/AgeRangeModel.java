package com.example.zhangshuai.entity;

public class AgeRangeModel extends BaseAgeModel {
    public String range;

    public AgeRangeModel(String range) {
        super(BaseAgeModel.AGE_ITEM_TYPE_RANGE);
        this.range = range;
    }
}
