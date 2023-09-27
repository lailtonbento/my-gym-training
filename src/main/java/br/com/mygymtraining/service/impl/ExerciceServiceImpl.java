package br.com.mygymtraining.service.impl;

import br.com.mygymtraining.entity.Exercise;
import br.com.mygymtraining.entity.dto.ExerciseDTO;
import br.com.mygymtraining.repository.ExerciseRepository;
import br.com.mygymtraining.service.ExerciseService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciceServiceImpl implements ExerciseService {


    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciceServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<ExerciseDTO> listExercises() {
        return exerciseRepository.findAll().stream()
                .map(Exercise::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ExerciseDTO> listPageable(Pageable pageable) {
        return exerciseRepository.findAll(pageable).map(Exercise::toDTO);
    }

    @Override
    public List<ExerciseDTO> findByExercise(String name) {
        return exerciseRepository.findByName(name).stream()
                .map(Exercise::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        existById(id);
        exerciseRepository.deleteById(id);
    }

    @Override
    public ExerciseDTO save(ExerciseDTO exerciseDTO) {
        return exerciseRepository.save(exerciseDTO.toEntity())
                .toDTO();
    }

    @Override
    public ExerciseDTO update(Long id, ExerciseDTO exerciseDTO) {
        existById(id);
        exerciseDTO.setId(id);
        return exerciseRepository.save(exerciseDTO.toEntity())
                .toDTO();
    }

    @SneakyThrows
    public void existById(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new Exception("Exercise not found");
        }
    }

    @SneakyThrows
    public ExerciseDTO findById(Long id) {
        return exerciseRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Exercise Not Found")).toDTO();
    }
}
