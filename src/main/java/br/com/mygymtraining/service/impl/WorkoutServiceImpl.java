package br.com.mygymtraining.service.impl;

import br.com.mygymtraining.entity.Workout;
import br.com.mygymtraining.entity.dto.WorkoutDTO;
import br.com.mygymtraining.repository.WorkoutRepository;
import br.com.mygymtraining.service.WorkoutService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }


    @Override
    public List<WorkoutDTO> listWorkouts() {
        return workoutRepository.findAll().stream()
                .map(Workout::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<WorkoutDTO> listPageable(Pageable pageable) {
        return workoutRepository.findAll(pageable).map(Workout::toDTO);
    }

    @Override
    public List<WorkoutDTO> findByUsername(String name) {
        return workoutRepository.findByName(name).stream()
                .map(Workout::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        existById(id);
        workoutRepository.deleteById(id);
    }

    @Override
    public WorkoutDTO save(WorkoutDTO workoutDTO) {
        return workoutRepository.save(workoutDTO.toEntity())
                .toDTO();
    }

    @Override
    public WorkoutDTO update(Long id, WorkoutDTO workoutDTO) {
        existById(id);
        workoutDTO.setId(id);
        return workoutRepository.save(workoutDTO.toEntity())
                .toDTO();
    }

    @SneakyThrows
    public void existById(Long id) {
        if (!workoutRepository.existsById(id)) {
            throw new Exception("Exercise not found");
        }
    }

    @SneakyThrows
    public WorkoutDTO findById(Long id) {
        return workoutRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Exercise Not Found")).toDTO();
    }
}
