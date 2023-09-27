package br.com.mygymtraining.entity.dto;

import br.com.mygymtraining.entity.Exercise;
import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Series is required")
    private Integer series;
    @NotBlank(message = "Reps is required")
    private Integer reps;
    private String method;

    public Exercise toEntity(){
        return Exercise.builder()
                .id(id)
                .name(name)
                .series(series)
                .reps(reps)
                .method(method)
                .build();
    }
}
