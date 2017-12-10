package eurecom.fr.mycontactlist;

/**
 * Created by koksa on 1.12.2017.
 */


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ListViewAdapter_Contact extends ArrayAdapter<Contact>
{
    private int layoutResourceId;
    public List<Contact> contactlist;
    public LayoutInflater layoutInflater;
    public ViewHolder viewHolder;



    public ListViewAdapter_Contact(Context context, int layoutResourceId, List<Contact> contactlist)
    {

        super(context, layoutResourceId,  contactlist);

        this.layoutResourceId = layoutResourceId;
        this.contactlist = contactlist;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount()
    {
        if (contactlist != null)
            return contactlist.size();
        else
            return 0;
    }

    @Override
    public Contact       getItem(int position)
    {
        return contactlist.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public static class ViewHolder
    {
        TextView txtname,txtphone, txtmail;
        ImageView img;
        Button delete,modify;
        LinearLayout onerow;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        viewHolder = null;
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(layoutResourceId, parent,false);

            viewHolder = new ViewHolder();

            viewHolder.txtmail = (TextView)convertView.findViewById(R.id.txtmail);

            viewHolder.txtname = (TextView)convertView.findViewById(R.id.txtname);

            viewHolder.txtphone = (TextView)convertView.findViewById(R.id.txtphone);


            viewHolder.img = (ImageView)convertView.findViewById(R.id.imgpicture);

            viewHolder.delete=(Button) convertView.findViewById(R.id.btndelete);

            viewHolder.onerow=(LinearLayout) convertView.findViewById(R.id.one_row);

            viewHolder.modify = (Button) convertView.findViewById(R.id.btnmodify);


            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        final Contact info = contactlist.get(position);


        viewHolder.txtname.setText(info.name);
        viewHolder.txtphone.setText(info.phone);
        viewHolder.txtmail.setText(info.email);




        Picasso
                .with(getContext())
                .load(info.pict)
                .into(viewHolder.img);



        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             try {

                 StringBuilder result = new StringBuilder();
                 URL url = new URL("https://berkaykoksallab2project.appspot.com/deletecontact?id=" + info.id);
                 HttpURLConnection con = (HttpURLConnection) url.openConnection();
                 con.setRequestMethod("GET");
                 int test = con.getResponseCode();
                 Intent intent = new Intent(getContext(), dummyactivity.class);
                 getContext().startActivity(intent);
                }
             catch(Exception e)
             {

             }









            }
        });


        viewHolder.modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getContext(), AddContactActivity.class);
                intent.putExtra("name", info.name);
                intent.putExtra("phone", info.phone);
                intent.putExtra("mail", info.email);
                intent.putExtra("pict", info.pict);
                getContext().startActivity(intent);


            }
        });


        return convertView;
    }
}