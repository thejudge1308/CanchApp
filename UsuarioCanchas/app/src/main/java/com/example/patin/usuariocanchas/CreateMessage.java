package com.example.patin.usuariocanchas;

import android.util.Log;

import com.example.patin.usuariocanchas.Model.TelefonoID;
import com.example.patin.usuariocanchas.Model.User;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

public class CreateMessage {
    private String email;
    private String type;

    /**
     *
     * @param email del receptor
     * @param type tipo del mensaje
     */
    public CreateMessage(String email, String type) {
        this.email = email;
        this.type = type;
        sendMessage(email,type);
    }

    public boolean sendMessage(String email,final String type){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userReference = database.getReference(); //Obtiene la referencia de la bd
        Query query = userReference.child(FireBaseReferences.USER_IDFIREBASE).orderByChild("email").equalTo(email);
        //Log.v("MESSAGING_FIREBASE","entro");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //TelefonoID telefonoID = new TelefonoID();
                    TelefonoID userR=null;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        userR = issue.getValue(TelefonoID.class);
                    }
                    if(userR!=null){
                        FirebaseMessaging fm = FirebaseMessaging.getInstance();
                        fm.send(new RemoteMessage.Builder(userR.getPhone_id() + "@gcm.googleapis.com").setMessageType(type)
                                //.setMessageId(Integer.toString(msgId.incrementAndGet()))
                                //.addData("my_message", "Hello World")
                                //.addData)
                                .build());
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
