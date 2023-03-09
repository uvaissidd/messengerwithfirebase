package com.example.uvmessenger.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.uvmessenger.R;
import com.example.uvmessenger.databinding.ActivityMainBinding;
import com.example.uvmessenger.menu.CallsFragment;
import com.example.uvmessenger.menu.ChatFragment;
import com.example.uvmessenger.menu.StatusFragment;
import com.example.uvmessenger.view.contact.ContactActivity;
import com.example.uvmessenger.view.settings.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        setUpWithViewPager(binding.viewPager);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        setSupportActionBar(binding.toolbar);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeFabIcon(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private void setUpWithViewPager(ViewPager viewPager){
        MainActivity.sectionsPagerAdapter adapter= new sectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ChatFragment(),"Chats");
        adapter.addFragment(new StatusFragment(),"Status");
        adapter.addFragment(new CallsFragment(),"Calls");

        viewPager.setAdapter(adapter);

    }
    private static class sectionsPagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTextList = new ArrayList<>();

        public sectionsPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTextList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position){
            return mFragmentTextList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        switch (id){
            case R.id.menu_search:
                Toast.makeText(MainActivity.this,"Action Search", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_new_group:
                Toast.makeText(MainActivity.this,"Action New Group", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_new_broadcast:
                Toast.makeText(MainActivity.this,"Action New Broadcast", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_wa_web:
                Toast.makeText(MainActivity.this,"Action Web", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_starred_message:
                Toast.makeText(MainActivity.this,"Action Starred Message", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
    }
        return super.onOptionsItemSelected(item);
    }

    private void changeFabIcon(final int index){
        binding.fabAction.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (index) {
                    case 0:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_chat_24));
                        binding.fabAction.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(MainActivity.this, ContactActivity.class));
                            }
                        });
                        break;
                    case 1:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_camera_alt_24));
                        break;
                    case 2:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_call_24));
                        break;
                }
                binding.fabAction.show();
            }
        },400);

    }
}