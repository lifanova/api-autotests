package ru.alfabank.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColorsData {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantoneValue;
}
