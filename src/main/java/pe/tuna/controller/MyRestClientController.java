package pe.tuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import pe.tuna.models.Country;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


@Controller
public class MyRestClientController {
    final String urlList = "https://restcountries.eu/rest/v2/all";

    @GetMapping("/list")
    public String listCountries(Model model){
        enableSSL();
        RestTemplate restTemplate = new RestTemplate();

        Country[] lista = restTemplate.getForObject(urlList, Country[].class);
        model.addAttribute("lista", lista);

        return "lista/lista";
    }

    private void enableSSL(){
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }


                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {

                    }


                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType){

                    }

                }
        };

        try{
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        }catch(Exception e){

        }
    }
}
