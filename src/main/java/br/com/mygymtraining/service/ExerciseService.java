package br.com.mygymtraining.service;

import br.com.mygymtraining.entity.Exercise;
import br.com.mygymtraining.entity.dto.ExerciseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    List<ExerciseDTO> listExercises();
    Page<ExerciseDTO> listPageable(Pageable pageable);
    List<ExerciseDTO> findByExercise(String name);
    void deleteById (Long id);
    ExerciseDTO save (ExerciseDTO exerciseDTO);
    ExerciseDTO update(Long id, ExerciseDTO exerciseDTO);
}
