package com.example.demo.dto;

import com.example.demo.entity.Extension;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ExtensionResponseDto {

    private String name;

    public ExtensionResponseDto(Extension extension) {
        this.name = extension.getName();
    }
}
