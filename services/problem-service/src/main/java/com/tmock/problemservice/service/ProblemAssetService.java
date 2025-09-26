package com.tmock.problemservice.service;

import com.tmock.problemservice.config.AwsS3Props;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ProblemAssetService {
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final AwsS3Props props;

    public record UploadPlan(String key, String putUrl, long expiresSec){}
    public record DownloadPlan(String key, String getUrl, long expiresSec){}

    public static String keyStatement(String problemId){
        return "%s/statement.md".formatted(problemId);
    }

    public UploadPlan presignPutStatement(String problemId){
        var key = keyStatement(problemId);
        var req =  PutObjectRequest.builder()
                .bucket(props.bucket())
                .key(key)
                .contentType("text/markdown; charset=utf-8")
                .build();
        var p = s3Presigner.presignPutObject(b -> b.putObjectRequest(req).signatureDuration(Duration.ofMinutes(15)));
        return new UploadPlan(key, p.url().toString(), 15*60);
    }

    public DownloadPlan presignDownloadStatement(String key){
        var req = GetObjectRequest.builder().bucket(props.bucket()).key(key).build();
        var p = s3Presigner.presignGetObject(b -> b.getObjectRequest(req).signatureDuration(Duration.ofMinutes(15)));
        return new DownloadPlan(key, p.url().toString(), 15*60);
    }
}
