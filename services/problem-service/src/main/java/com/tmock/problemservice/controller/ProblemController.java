package com.tmock.problemservice.controller;

import com.tmock.problemservice.dto.CreateProblemRequest;
import com.tmock.problemservice.dto.UpdateProblemRequest;
import com.tmock.problemservice.model.Problem;
import com.tmock.problemservice.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/problems")
public class ProblemController {
    private final ProblemService problemService;

    @PostMapping
    public Problem create(@RequestBody CreateProblemRequest request) {
        return problemService.create(request);
    }

    @GetMapping
    public ResponseEntity<List<Problem>> getAll(){
        return ResponseEntity.ok(problemService.findAll());
    }

    @PatchMapping("/{id}")
    public Problem update(@PathVariable String id, @RequestBody UpdateProblemRequest request) {
        return problemService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        problemService.deleteById(id);
    }
}
