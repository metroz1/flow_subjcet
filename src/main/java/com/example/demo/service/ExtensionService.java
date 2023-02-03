package com.example.demo.service;

import com.example.demo.dto.ExtensionResponseDto;
import com.example.demo.entity.Extension;
import com.example.demo.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExtensionService {

    private final ExtensionRepository extensionRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> getExtensionList() {

        List<Extension> extensionList = extensionRepository.findAll();
        String[] extensionNameList = new String[extensionList.size()];

        for (int i = 0; i < extensionNameList.length; i++) {
            extensionNameList[i] = extensionList.get(i).getName();
        }

        return new ResponseEntity<>(extensionNameList, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> createExtension(String name) {

        if (extensionRepository.countAll() >= 200)
            return new ResponseEntity<>("등록 가능한 확장자 수를 초과했습니다.", HttpStatus.BAD_REQUEST);

        if (extensionRepository.existsByName(name))
            return new ResponseEntity<>(name + " 확장자가 이미 등록되어 있습니다.", HttpStatus.BAD_REQUEST);

        if (name.length() > 20)
            return new ResponseEntity<>("확장자의 길이가 20 자리를 초과했습니다.", HttpStatus.BAD_REQUEST);

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
