package com.example.tripit.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripit.Interface.GuideClickListner;
import com.example.tripit.R;

public class GuideViwHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtGuideName , txtGuideContactNumber , txtGuideAge , txtGuideExperiance , txtGuideAmountPerDay ,txtguidesCatagory;
    public ImageView guideImageView;
    public GuideClickListner guidelistner;


    public GuideViwHolder(@NonNull View itemView) {
        super(itemView);

        guideImageView = (ImageView) itemView.findViewById(R.id.myguide_image);
        txtGuideName = (TextView) itemView.findViewById(R.id.guideName);
        txtGuideContactNumber = (TextView) itemView.findViewById(R.id.guideConNumber);
        txtGuideAge = (TextView) itemView.findViewById(R.id.guideAge);
        txtGuideExperiance = (TextView) itemView.findViewById(R.id.guideExperiance);
        txtGuideAmountPerDay = (TextView) itemView.findViewById(R.id.guideAmount);
        txtguidesCatagory = (TextView) itemView.findViewById(R.id.guidesCatagory);

    }

    public void setGuideClickListner(GuideClickListner listner)
    {

        this.guidelistner = listner;

    }

    @Override
    public void onClick(View view)
    {
        guidelistner.onClick(view, getAdapterPosition(),false);

    }
}
