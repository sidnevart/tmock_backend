package com.tmock.problemservice.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
public class S3Config {
    @Bean
    S3Client s3Client(AwsS3Props props) {
        var creds = AwsBasicCredentials.create(props.accessKey(), props.secretKey());
        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(creds))
                .endpointOverride(URI.create(props.endpoint()))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(props.usePathStyle())
                        .build())
                .region(Region.of(props.region()))
                .build();
    }

    @Bean
    S3Presigner s3Presigner(AwsS3Props props) {
        var creds = AwsBasicCredentials.create(props.accessKey(), props.secretKey());
        return S3Presigner.builder()
                .credentialsProvider(StaticCredentialsProvider.create(creds))
                .endpointOverride(URI.create(props.endpoint()))
                .region(Region.of(props.region()))
                .build();
    }
}
