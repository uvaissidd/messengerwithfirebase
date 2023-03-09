package com.example.uvmessenger.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uvmessenger.R;
import com.example.uvmessenger.adapter.callListAdapter;
import com.example.uvmessenger.adapter.chatListAdapter;
import com.example.uvmessenger.model.callList;

import java.util.ArrayList;
import java.util.List;

public class CallsFragment extends Fragment {


    public CallsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_calls, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        List<callList> lists= new ArrayList<>();

//        recyclerView.setAdapter(new callListAdapter(lists,getContext()));
        return view;

    }
}