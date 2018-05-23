package com.example.fito.login;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PrincipalRequest extends StringRequest
{

    private static String Login_URL = "http://172.16.47.139/CanchAppAdmin/Cancha.php";
    private Map<String, String> parametros;

    public PrincipalRequest(String id, String rut,Response.Listener<String> listener)
    {
        super(Request.Method.POST, Login_URL, listener,null);

        Log.d("Rut2",rut);

        parametros = new HashMap<>();
        parametros.put("rut",rut);
        parametros.put("id",id);
    }

    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
