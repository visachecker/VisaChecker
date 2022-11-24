package main.java;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateStatus {

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    final Map<String, Object> users = new HashMap<>();
    UserHelper user = new UserHelper();

    //Updating status of database child, if status Rejected or Approved changing field "finalStatus" to true so it's not checked by server in future
    public void update_data(String address, String status) {
        users.put("status", status);
        users.put("firstTimeAdded", "false");
        users.put("finalStatus", "false");
        reference = rootNode.getReference(address);
        if (status.equals("Rejected") || status.equals("Approved")) {
            users.put("finalStatus", "true");
        }

        reference.updateChildrenAsync(users);
    }

}
