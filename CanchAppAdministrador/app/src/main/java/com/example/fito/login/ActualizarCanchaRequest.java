package com.example.fito.login;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ActualizarCanchaRequest extends StringRequest
{

    private static String Login_URL = CommandName.url+"ActualizarCancha.php";
    private Map<String, String> parametros;

    public ActualizarCanchaRequest(String nombre, String rut, String direccion, String ubicacion,Response.Listener<String> listener)
    {
        super(Request.Method.POST, Login_URL, listener,null);

        Log.d("Rut2",rut);

        parametros = new HashMap<>();
        parametros.put("rut",rut);
        parametros.put("nombre",nombre);
        parametros.put("direccion",direccion);
        parametros.put("ubicacion",ubicacion);
    }

    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
