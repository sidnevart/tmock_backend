package com.tmock.problemservice.controller;

import com.tmock.problemservice.config.AwsS3Props;
import com.tmock.problemservice.dto.UpdateProblemRequest;
import com.tmock.problemservice.dto.UploadRequest;
import com.tmock.problemservice.model.Problem;
import com.tmock.problemservice.service.ProblemAssetService;
import com.tmock.problemservice.service.ProblemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ExecutableAggregationOperation;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.Map;

@RestController
@RequestMapping("/api/problems/assets")
@RequiredArgsConstructor
@Slf4j
public class ProblemAssetController {
    private final ProblemAssetService assetService;
    private final S3Client s3Client;
    private final AwsS3Props props;
    private final ProblemService problemService;


    @PostMapping("/statement/upload-url")
    public Map<String,Object> uploadUrl(@RequestBody UploadRequest request) {
        log.info("Upload url for problem id {}", request.problemId());
        var plan = assetService.presignPutStatement(request.problemId());
        return Map.of("key", plan.key(), "putUrl", plan.putUrl(), "expiresSec", plan.expiresSec());
    }

    @GetMapping("/download-url")
    public Map<String,Object> downloadUrl(@RequestParam String key) {
        var plan = assetService.presignDownloadStatement(key);
        return Map.of("key", plan.key(), "url", plan.getUrl(), "expiresSec", plan.expiresSec());
    }

    @PostMapping("/{id}/attach-statement")
    public Problem attach(@PathVariable String id,
                          @RequestParam String key){
        var expected = ProblemAssetService.keyStatement(id);
        if (!expected.equals(key)) {
            throw new IllegalArgumentException("unexpected id");
        }
        var head = s3Client.headObject(b -> b.bucket(props.bucket()).key(key));
        return problemService.update(id, new UpdateProblemRequest(null, null, null, "s3://" + props.bucket() + "/" + key));
    }
}
