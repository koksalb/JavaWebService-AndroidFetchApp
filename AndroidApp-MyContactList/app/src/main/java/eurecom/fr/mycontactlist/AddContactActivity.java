package eurecom.fr.mycontactlist;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        String name = ""; String phone=""; String email = "";String pict = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png";

        Serializable incoming = getIntent().getSerializableExtra("name");
        if(incoming != null){
        name = incoming.toString();}
        incoming = getIntent().getSerializableExtra("phone");
        if(incoming != null){
        phone = incoming.toString();}
        incoming = getIntent().getSerializableExtra("mail");
        if(incoming != null){
        email = incoming.toString();}

        incoming = getIntent().getSerializableExtra("pict");
        if(incoming != null){
            pict = incoming.toString();}



        ((EditText)findViewById(R.id.txtname)).setText(name);
        ((EditText)findViewById(R.id.txtphone)).setText(phone);
        ((EditText)findViewById(R.id.txtmail)).setText(email);


        if(name.length() > 0) {
            ((EditText)findViewById(R.id.txtname)).setEnabled(false);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       Button btnadd = (Button) findViewById(R.id.btnadd);
       btnadd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String name = ((EditText) findViewById(R.id.txtname)).getText().toString();
               String phone = ((EditText) findViewById(R.id.txtphone)).getText().toString();
               String mail = ((EditText) findViewById(R.id.txtmail)).getText().toString();
               String pict = ((EditText) findViewById(R.id.txtpict)).getText().toString();
               if (pict .length() < 3){
                   pict = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png";
               }
               try {
                   HttpPost post = new HttpPost("https://berkaykoksallab2project.appspot.com/save");
                   List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                   nvps.add(new BasicNameValuePair("name", name));
                   nvps.add(new BasicNameValuePair("phone", phone));
                   nvps.add(new BasicNameValuePair("email", mail));
                   nvps.add(new BasicNameValuePair("pict", pict));

                   post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

                   DefaultHttpClient httpClient = new DefaultHttpClient();
                   HttpResponse response = httpClient.execute(post);
               }
               catch(Exception e)
               {
               }

               finish();



           }
       });


    }

}
