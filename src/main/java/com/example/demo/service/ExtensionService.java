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

    @Transactional
    public ResponseEntity<?> createExtension(String name) {

        if (extensionRepository.existsByName(name))
            return new ResponseEntity<>(name + " 확장자가 이미 등록되어 있습니다.", HttpStatus.BAD_REQUEST);

        Extension extension = Extension.builder().name(name).build();

        extensionRepository.save(extension);

        return new ResponseEntity<>(name + " 확장자 차단 등록 되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteExtension(String name) {

        if (!extensionRepository.existsByName(name))
            return new ResponseEntity<>(name + " 확장자는 등록되어 있지 않습니다.", HttpStatus.BAD_REQUEST);

        extensionRepository.deleteByName(name);

        return new ResponseEntity<>(name + " 확장자 삭제 되었습니다.", HttpStatus.OK);
    }
}
