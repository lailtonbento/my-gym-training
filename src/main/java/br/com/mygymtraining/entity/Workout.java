package br.com.mygymtraining.entity;

import br.com.mygymtraining.entity.dto.WorkoutDTO;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Workout implements Serializable {
    private static final long serialVersionUID = 6846117438634217888L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    public WorkoutDTO toDTO(){
        return WorkoutDTO.builder()
                .id(id)
                .name(name)
                .exercises(exercises)
                .build();
    }
}
