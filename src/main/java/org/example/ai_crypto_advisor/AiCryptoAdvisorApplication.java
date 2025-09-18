package org.example.ai_crypto_advisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

@SpringBootApplication
public class AiCryptoAdvisorApplication {

	public static void main(String[] args) {

		SpringApplication.run(AiCryptoAdvisorApplication.class, args);

	}

}
