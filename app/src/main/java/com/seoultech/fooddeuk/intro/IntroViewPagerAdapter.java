package com.seoultech.fooddeuk.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seoultech.fooddeuk.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IntroViewPagerAdapter extends RecyclerView.Adapter<IntroViewPagerAdapter.IntroViewHolder> {

    Context mContext;
    List<ScreenItem> mListScreen;

    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    public static class IntroViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        ImageView imgCustomer;

        public IntroViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imgCustomer = itemView.findViewById(R.id.imgCustomer);
        }

        public void bind(String title, String description, int screenImg) {
            tvTitle.setText(title);
            tvDescription.setText(description);
            imgCustomer.setImageResource(screenImg);
        }
    }

    @NonNull
    @NotNull
    @Override
    public IntroViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_guide_screen,  parent, false);
        IntroViewHolder introViewHolder = new IntroViewHolder(view);
        return introViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull IntroViewHolder introViewHolder, int position) {
        introViewHolder.bind(mListScreen.get(position).getTitle(),
                mListScreen.get(position).getDescription(),
                mListScreen.get(position).getScreenImg());
    }

    @Override
    public int getItemCount() {
        return mListScreen.size();
    }
}