package com.project.imdb_clone_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_Achievement extends RecyclerView.Adapter <Adapter_Achievement.AchievementViewHolder> implements ItemClickListener {

    List<Model_Achievement> achievements;
    private ItemClickListener clickListener;

    public Adapter_Achievement(List<Model_Achievement> achievements){
        this.achievements = achievements;
    }

    @NonNull
    @Override
    public AchievementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_achievement, parent, false);
        AchievementViewHolder achievementViewHolder = new AchievementViewHolder(view);
        return achievementViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AchievementViewHolder holder, int position) {
        Model_Achievement achievement = achievements.get(position);
        holder.award_name.setText(achievement.getAward());
        holder.award_year.setText(achievement.getYear());
    }

    @Override
    public int getItemCount() {
        return achievements.size();

    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public void onClick(View view, int position) {
    }

    public class AchievementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView award_name;
        TextView award_year;

        public AchievementViewHolder(@NonNull View itemView) {
            super(itemView);

            award_name = itemView.findViewById(R.id.actor_award);
            award_year = itemView.findViewById(R.id.actor_award_year);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

}