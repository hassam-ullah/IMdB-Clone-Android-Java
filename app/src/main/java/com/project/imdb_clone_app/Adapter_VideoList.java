package com.project.imdb_clone_app;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_VideoList extends RecyclerView.Adapter <Adapter_VideoList.VideoViewHolder>  {

    List<Model_VideoCard> videoCards;


    public Adapter_VideoList(List<Model_VideoCard> videoCards){
        this.videoCards = videoCards;
    }

    @NonNull

    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_video, parent, false);
        VideoViewHolder videoViewHolder = new VideoViewHolder(view);
        return videoViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {


        Model_VideoCard videoCard = videoCards.get(position);
        holder.img.setImageResource(videoCard.getImg());
        holder.title.setText(videoCard.getTitle());
    }

    @Override
    public int getItemCount() {
        return videoCards.size();

    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {


        ImageView img;
        TextView title;


        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            //hooks

            img = itemView.findViewById(R.id.video_card_img);
            title = itemView.findViewById(R.id.video_card_title);

        }


    }

}

