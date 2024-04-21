package org.kata.yreya;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class App {
    private static final RestTemplate template = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String url = "http://94.198.50.185:7081/api/users";

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        User james = new User(3, "James", "Brown", (byte) 42);
        Map<String, String> jsonWithUser = mapper.convertValue(james, Map.class);

        // Add header cookie and Content-Type
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, null, String.class);
        headers.add("Content-Type", "application/json");
        headers.add("Cookie", response.getHeaders().getFirst("set-cookie"));

        // POST
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonWithUser, headers);
        response = template.exchange(url, HttpMethod.POST, request, String.class);
        sb.append(response.getBody());

        // PUT
        james.setName("Thomas");
        james.setLastName("Shelby");
        jsonWithUser = mapper.convertValue(james, Map.class);
        request = new HttpEntity<>(jsonWithUser, headers);
        response = template.exchange(url, HttpMethod.PUT, request, String.class);
        sb.append(response.getBody());

        // DELETE
        response = template.exchange(url + "/3", HttpMethod.DELETE, request, String.class);
        sb.append(response.getBody());

        System.out.println(sb);
    }
}