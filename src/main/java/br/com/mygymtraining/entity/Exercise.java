package br.com.mygymtraining.entity;

import br.com.mygymtraining.entity.dto.ExerciseDTO;
import lombok.*;


import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Exercise implements Serializable {
    private static final long serialVersionUID = 6846117438634217888L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer series;
    private Integer reps;
    private String method;


    public ExerciseDTO toDTO(){
        return ExerciseDTO.builder()
                .id(id)
                .name(name)
                .series(series)
                .reps(reps)
                .method(method)
                .build();
    }
}
