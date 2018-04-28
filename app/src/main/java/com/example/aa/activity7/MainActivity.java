package com.example.aa.activity7;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<JsonInfo>{
    static String ENDPOINT = "https://api.myjson.com/bins/rj1g3";
    ProgressBar mProgressBar;
    TextView jsoninfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        jsoninfo = findViewById(R.id.jsoninfo);
        //TODO: check internet connectivity if there is internet execute the loader else show them a msg to check there connection
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        if (isConnected){
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(1, null, this);
        }
        else {
            mProgressBar.setVisibility(View.GONE);
            jsoninfo.setText(R.string.no_internet);
        }
    }

    @Override
    public android.support.v4.content.Loader onCreateLoader(int id, Bundle args) {
        mProgressBar.setVisibility(View.VISIBLE);
        return new Loader(MainActivity.this, MainActivity.ENDPOINT);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<JsonInfo> loader,JsonInfo data) {
        //TODO: fill the textView with the object information
        if (data != null) {
            // There is another solution by using more than one TextView for cityName, lon, lat and restNames
            // by changing the XML
            // but I use solve it this way to show the result
            jsoninfo.setText(data.toString());
        }
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader loader) {

    }

}