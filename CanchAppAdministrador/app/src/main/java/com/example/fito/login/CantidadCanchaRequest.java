package com.example.fito.login;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CantidadCanchaRequest extends StringRequest
{

    private static String Login_URL = CommandName.url+"CantidadCancha.php";
    private Map<String, String> parametros;

    public CantidadCanchaRequest(String rut,Response.Listener<String> listener)
    {
        super(Request.Method.POST, Login_URL, listener,null);

        Log.d("Rut2",rut);

        parametros = new HashMap<>();
        parametros.put("rut",rut);
    }

    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
