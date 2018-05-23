package com.example.fito.login;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest{
    private static String Registro_URL = "http://000utalca.000webhostapp.com/registro.php";
    private Map<String, String> parametros;

    public RegistroRequest(String nombre, String usuario, String clave, int edad, Response.Listener<String> listener)
    {
        super(Method.POST,Registro_URL, listener,null);

        parametros = new HashMap<>();
        parametros.put("nombre",nombre);
        parametros.put("usuario",usuario);
        parametros.put("clave",clave);
        parametros.put("edad",edad+"");
    }

    @Override
    public Map<String, String> getParams()
    {
        return parametros;
    }
}
