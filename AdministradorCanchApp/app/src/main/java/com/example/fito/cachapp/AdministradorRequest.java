package com.example.fito.cachapp;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class AdministradorRequest extends StringRequest
{
    private static final String ADMINISTRADOR_REQUEST_URL = "http://192.168.0.6/AdministradorCanchApp/ConsultaCancha.php";
    private Map<String, String> parametros;

    public AdministradorRequest(String rut, Response.Listener<String> listener)
    {

        super(Method.POST, ADMINISTRADOR_REQUEST_URL, listener,null);

        Log.d("Request",ADMINISTRADOR_REQUEST_URL);

        parametros = new HashMap<>();
        parametros.put("rut",rut);
        //parametros.put("password",password);
    }

    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
