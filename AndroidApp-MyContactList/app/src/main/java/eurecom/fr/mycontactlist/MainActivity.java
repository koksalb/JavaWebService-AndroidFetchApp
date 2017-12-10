package eurecom.fr.mycontactlist;


import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<List<Contact>> {

    SwipeRefreshLayout swipeContainer;

    @Override
    public Loader<List<Contact>> onCreateLoader(int id, Bundle args) {
        Log.i("main", "creating loader");
        JsonLoader loader = new JsonLoader(this);
        loader.forceLoad();
        return loader;



    }


    @Override
    public void onLoadFinished(Loader<List<Contact>> arg0, List<Contact> arg1) {
        Log.i("main", "onLoadFinished");

        ListViewAdapter_Contact adapter = new ListViewAdapter_Contact(this, R.layout.row_contact,arg1);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.invalidateViews();

        swipeContainer.setRefreshing(false);


    }





    @Override
    public void onLoaderReset(Loader<List<Contact>> arg0) {
        // TODO Auto-generated method stub
    }




    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView1);
        //getLoaderManager().initLoader(0, null,  this);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getLoaderManager().restartLoader(0, null,   MainActivity.this);
            }
        });





        Button btn_add = (Button) findViewById(R.id.btnadd);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);





            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("main", "onResume");
        getLoaderManager().restartLoader(0, null,   this);
    }


}

