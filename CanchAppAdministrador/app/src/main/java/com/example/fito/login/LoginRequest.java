package com.example.fito.login;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static String Login_URL = "http://172.16.47.139/CanchAppAdmin/Login.php";
    private Map<String, String> parametros;

    public LoginRequest(String rut, String clave, Response.Listener<String> listener)
    {
        super(Method.POST, Login_URL, listener,null);

        parametros = new HashMap<>();
        parametros.put("rut",rut);
        parametros.put("password",clave);
    }

    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
