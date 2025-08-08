package org.example.productservice.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private UUID id;

    private String name;

    private UUID parentId;
}
