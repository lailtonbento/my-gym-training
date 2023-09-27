package br.com.mygymtraining.service;

import br.com.mygymtraining.entity.Workout;
import br.com.mygymtraining.entity.dto.UserDTO;
import br.com.mygymtraining.entity.dto.WorkoutDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WorkoutService {

    List<WorkoutDTO> listWorkouts();
    Page<WorkoutDTO> listPageable(Pageable pageable);
    List<WorkoutDTO> findByUsername(String name);
    void deleteById (Long id);
    WorkoutDTO save (WorkoutDTO workoutDTO);
    WorkoutDTO update(Long id, WorkoutDTO workoutDTO);
}
