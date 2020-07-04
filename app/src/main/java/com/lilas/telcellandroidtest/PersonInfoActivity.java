package com.lilas.telcellandroidtest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lilas.telcellandroidtest.adapter.PersonInfoAdapter;
import com.lilas.telcellandroidtest.constants.AppConstants;
import com.lilas.telcellandroidtest.model.PersonInfo;

import java.util.List;

public class PersonInfoActivity extends AppCompatActivity {
    private static final String TAG = PersonInfoActivity.class.getCanonicalName();

    private RecyclerView mPersonInfoListView;
    private TextView mTvInfoText;
    private PersonInfoAdapter mPersonInfoAdapter;
    private List<PersonInfo> mPersonList;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        mPersonInfoListView = findViewById(R.id.viewPersonInfo);
        mTvInfoText = findViewById(R.id.tvInfoText);

        mPersonList = getIntent().getParcelableArrayListExtra(AppConstants.INFO_LIST_KEY);
        mTitle = getIntent().getStringExtra(AppConstants.INFO_TITLE_KEY);

        setTitle(mTitle);
        mPersonInfoAdapter = new PersonInfoAdapter(this);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(PersonInfoActivity.this, LinearLayoutManager.VERTICAL, false);
        mPersonInfoListView.setLayoutManager(horizontalLayoutManager);
        mPersonInfoListView.setHasFixedSize(true);
        mPersonInfoListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mPersonInfoListView.setAdapter(mPersonInfoAdapter);


        if (mPersonList.isEmpty()) {
            Log.i(TAG, "person list is empty");
            mTvInfoText.setVisibility(View.VISIBLE);
        } else {
            mTvInfoText.setVisibility(View.GONE);
            Log.i(TAG, "person list ----> " + mPersonList.toString());
            mPersonInfoAdapter.setPersonList(mPersonList);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPersonInfoListView.setAdapter(null);
    }
}
