package com.example.fito.login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AgregarCanchaRequest extends StringRequest {
    private static String Login_URL = CommandName.url+"AgregarCancha.php";
    private Map<String, String> parametros;

    public AgregarCanchaRequest(String nombre, String direccion, String ubicacion, String  estado, String rut,Response.Listener<String> listener)
    {
        super(Method.POST, Login_URL, listener,null);

        parametros = new HashMap<>();
        parametros.put("nombre",nombre);
        parametros.put("direccion",direccion);
        parametros.put("ubicacion",ubicacion);
        parametros.put("estado",estado);
        parametros.put("rut",rut);
    }

    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
