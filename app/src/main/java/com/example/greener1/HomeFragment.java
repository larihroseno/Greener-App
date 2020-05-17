package com.example.greener1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.

 */
public class HomeFragment extends Fragment {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    RecyclerView recyclerView;
    List<ModelPost> postList;
    AdapterPost adapterPost;

    String userId;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //init

        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        user = fAuth.getCurrentUser();



        //recyclerView and its properties
        recyclerView = view.findViewById(R.id.postsRecyclerView);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());

        //show newest post first, for this load from last
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        //SETlAYOUT to recyclerview

        recyclerView.setLayoutManager(layoutManager);

        //init post List

        postList = new ArrayList<>();

        loadPosts();


        return view;
    }




    private void loadPosts() {
        // path  of all posts

        FirebaseFirestore db = FirebaseFirestore.getInstance();
               db.collection("Posts")
                .get()

        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                postList.clear();

                if (task.isSuccessful()) {


                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        ModelPost modelPost = document.toObject(ModelPost.class);
                        postList.add(modelPost);

                        //adapter
                        adapterPost = new AdapterPost(getActivity(), postList);



                        //set adapater to reclycerview
                        recyclerView.setAdapter(adapterPost);



                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }


            }
        });
    }


  private void searchPosts(final String searchQuery) {
      FirebaseFirestore db = FirebaseFirestore.getInstance();
      db.collection("Posts")
              .get()
              .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                                         @Override
                                         public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                             postList.clear();

                                             if (task.isSuccessful()) {


                                                 for (QueryDocumentSnapshot document : task.getResult()) {
                                                     Log.d(TAG, document.getId() + " => " + document.getData());
                                                     ModelPost modelPost = document.toObject(ModelPost.class);

                                                     if(modelPost.getpTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                                                             modelPost.getpDescr().toLowerCase().contains(searchQuery.toLowerCase()))
                                                     {


                                                         postList.add(modelPost);
                                                     }

                                                     //adapter
                                                     adapterPost = new AdapterPost(getActivity(), postList);
                                                     //sedt adapater to reclycerview

                                                     recyclerView.setAdapter(adapterPost);


                                                 }
                                             } else {
                                                 Log.w(TAG, "Error getting documents.", task.getException());
                                             }


                                         }
                                     });
  }

    private void checkUserStatus(){
        //get current user
        FirebaseUser user = fAuth.getCurrentUser();
        if (user != null){
            //user is signed in stay here
            //set email of logged in user
            //mProfileTv.setText(user.getEmail());
        }
        else {
            //user not signed in, go to main acitivity
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }
    }

   //inflate options menu

      @Override
      public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
          //inflating menu

          inflater.inflate(R.menu.home_menu, menu);


         //searchview to search posts by post title/description
          MenuItem  item = menu.findItem(R.id.action_search);
          SearchView searchView =(SearchView) MenuItemCompat.getActionView(item);

          //search listener

          searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

              @Override
              public boolean onQueryTextSubmit(String s) {
                  //called when user press search button
                  if(!TextUtils.isEmpty(s)){
                      searchPosts(s);

                  }else{
                      loadPosts();
                  }
                  return false;
              }

              @Override
              public boolean onQueryTextChange(String s) {
                  //called as and when user press any letter
                  if(!TextUtils.isEmpty(s)){
                      searchPosts(s);

                  }else{
                      loadPosts();
                  }
                  return false;
              }

          });


          super.onCreateOptionsMenu(menu, inflater);

      }

    //handle menu item clicks
    //The startActivity() method starts an instance of the Activity that's specified by the Intent.
    @Override
      public boolean onOptionsItemSelected(@NonNull MenuItem item) {
          int id = item.getItemId();

          if (id == R.id.home) {

              startActivity (new Intent(getActivity(), DashboardActivity.class));


          }
          if (id == R.id.profile) {

              startActivity (new Intent(getActivity(), SettingsUser.class));


          }
          if (id == R.id.challenge) {
              startActivity (new Intent(getActivity(), UserAreaActivity.class));


          }
          if (id == R.id.logOut) {
              fAuth.signOut();
          }




          return super.onOptionsItemSelected(item);


  }

}
