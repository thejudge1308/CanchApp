package com.example.fito.login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EliminarCanchaRequest extends StringRequest {
    private static String Login_URL = CommandName.url+"EliminarCancha.php";
    private Map<String, String> parametros;

    public EliminarCanchaRequest(String id, Response.Listener<String> listener)
    {
        super(Method.POST, Login_URL, listener,null);

        parametros = new HashMap<>();
        parametros.put("id",id);

    }

    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
