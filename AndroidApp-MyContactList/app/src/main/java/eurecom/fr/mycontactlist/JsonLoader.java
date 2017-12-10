package eurecom.fr.mycontactlist;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public class JsonLoader extends AsyncTaskLoader<List<Contact>> {
    public JsonLoader(Context context) {
        super(context);
        Log.i("main", "loader created");
    }
    @Override
    public List<Contact> loadInBackground() {
        Log.i("main", "loadInBackground");
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(
                "https://berkaykoksallab2project.appspot.com/contactlist?respType=json");
        HttpResponse resp;
        try {
            resp = client.execute(get);
        } catch (IOException e) {
            // TODO handle
            return null;
        }
        HttpEntity entity = resp.getEntity();
        String jsonString;
        try {
            InputStream stream = entity.getContent();
            Reader reader = new InputStreamReader(stream, "UTF-8");
            jsonString = readAll(reader);
        } catch (IOException e) {
            // TODO handle
            e.printStackTrace();
            return null;
        }
        if (jsonString != null) {
            List<Contact> result;
            try {
                JSONArray listOfContacts = new JSONArray(jsonString);
                int len = listOfContacts.length();
                result = new ArrayList<Contact>(len);
                Log.i("main", "json string len is " + len);
                for (int i = 0; i < len; i++) {
                    result.add(new Contact(listOfContacts.getJSONObject(i)));
                }
            } catch (JSONException e) {
                // TODO handle
                e.printStackTrace();
                return null;
            }
            return result;
        }else
            return null;
    }
    private String readAll(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder(4096);
        for (CharBuffer buf = CharBuffer.allocate(512); (reader.read(buf)) > -1;
             buf
                     .clear()) {
            builder.append(buf.flip());
        }
        return builder.toString();
    }
}
