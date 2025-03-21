package com.office.notfound.payment.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PortOneServiceImpl implements PortOneService {

    @Value("${portone.api-key}")
    private String apiKey;

    @Value("${portone.api-secret}")
    private String apiSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String requestPayment(String merchantUid, int amount, String paymentMethod) {
        // 🔥 PortOne API를 실제로 호출
        String accessToken = getPortOneAccessToken();
        if (accessToken == null) {
            throw new RuntimeException("PortOne 액세스 토큰을 가져올 수 없습니다.");
        }

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("merchant_uid", merchantUid);
        requestBody.put("amount", amount);
        requestBody.put("pg", "html5_inicis");
        requestBody.put("pay_method", paymentMethod);
        requestBody.put("name", "오피스 예약 결제");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.iamport.kr/payments/prepare",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody(); // JSON 응답 반환
        } else {
            throw new RuntimeException("PortOne 결제 요청 실패: " + response.getBody());
        }
    }

    @Override
    public String cancelPayment(String impUid, int paymentAmount) {
        return "";
    }

    private String getPortOneAccessToken() {
        String url = "https://api.iamport.kr/users/getToken";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("imp_key", apiKey);
        requestBody.put("imp_secret", apiSecret);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("response")) {
                Map<String, Object> responseData = (Map<String, Object>) responseBody.get("response");
                return (String) responseData.get("access_token");
            }
        }
        return null;
    }

    @Override
    public String getAccessToken() {
        String url = "https://api.iamport.kr/users/getToken";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("imp_key", apiKey);
        requestBody.put("imp_secret", apiSecret);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("response")) {
                Map<String, Object> responseData = (Map<String, Object>) responseBody.get("response");
                return (String) responseData.get("access_token");
            }
        }
        throw new RuntimeException("포트원 액세스 토큰 발급 실패");
    }

    @Override
    public Map<String, Object> getPaymentData(String impUid, String token) {
        String url = "https://api.iamport.kr/payments/" + impUid;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("response")) {
                return (Map<String, Object>) responseBody.get("response");
            }
        }
        throw new RuntimeException("결제 정보 조회 실패");
    }
}
