package org.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Integer id;
    private String title;
    private String description;
    private Boolean completed;

}
