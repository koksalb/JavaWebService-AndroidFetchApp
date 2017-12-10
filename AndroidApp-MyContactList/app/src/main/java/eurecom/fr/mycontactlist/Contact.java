package eurecom.fr.mycontactlist;


import org.json.JSONException;
import org.json.JSONObject;

public class Contact {
    public final String id;
    public final String name;
    public final String email;
    public final String phone;
    public final String pict;
    public Contact(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getString("id");
        phone = jsonObject.getString("phone");
        email = jsonObject.getString("email");
        name = jsonObject.getString("name");
        pict = jsonObject.getString("pict");
    }
    @Override
    public String toString() {
        return String.format("%s - %s - %s", name,phone, email);
    }
}
