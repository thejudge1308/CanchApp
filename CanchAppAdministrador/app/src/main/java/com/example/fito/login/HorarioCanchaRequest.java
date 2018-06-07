package com.example.fito.login;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class HorarioCanchaRequest extends StringRequest {
    private static String HorariosCancha_URL = CommandName.url+"ConsultaHorariosCancha.php";
    private Map<String, String> parametros;
    public HorarioCanchaRequest(String nombreCancha, String date,Response.Listener<String> listener)
    {
        super(Request.Method.POST, HorariosCancha_URL, listener,null);
        parametros = new HashMap<>();
        //Log.v("Salida",""+nombreCancha);
        parametros.put("canchanombre",nombreCancha);
        parametros.put("date",date);
    }
    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
