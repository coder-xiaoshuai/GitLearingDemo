package com.example.zhangshuai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhangshuai.entity.AgeNormalModel;
import com.example.zhangshuai.entity.AgePlaceholderModel;
import com.example.zhangshuai.entity.AgeRangeModel;
import com.example.zhangshuai.entity.BaseAgeModel;
import com.example.zhangshuai.gitlearingdemo.R;

import java.util.List;

public class AgeChoiceAdapter extends RecyclerView.Adapter {

    private List<BaseAgeModel> ageModels;
    private Context context;

    public AgeChoiceAdapter(Context context, List<BaseAgeModel> list) {
        this.ageModels = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BaseAgeModel.AGE_ITEM_TYPE_NORMAL) {
            return new NormalViewHolder(LayoutInflater.from(context).inflate(R.layout.item_age_normal, parent, false));
        }
        if (viewType == BaseAgeModel.AGE_ITEM_TYPE_PLACEHOLDER) {
            return new PlaceholderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_age_normal, parent, false));
        }
        if (viewType == BaseAgeModel.AGE_ITEM_TYPE_RANGE) {
            return new RangeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_age_normal, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseAgeModel model = ageModels.get(position);
        if (holder instanceof NormalViewHolder && model instanceof AgeNormalModel) {
            AgeNormalModel ageNormalModel = (AgeNormalModel) model;
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            ((NormalViewHolder) holder).textView.setText(ageNormalModel.age + "岁");
        }
        if (holder instanceof RangeViewHolder && model instanceof AgeRangeModel) {
            AgeRangeModel ageNormalModel = (AgeRangeModel) model;
            RangeViewHolder normalViewHolder = (RangeViewHolder) holder;
            ((RangeViewHolder) holder).textView.setText(ageNormalModel.range);
        }
        if (holder instanceof PlaceholderViewHolder && model instanceof AgePlaceholderModel) {
            ((PlaceholderViewHolder) holder).textView.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return ageModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        BaseAgeModel model = ageModels.get(position);
        return model.itemType;
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_age);
        }
    }

    class RangeViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public RangeViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_age);
        }
    }

    class PlaceholderViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public PlaceholderViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_age);
        }
    }
}
