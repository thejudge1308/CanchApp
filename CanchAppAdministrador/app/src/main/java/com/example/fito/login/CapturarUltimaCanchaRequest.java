package com.example.fito.login;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CapturarUltimaCanchaRequest extends StringRequest
{

    private static String Login_URL = CommandName.url+"ConsultaUltimaCancha.php";
    private Map<String, String> parametros;

    public CapturarUltimaCanchaRequest(Response.Listener<String> listener)
    {
        super(Request.Method.POST, Login_URL, listener,null);
    }

    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
