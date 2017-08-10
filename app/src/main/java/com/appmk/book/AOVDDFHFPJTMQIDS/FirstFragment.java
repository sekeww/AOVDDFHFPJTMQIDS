package com.appmk.book.AOVDDFHFPJTMQIDS;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.ArrayList;
import java.util.List;

import static com.appmk.book.AOVDDFHFPJTMQIDS.SonsAdapter.CONTENT;


/**
 * Created by BEK on 19.02.2017.
 */

public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    private ListView listView;
    private List<Sons> mSons = new ArrayList<>();
    private Context context;
    private SonsListViewAdapter mAdapter;
    private TinyDB tinydb;

    private int z=0;
    private ProgressDialog pd;
    private LinearLayout linLayout;

    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        tinydb = new TinyDB(context);

        listView = (ListView) view.findViewById(R.id.listView);
        linLayout = (LinearLayout)view.findViewById(R.id.linLayout);

                listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });

        mAdapter = new SonsListViewAdapter(context, mSons);
        listView.setAdapter(mAdapter);

        downloadSons();

        return view;
    }




    public void downloadSons() {

        pd = new ProgressDialog(getActivity());
        pd.setTitle("Сервермен байланыс орнатылуда...");
        pd.setMessage("1...2...3...");
        pd.setCancelable(true);
        pd.setIndeterminate(false);
        pd.show();

        String whereClause;

//        if (MainActivity.QUERY_TEXT.isEmpty())
//            whereClause = "title LIKE '" + title + "%'";
//        else
            whereClause = "title LIKE '" + CONTENT[page] + "%'";

        Log.e(FirstFragment.class.getSimpleName(), "whereClause: " + whereClause);
        QueryOptions queryOptions = new QueryOptions();
        List<String> sortBy = new ArrayList<String>();
        sortBy.add( "title" );
        queryOptions.setSortBy( sortBy );
        queryOptions.setPageSize(50);

        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setWhereClause(whereClause);
        query.setQueryOptions( queryOptions );

        Backendless.Persistence.of(Sons.class).find(query,new AsyncCallback<BackendlessCollection<Sons>>() {

            @Override
            public void handleResponse(BackendlessCollection<Sons> response) {
                //Log.d("my_log",response+" my response is");

                System.out.println( "Total restaurants - " + response.getTotalObjects() );
                int size = response.getCurrentPage().size();
                System.out.println( "Loaded " + size + " restaurants in the current page" );
                if( size > 0 ) {
                    response.nextPage( this );
                }

                if(pd!=null)
                    pd.dismiss();

                displaySons( response.getData());
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                if(pd!=null)
                    pd.dismiss();
                Snackbar snackbar = Snackbar
                        .make(linLayout, "Ғаламтор қосылымы жоқ!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Қайталау", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                downloadSons();
                            }
                        });

                // Changing message text color
                snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();
                Log.e("my_log","some error "+ fault.getMessage());
            }


        });
    }
    private void displaySons(List<Sons> sons) {
        mSons.addAll(sons);
        mAdapter.notifyDataSetChanged();
    }

    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}