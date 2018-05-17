package com.example.fito.cachapp;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class AdministradorRequest extends StringRequest
{
    private static String Login_URL = "http://000utalca.000webhostapp.com/login.php";
    private Map<String, String> parametros;

    public AdministradorRequest(String usuario, String clave, Response.Listener<String> listener)
    {
        super(Method.POST, Login_URL, listener,null);

        parametros = new HashMap<>();
        parametros.put("usuario",usuario);
        parametros.put("clave",clave);
    }

    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
