package com.example.uvmessenger.menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uvmessenger.R;
import com.example.uvmessenger.adapter.chatListAdapter;
import com.example.uvmessenger.databinding.FragmentChatBinding;
import com.example.uvmessenger.model.chatList;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatFragment extends Fragment {

    private static final String TAG = "ChatFragment";

    public ChatFragment() {
        // Required empty public constructor
    }

    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    private FirebaseFirestore firestore;
    private  List<chatList> list;
    private Handler handler= new Handler();

    private FragmentChatBinding binding;

    private ArrayList<String> allUserID;

    private chatListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_chat, container, false);

        list=new ArrayList<>();
        allUserID = new ArrayList<>();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new chatListAdapter(list, getContext());
        binding.recyclerView.setAdapter(adapter);

//        list.add(new chatList("11","Shah rukh khan","hello friend","13/3/2000","https://cdn.dnaindia.com/sites/default/files/styles/full/public/2021/04/01/967300-shah-rukh-khan.jpg"));
//        list.add(new chatList("11","Salman Khan","hello friend","13/3/2000","https://www.bollywoodhungama.com/wp-content/uploads/2021/05/Salman-Khan-helps-18-year-old-boy-from-Karnataka-with-ration-and-educational-equipment-after-his-father-succumbs-to-COVID-19.jpg"));
//        list.add(new chatList("11","Amir Khan","hello friend","13/3/2000","https://images.news18.com/ibnlive/uploads/2021/02/1612346460_aamir_khan_4k-2880x1800.jpg"));
//        list.add(new chatList("11","Nora Fatehi","hello friend","13/3/2000","https://static.toiimg.com/thumb/msid-74994072,width-800,height-600,resizemode-75,imgsize-158648,pt-32,y_pad-40/74994072.jpg"));
//
//        recyclerView.setAdapter(new chatListAdapter(list,getContext()));

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();
        firestore = FirebaseFirestore.getInstance();



        if (firebaseUser != null) {
            getChatList();
        }
        return binding.getRoot();
    }

    private void getChatList() {
        binding.progressCircular.setVisibility(View.VISIBLE);
        reference.child("childList");
        reference.child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                allUserID.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String userID = Objects.requireNonNull(snapshot.child("chatID").getValue()).toString();
                    Log.d(TAG,"onDataChange: userId"+userID);

                    binding.progressCircular.setVisibility(View.GONE);
                    allUserID.add(userID);
                }
                getUserInfo();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getUserInfo(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                for (String userID: allUserID){
                    firestore.collection("userID").document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Log.d(TAG, "onSuccess: ddd"+documentSnapshot.getString("userNamw"));
                            try {
                                chatList chat = new chatList(
                                        documentSnapshot.getString("userID"),
                                        documentSnapshot.getString("userName"),
                                        "this is description...",
                                        "",
                                        documentSnapshot.getString("imageProfile")
                                );
                                list.add(chat);
                            }catch (Exception e){
                                Log.d(TAG, "onSuccess: "+e.getMessage());
                            }
                            if (adapter != null){
                                adapter.notifyItemInserted(0);
                                adapter.notifyDataSetChanged();

                                Log.d(TAG, "onSuccess: adapter"+adapter.getItemCount());
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: error L "+e.getMessage());
                        }
                    });
                }
            }
        });
    }

}