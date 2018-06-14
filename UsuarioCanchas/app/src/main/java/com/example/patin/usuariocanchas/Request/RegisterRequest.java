package com.example.patin.usuariocanchas.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.patin.usuariocanchas.Values.Rutes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private Map<String, String> parametros;

    public RegisterRequest(
            String email,
            String nickname,
            String name,
            String surname,
            String password,
            Date fechaNacimiento,
            Response.Listener<String> listener){
        super(Request.Method.POST, Rutes.RegisterPHP, listener,null);

        parametros = new HashMap<>();
        parametros.put("email",email);
        parametros.put("nickname",nickname);
        parametros.put("name",name);
        parametros.put("surname",surname);
        parametros.put("pass",password);
        parametros.put("fecha_nacimiento",fechaNacimiento.toString());
    }

    public Map<String, String> getParams() {
        return parametros;
    }
}
