package com.example.patin.usuariocanchas.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.patin.usuariocanchas.Values.Rutes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private Map<String, String> parametros;

    public LoginRequest(
            String email,
            String password,
            Response.Listener<String> listener){
        super(Request.Method.POST, Rutes.loginPHP, listener,null);

        parametros = new HashMap<>();
        parametros.put("email",email);
        parametros.put("pass",password);
    }

    public Map<String, String> getParams() {
        return parametros;
    }
}
