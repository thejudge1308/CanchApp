package com.example.patin.usuariocanchas.Fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Activities.SportActivity;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URLEncoder;

/**
 * Fragment para mostrar la pagina de webpay y pagar la cuota de un evento.
 * Recibe el monto a pagar y el id del evento en un bundle al llamar el fragment
 */
public class WebpayFragment extends Fragment {

    //Para mostrar la pagina de Webpay
    private WebView webView;

    //Url del servidor que se encarga de la transacción
    private String webpayUrl = "https://daldana9.000webhostapp.com/init.php";

    //Monto a pagar, se lo pasan desde el fragment anterior
    private int amount;

    //Id del evento a pagar, se lo pasan desde el fragment anterior
    private String eventId;

    //Id del usuario que va a pagar, se lo pasan desde el fragment anterior... maybe
    private String userId = SingletonUser.getInstance().getId();
    //private String userId = "-LFVSvYxApO1OCctB56v";

    public WebpayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webpay, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Obtener argumentos e iniciar transaccion
        this.amount = SportActivity.amount;
        this.eventId= SportActivity.keyUser;
        Log.d("_WEBPAY", this.amount+" "+this.eventId);
        initWebpayTransaction();

    }

    /*
    Iniciar pago por medio de Webpay
    */
    private void initWebpayTransaction(){
        String postData = "";
        try {
            //Transformar el monto a pagar para enviarlo por metodo HTTP POST
            postData = "amount="+ URLEncoder.encode(String.valueOf(this.amount),"UTF-8");
        }catch (Exception ex){
            //D:
        }
        this.webView = this.getActivity().findViewById(R.id.webpay_webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        //Llamar URL de servidor PHP donde se maneja la transaccion
        webView.postUrl(webpayUrl, postData.getBytes());
    }

    //Interface necesaria para hacer funcionar el componente Webview
    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        //Este metodo detecta los cambios en la url del webview, aca lo usamos para saber cuando termino la transaccion
        //o cuando ocurre un error
        @Override
        public void onPageFinished(WebView view, String url) {

            //Transaccion terminada, guardamos en la base de datos y volvemos al fragment anterior!
            if(url.endsWith("voucher.cgi")) {
                DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                db.child(FireBaseReferences.USER_PAY_REFERENCE).child(userId).child(eventId).child("estaPagado").setValue("1");
                FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                ft.replace(R.id.frangment_content, new HomeFragment());
                ft.commit();
            }
            //La transaccion en Webpay fallo, posiblemente el tipo no tiene plata xd
            else if(url.endsWith("error.php")) {
                Toast.makeText(getActivity().getApplicationContext(), "No se pudo completar la transacción. Por favor intente más tarde.", Toast.LENGTH_LONG).show();
                FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                ft.replace(R.id.frangment_content, new HomeFragment());
                ft.commit();
            }
        }
    }
}
