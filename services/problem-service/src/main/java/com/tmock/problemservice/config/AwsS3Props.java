package com.tmock.problemservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.s3")
public record AwsS3Props(
        String endpoint,
        String region,
        String accessKey,
        String secretKey,
        boolean usePathStyle,
        String bucket
) {}
