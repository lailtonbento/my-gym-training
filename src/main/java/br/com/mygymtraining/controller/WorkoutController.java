package br.com.mygymtraining.controller;

import br.com.mygymtraining.entity.dto.WorkoutDTO;
import br.com.mygymtraining.service.impl.WorkoutServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutServiceImpl workoutService;

    public WorkoutController(WorkoutServiceImpl workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public ResponseEntity<Page<WorkoutDTO>> findAllPageable(
            @PageableDefault(page = 0, sort = {"username"}) Pageable pageable) {
        return ResponseEntity.ok(workoutService.listPageable(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(workoutService.findById(id));
    }

    @PutMapping("/{id}")
    public WorkoutDTO update(@PathVariable("id") Long id, @Valid @RequestBody WorkoutDTO workoutDTO){
        return workoutService.update(id, workoutDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public WorkoutDTO create(@Valid @RequestBody WorkoutDTO workoutDTO){
        return workoutService.save(workoutDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        workoutService.deleteById(id);
    }
}
