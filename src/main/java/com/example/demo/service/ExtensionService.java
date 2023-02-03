package com.example.demo.service;

import com.example.demo.dto.ExtensionResponseDto;
import com.example.demo.entity.Extension;
import com.example.demo.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExtensionService {

    private final ExtensionRepository extensionRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> getExtensionList() {

        List<Extension> extensionList = extensionRepository.findAll();
        List<ExtensionResponseDto> extensionResponseDtoList = extensionList.stream().map(ExtensionResponseDto::new).collect(Collectors.toList());

        return new ResponseEntity<>(extensionResponseDtoList, HttpStatus.OK);
    }
}
