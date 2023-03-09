package com.example.uvmessenger.view.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.uvmessenger.R;
import com.example.uvmessenger.adapter.ContactAdapter;
import com.example.uvmessenger.databinding.ActivityContactBinding;
import com.example.uvmessenger.model.user.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private static final String TAG = "ContactActivity";
    private ActivityContactBinding binding;
    private List<Users> list = new ArrayList<>();
    private ContactAdapter adapter;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore  firestore;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_contact);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
        if (firebaseUser != null){
            getContactList();
        }
    }

    private void getContactList() {
        firestore.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot snapshot: queryDocumentSnapshots) {
                    String userID = snapshot.getString("userID");
                    String userName = snapshot.getString("userName");
                    String imageUri = snapshot.getString("imageProfile");
                    String desc = snapshot.getString("bio");

                    Users user = new Users();
                    user.setUserID(userID);
                    user.setBio(desc);
                    user.setUserName(userName);
                    user.setImageProfile(imageUri);

                    if (userID != null && !userID.equals(firebaseUser.getUid())) {
                        list.add(user);
                    }
                }
                adapter = new ContactAdapter(list,ContactActivity.this);
                binding.recyclerView.setAdapter(adapter);
            }
        });
    }
}