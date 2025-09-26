package com.tmock.problemservice.service;

import com.tmock.problemservice.dto.CreateProblemRequest;
import com.tmock.problemservice.dto.UpdateProblemRequest;
import com.tmock.problemservice.model.Problem;
import com.tmock.problemservice.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;

    public List<Problem> findAll() {
        return problemRepository.findAll();
    }

    public Problem findById(String id) {
        return problemRepository.findById(id).orElseThrow();
    }

    public Problem findByTitle(String title) {
        return problemRepository.findByTitle(title).orElseThrow();
    }

    public List<Problem> findByTopic(String topic) {
        return problemRepository.findProblemsByTopicsContaining(topic);
    }

    public List<Problem> findByDifficulty(String difficulty) {
        return problemRepository.findProblemsByDifficulty(difficulty);
    }

    public Problem create(CreateProblemRequest request) {
        var p = new Problem();
        p.setTitle(request.title());
        p.setDifficulty(request.difficulty());
        p.setStatementUri(request.statementUri());
        p.setTopics(request.topics());
        return problemRepository.save(p);
    }

    public Problem update(String id, UpdateProblemRequest request) {
        var p = problemRepository.findById(id).orElse(null);

        if (request.title() != null) p.setTitle(request.title());
        if (request.difficulty() != null) p.setDifficulty(request.difficulty());
        if(request.statementUri() != null) p.setStatementUri(request.statementUri());
        if (request.topics() != null) p.setTopics(request.topics());

        return problemRepository.save(p);

    }

    public void deleteById(String id) {
        problemRepository.deleteById(id);
    }
}
