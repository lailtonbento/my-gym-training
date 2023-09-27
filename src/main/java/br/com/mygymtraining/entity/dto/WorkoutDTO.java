package br.com.mygymtraining.entity.dto;

import br.com.mygymtraining.entity.Exercise;
import br.com.mygymtraining.entity.Workout;
import jakarta.validation.constraints.NotBlank;
import lombok.*;



import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDTO {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank
    private List<Exercise> exercises;

    public Workout toEntity(){
        return Workout.builder()
                .id(id)
                .name(name)
                .exercises(exercises)
                .build();
    }
}
