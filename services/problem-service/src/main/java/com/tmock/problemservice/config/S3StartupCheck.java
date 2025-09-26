package com.tmock.problemservice.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
class S3StartupCheck {
    private final AwsS3Props props;

    @PostConstruct
    void check() {
        log.info("S3 props: endpoint={}, bucket={}, region={}, pathStyle={}",
                props.endpoint(), props.bucket(), props.region(), props.usePathStyle());
    }
}

