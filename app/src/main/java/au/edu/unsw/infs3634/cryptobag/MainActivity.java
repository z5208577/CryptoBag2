package au.edu.unsw.infs3634.cryptobag;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.detail_container) != null) {
            mTwoPane = true;
        }

        RecyclerView mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Gson gson = new Gson();
        CoinLoreResponse XYZ = gson.fromJson(CoinLoreResponse.json, CoinLoreResponse.class);
        RecyclerView.Adapter mAdapter = new CoinAdapter(this, XYZ.getData(), mTwoPane);
        mRecyclerView.setAdapter(mAdapter);
    }
}
