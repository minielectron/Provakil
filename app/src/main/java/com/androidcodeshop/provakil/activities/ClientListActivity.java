package com.androidcodeshop.provakil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidcodeshop.provakil.R;
import com.androidcodeshop.provakil.adapters.ClientListAdapter;
import com.androidcodeshop.provakil.data.ClientDataList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.androidcodeshop.provakil.activities.ClientFormActivity.IS_REFRESH;

public class ClientListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.client_list_recycler_view)
    RecyclerView clientListRecyclerView;
    private ClientListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setRecyclerView();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClientFormActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void setRecyclerView() {
        adapter = new ClientListAdapter(this, ClientDataList.getStoredData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        clientListRecyclerView.setLayoutManager(linearLayoutManager);
        clientListRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            if (intent.getExtras().get(IS_REFRESH).equals("yes"))
                adapter.notifyDataSetChanged();
        }
    }
}
