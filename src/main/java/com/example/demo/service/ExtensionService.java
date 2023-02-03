package com.example.demo.service;

import com.example.demo.entity.BasicExtension;
import com.example.demo.entity.CustomExtension;
import com.example.demo.repository.BasicExtensionRepository;
import com.example.demo.repository.CustomExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtensionService {

    private String[] basicExtension = {"bat", "cmd", "com", "cpl", "exe", "scr", "js"};

    private final CustomExtensionRepository customExtensionRepository;
    private final BasicExtensionRepository basicExtensionRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> getCustomExtensionList() {

        List<CustomExtension> extensionList = customExtensionRepository.findAll();
        String[] extensionNameList = new String[extensionList.size()];

        for (int i = 0; i < extensionNameList.length; i++) {
            extensionNameList[i] = extensionList.get(i).getName();
        }

        return new ResponseEntity<>(extensionNameList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getBasicExtensionList() {

        List<BasicExtension> extensionList = basicExtensionRepository.findAll();
        String[] extensionNameList = new String[extensionList.size()];

        for (int i = 0; i < extensionNameList.length; i++) {
            extensionNameList[i] = extensionList.get(i).getName();
        }

        return new ResponseEntity<>(extensionNameList, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> createCustomExtension(String name) {

        if (Arrays.asList(basicExtension).contains(name))
            return new ResponseEntity<>("기본 확장자는 위에서 등록 바랍니다.", HttpStatus.BAD_REQUEST);

        if (customExtensionRepository.count() >= 200)
            return new ResponseEntity<>("등록 가능한 확장자 수를 초과했습니다.", HttpStatus.BAD_REQUEST);

        if (basicExtensionRepository.existsByName(name) || customExtensionRepository.existsByName(name))
            return new ResponseEntity<>(name + " 확장자가 이미 등록되어 있습니다.", HttpStatus.BAD_REQUEST);

        if (name.length() > 20)
            return new ResponseEntity<>("확장자의 길이가 20 자리를 초과했습니다.", HttpStatus.BAD_REQUEST);

        CustomExtension extension = CustomExtension.builder().name(name).build();

        customExtensionRepository.save(extension);

        return new ResponseEntity<>(name + " 확장자 차단 등록 되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> createBasicExtension(String name) {

        if (basicExtensionRepository.existsByName(name))
            return new ResponseEntity<>(name + " 확장자가 이미 등록되어 있습니다.", HttpStatus.BAD_REQUEST);

        BasicExtension extension = BasicExtension.builder().name(name).build();

        basicExtensionRepository.save(extension);

        return new ResponseEntity<>(name + " 확장자 차단 등록 되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteBasicExtension(String name) {

        if (!basicExtensionRepository.existsByName(name))
            return new ResponseEntity<>(name + " 확장자는 등록되어 있지 않습니다.", HttpStatus.BAD_REQUEST);

        basicExtensionRepository.deleteByName(name);

        return new ResponseEntity<>(name + " 확장자 삭제 되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteCustomExtension(String name) {

        if (!customExtensionRepository.existsByName(name))
            return new ResponseEntity<>(name + " 확장자는 등록되어 있지 않습니다.", HttpStatus.BAD_REQUEST);

        customExtensionRepository.deleteByName(name);

        return new ResponseEntity<>(name + " 확장자 삭제 되었습니다.", HttpStatus.OK);
    }
}
