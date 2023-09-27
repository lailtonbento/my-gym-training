package br.com.mygymtraining.controller;

import br.com.mygymtraining.entity.dto.ExerciseDTO;
import br.com.mygymtraining.service.impl.ExerciceServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {


    private final ExerciceServiceImpl exerciceService;

    public ExerciseController(ExerciceServiceImpl exerciceService) {
        this.exerciceService = exerciceService;
    }

    @GetMapping
    public ResponseEntity<Page<ExerciseDTO>> findAllPageable(
            @PageableDefault(page = 0, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(exerciceService.listPageable(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(exerciceService.findById(id));
    }

    @PutMapping("/{id}")
    public ExerciseDTO update(@PathVariable("id") Long id, @Valid @RequestBody ExerciseDTO exerciseDTO){
        return exerciceService.update(id, exerciseDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ExerciseDTO create(@Valid @RequestBody ExerciseDTO exerciseDTO){
        return exerciceService.save(exerciseDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        exerciceService.deleteById(id);
    }
}
