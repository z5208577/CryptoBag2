package au.edu.unsw.infs3634.cryptobag;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.text.NumberFormat;

public class DetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    private Coin mCoin;

    public DetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments().containsKey(ARG_ITEM_ID)) {
           mCoin = Coin.getCoin(getArguments().getString(ARG_ITEM_ID));
            this.getActivity().setTitle(mCoin.getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        if(mCoin != null) {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            ((TextView) rootView.findViewById(R.id.tvName)).setText(mCoin.getName());
            ((TextView) rootView.findViewById(R.id.tvSymbol)).setText(mCoin.getSymbol());
            ((TextView) rootView.findViewById(R.id.tvValueField)).setText(mCoin.getPriceUsd());
            ((TextView) rootView.findViewById(R.id.tvChange1hField)).setText(mCoin.getPercentChange1h() + " %");
            ((TextView) rootView.findViewById(R.id.tvChange24hField)).setText(mCoin.getPercentChange24h() + " %");
            ((TextView) rootView.findViewById(R.id.tvChange7dField)).setText(mCoin.getPercentChange7d() + " %");
            ((TextView) rootView.findViewById(R.id.tvMarketcapField)).setText(mCoin.getMarketCapUsd());
            ((TextView) rootView.findViewById(R.id.tvVolumeField)).setText(formatter.format(mCoin.getVolume24()));
            ((ImageView) rootView.findViewById(R.id.ivSearch)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchCoin(mCoin.getName());
                }
            });
        }

        return rootView;
    }

    private void searchCoin(String name) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + name));
        startActivity(intent);
    }


}
