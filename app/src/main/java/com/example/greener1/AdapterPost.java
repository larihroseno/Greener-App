package com.example.greener1;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.MyHolder>{

    Context context;
    List<ModelPost> postList;
    String userId;


    private DocumentReference postsRef;



    public AdapterPost(Context context, List<ModelPost> postList) {
        this.context=context;
        this.postList=postList;

        userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
       // postsRef= FirebaseFirestore.getInstance().document("Posts");

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflate layout row_post.xm


        View view= LayoutInflater.from(context).inflate(R.layout.row_posts, viewGroup, false);
        return new MyHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {

        //get data

        final String userId = postList.get(i).getUid();
        String email = postList.get(i).getEmail();
        String udp = postList.get(i).getDp();
        String userName = postList.get(i).getUserName();
        final String pId = postList.get(i).getpId();
        final String pTitle = postList.get(i).getpTitle();
        final String pDescription = postList.get(i).getpDescr();
        final String profileImage = postList.get(i).getProfileImage();
        String pTimeStamp = postList.get(i).getpTime();

        //convert timestamp to dd/mm/yyyy hh:mm am/pm

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
        String pTime = (String) DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();

        //set data

        myHolder.uNameTv.setText(userName);
        myHolder.pTimeTv.setText(pTime);
        myHolder.pTitleTv.setText(pTitle);
        myHolder.pDescriptionTv.setText(pDescription);



        //set user dp

        try{


            Picasso.get().load(udp).placeholder(R.drawable.ic_face_black).into(myHolder.uPictureIv);

        }
        catch (Exception e){

        }
        try {
            Picasso.get().load(profileImage).into(myHolder.pImageIv);


        }catch (Exception e){

        } //set post image
        // if there is no image i.e pImage equals("no image") then hide imageView
        if(profileImage.equals("noImage")){
            //hide imageview
            myHolder.pImageIv.setVisibility(View.GONE);

        }
        else{
            try {
                Picasso.get().load(profileImage).into(myHolder.pImageIv);


            }catch (Exception e){

            }

        }

        //handle button clicks
        myHolder.moreBtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Toast.makeText(context, "More", Toast.LENGTH_SHORT).show();

            }

            });
        //handle button clicks
        myHolder.likeBtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Likes", Toast.LENGTH_SHORT).show();

            }

        });
        //handle button clicks
        myHolder.commentBtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Comment", Toast.LENGTH_SHORT).show();

            }

        });
        //handle button clicks
        myHolder.shareBtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show();

            }

        });

        }

            @Override
    public int getItemCount() {
        return postList.size();
    }
    //view holder class

    class MyHolder extends RecyclerView.ViewHolder{
        //views from row_post.xml
        ImageView uPictureIv, pImageIv;
        TextView uNameTv, pTimeTv, pTitleTv, pDescriptionTv, pLikesTv;
        ImageButton moreBtn;
        Button likeBtn, commentBtn, shareBtn;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //init views
            uPictureIv=itemView.findViewById(R.id.userPictureIv);
            uNameTv=itemView.findViewById(R.id.userNameTv);
            pImageIv=itemView.findViewById(R.id.pImageIv);
            pTimeTv=itemView.findViewById(R.id.postTimeTv);
            pTitleTv=itemView.findViewById(R.id.pTitleTv);
            pDescriptionTv=itemView.findViewById(R.id.pDescriptionTv);
            pLikesTv=itemView.findViewById(R.id.pLikesTv);
            moreBtn=itemView.findViewById(R.id.moreBtn);
            likeBtn=itemView.findViewById(R.id.likesBtn);
            commentBtn=itemView.findViewById(R.id.commentBtn);
            shareBtn=itemView.findViewById(R.id.shareBtn);



        }
    }
}
