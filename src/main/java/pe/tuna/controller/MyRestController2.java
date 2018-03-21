package pe.tuna.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

@Controller
@RequestMapping("/saludo")
public class MyRestController2 {
    final String urlUserHello = "http://localhost:8080/apiSample/hello";
    final String urlAdminHello = "http://localhost:8080/apiSample/admin/hello";

    @GetMapping("/user")
    public String userHello(Model model){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<?> response = restTemplate.exchange(urlUserHello, HttpMethod.GET,
                new HttpEntity<String>(createHeaders("Miguel","chinchay")), String.class);

        model.addAttribute("saludo",response.getBody());

        return "saludo/user";
    }
    @GetMapping("/admin")
    public String adminHello(Model model){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<?> response = restTemplate.exchange(urlAdminHello, HttpMethod.GET,
                new HttpEntity<String>(createHeaders("admin","admin")), String.class);

        model.addAttribute("saludo",response.getBody());

        return "saludo/admin";
    }

    @SuppressWarnings("serial")
    private HttpHeaders createHeaders(String userName, String password){
        return new HttpHeaders(){
            {
                String auth = userName + ":" + password;
                byte[] encodedAuth = org.apache.tomcat.util.codec.binary.Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }
}
