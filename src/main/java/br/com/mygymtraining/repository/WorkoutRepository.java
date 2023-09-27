package br.com.mygymtraining.repository;

import br.com.mygymtraining.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByName(String name);
}
