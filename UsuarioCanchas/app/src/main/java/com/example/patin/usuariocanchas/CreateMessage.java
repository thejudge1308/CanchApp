package com.example.patin.usuariocanchas;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.example.patin.usuariocanchas.Model.TelefonoID;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.android.gms.common.internal.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreateMessage {
    private String email;
    private String type;
    /**
     * Notificaciones:
     * Envio Partido = 0
     * Envio Amigo = 1
     * Envio Equipo = 2
     * Envio Servicio = 3
     * Recepcion de Amigo 4.
     */

    public final static String NOTIFICACION_PARTIDO="0";
    public final static String NOTIFICACION_AMIGO="1";
    public final static String NOTIFICACION_EQUIPO="2";
    public final static String NOTIFICACION_SERVICIO="3";
    public final static String NOTIFICACION_AMIGO_ACEPT="4";

    /**
     *
     * @param email del receptor
     * @param type tipo del mensaje NOTIFICATION
     */
    public CreateMessage(String email, String type) {
        this.email = email;
        this.type = type;
        sendMessage(email,type);
    }

    public final static String AUTH_KEY_FCM = "AIzaSyCjLHq3JludBXV0i6LOaLS1VHblKYlbhiM";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

    public void sendNotification(final String deviceToken,final String message){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(API_URL_FCM);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject parentjson = new JSONObject();
                    JSONObject notificationValue = new JSONObject();
                    notificationValue.put("body", message);
                    notificationValue.put("title", "Notificación de CanchApp");
                    notificationValue.put("sound", "default");

                    parentjson.put("to",deviceToken);
                    parentjson.put("notification",notificationValue);

                    Log.i("MESSAGING_FIREBASE", parentjson.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(parentjson.toString());

                    os.flush();
                    os.close();

                    Log.i("MESSAGING_FIREBASE", String.valueOf(conn.getResponseCode()));
                    Log.i("MESSAGING_FIREBASE" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }




    public boolean sendMessage(String email,final String type){
        Log.v("MESSAGING_FIREBASE","Antes de enviar notificacion");
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
                   //Envio de push notification
                    //sendPushNotification(userR.getPhone_id(),type);
                    Log.v("MESSAGING_FIREBASE","Antes de enviar notificacion");
                    sendNotification(userR.getPhone_id(),getHeader(type));
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


    public static String getHeader(String opcion){
        switch (opcion){
            case NOTIFICACION_PARTIDO:
                return SingletonUser.getInstance().getNickname()+" te ha invitado a un partido!";
            case NOTIFICACION_AMIGO:
                return SingletonUser.getInstance().getName()+" quiere ser tu amigo!";
            case NOTIFICACION_EQUIPO:
                return "Tienes una solicitud de un equipo.";
            case NOTIFICACION_SERVICIO:
                return "Alguien necesita de tus servicios.";
            case NOTIFICACION_AMIGO_ACEPT:
                return "Han aceptado tu solicitud de amistad.";
            default:
                return "Unete al juego.";
        }

    }





}
