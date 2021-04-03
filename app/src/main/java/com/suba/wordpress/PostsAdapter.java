package com.suba.wordpress;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    List<Post> allPosts;

    public PostsAdapter(List<Post> allPosts) {
        this.allPosts = allPosts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.postTitle.setText(allPosts.get(position).getTitle());
        Picasso.get().load(allPosts.get(position).getFeature_image()).into(holder.postImage);

        holder.view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(v.getContext(),DetailActivity.class);
                i.putExtra("title", allPosts.get(position).getTitle());
                i.putExtra("content", allPosts.get(position).getContent());
                i.putExtra("featured_media_src_url", allPosts.get(position).getFeature_image());
                v.getContext().startActivity(i);
            }

        });
    }

    @Override
    public int getItemCount() {
        return allPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView postImage;
        TextView postTitle;
        View view;
        public ViewHolder(@NonNull View itemView){

            super(itemView);
            view = itemView;
            postImage = itemView.findViewById(R.id.post_image);
            postTitle = itemView.findViewById(R.id.post_title);
        }
    }
}
