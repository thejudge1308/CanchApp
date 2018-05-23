package com.example.fito.cachapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String lOGIN_REQUEST_URL = "http://192.168.0.6/Usuario/Login.php";
    private Map<String, String> parametros;

    public LoginRequest(String usuario, String clave, Response.Listener<String> listener)
    {
        super(Method.POST, lOGIN_REQUEST_URL, listener,null);

        parametros = new HashMap<>();
        parametros.put("correo",usuario);
        parametros.put("clave",clave);
    }

    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
