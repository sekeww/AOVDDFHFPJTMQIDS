package com.appmk.book.AOVDDFHFPJTMQIDS;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SonFragment extends Fragment {

    private static final String ARG_SON = "son";

    private String mSon;

    public SonFragment() {
        // Required empty public constructor
    }

    public static SonFragment newInstance(String son) {
        SonFragment fragment = new SonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SON, son);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSon = getArguments().getString(ARG_SON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_son, container, false);

        TextView mSonTextView = (TextView) v.findViewById(R.id.sonTextView);

        mSonTextView.setText(mSon);

        return v;
    }

}
