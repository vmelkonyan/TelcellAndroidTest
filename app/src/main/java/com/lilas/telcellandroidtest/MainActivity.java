package com.lilas.telcellandroidtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lilas.telcellandroidtest.adapter.PersonListAdapter;
import com.lilas.telcellandroidtest.callback.GetPersonListCallback;
import com.lilas.telcellandroidtest.constants.AppConstants;
import com.lilas.telcellandroidtest.model.Person;
import com.lilas.telcellandroidtest.repo.PersonRepo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PersonListAdapter.ItemClickListener {
    private static final String TAG = MainActivity.class.getCanonicalName();

    private RecyclerView mPersonListView;
    private TextView mTvInfoText;
    private PersonListAdapter mPersonListAdapter;
    private List<Person> mPersonList;
    private ProgressBar mPbLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPersonListView = findViewById(R.id.viewPerson);
        mTvInfoText = findViewById(R.id.tvInfoText);
        mPbLoad = findViewById(R.id.pbLoad);
        mPbLoad.setVisibility(View.VISIBLE);

        mPersonListAdapter = new PersonListAdapter(this);
        mPersonListAdapter.setClickListener(this);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mPersonListView.setLayoutManager(horizontalLayoutManager);
        mPersonListView.setHasFixedSize(true);
        mPersonListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mPersonListView.setAdapter(mPersonListAdapter);
        PersonRepo personRepo = new PersonRepo();
        personRepo.getPersonList(new GetPersonListCallback() {
            @Override
            public void onSuccess(List<Person> personList) {
                mPbLoad.setVisibility(View.GONE);
                if (personList.isEmpty()) {
                    mPersonListView.setVisibility(View.GONE);
                    mTvInfoText.setVisibility(View.VISIBLE);
                } else {
                    mPersonList = personList;
                    mPersonListView.setVisibility(View.VISIBLE);
                    mTvInfoText.setVisibility(View.GONE);
                    Log.i(TAG, "person list ----> " + mPersonList.toString());
                    mPersonListAdapter.setmPersonList(personList);
                }
            }

            @Override
            public void onFailure(String message) {
                Log.e(TAG, message);
                mPbLoad.setVisibility(View.GONE);
                mPersonListView.setVisibility(View.GONE);
                mTvInfoText.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, PersonInfoActivity.class);
        intent.putParcelableArrayListExtra(AppConstants.INFO_LIST_KEY, (ArrayList<? extends Parcelable>) mPersonList.get(position).getPersonInfoList());
        intent.putExtra(AppConstants.INFO_TITLE_KEY, mPersonList.get(position).getFirstName());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPersonListView.setAdapter(null);
    }
}