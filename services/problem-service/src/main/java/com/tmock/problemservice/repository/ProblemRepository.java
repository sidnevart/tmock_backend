package com.tmock.problemservice.repository;

import com.tmock.problemservice.model.Difficulty;
import com.tmock.problemservice.model.Problem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProblemRepository extends MongoRepository<Problem, String> {
    @Override
    List<Problem> findAll();

    Optional<Problem> findByTitle(String title);

    List<Problem> findProblemsByDifficulty(String difficulty);
    List<Problem> findProblemsByTopicsContaining(String topic);
}
