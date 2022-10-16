package com.powerldger.code.dto;

import com.powerldger.code.model.Batteries;
import lombok.*;

import java.util.List;
import java.util.OptionalDouble;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatterieDto {

    @NonNull
    private List<Batteries> batteries;

    @NonNull
    private Double average;

    @NonNull
    private Double total;


}
