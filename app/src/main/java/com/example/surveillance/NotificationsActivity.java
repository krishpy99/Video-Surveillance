package com.example.surveillance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class NotificationsActivity extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

       @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_notifs);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Global");
        mDatabase.keepSynced(true);
        mBlogList= findViewById(R.id.myrecyclerview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Blog,BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(Blog.class,R.layout.blog_row,BlogViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int i) {
                final Blog model2=model;
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle=new Bundle();
                        bundle.putString("title",model2.getTitle());
                        bundle.putString("desc",model2.getDesc());
                        Intent i = new Intent(NotificationsActivity.this,MapsActivity.class);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                });
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);

        }

        public static  class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        Button b;
        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
            b=mView.findViewById(R.id.explore);
        }
        public void setTitle(String title){
            TextView post_title= mView.findViewById(R.id.post_title);
            post_title.setText(title);

        }
        public void setDesc(String desc){
                TextView post_desc= mView.findViewById(R.id.post_desc);
                post_desc.setText(desc);

            }
            public void setImage(Context ctx,String image){
                ImageView post_Image= mView.findViewById(R.id.post_img);
                Picasso.with(ctx).load(image).into(post_Image);
            }


        }
    }

