package com.example.patin.usuariocanchas.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.patin.usuariocanchas.R;

import java.net.URLEncoder;

public class WebpayFragment extends Fragment {

    //Para mostrar la pagina de Webpay
    private WebView webView;
    //Monto a pagar
    private int amount;

    public WebpayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webpay, container, false);
    }

    /*
    Iniciar pago por medio de Webpay
    */
    private void initWebpayTransaction(){
        String jsonResponse = "";
        String postData = "";
        try {
            //Transformar el monto a pagar para enviarlo por metodo POST
            postData = "amount="+ URLEncoder.encode(String.valueOf(this.amount),"UTF-8");
        }catch (Exception ex){
            //D:
        }
        this.webView = this.getActivity().findViewById(R.id.webpay_webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        //Llamar URL de servidor PHP donde se maneja la transaccion
        webView.postUrl("https://daldana9.000webhostapp.com/init.php", postData.getBytes());
    }

    //Interface necesaria para hacer funcionar el componente Webview
    private class MyWebViewClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        //TODO Me falta un metodo aqui D:
    }
}
