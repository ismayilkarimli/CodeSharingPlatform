package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.service.CodeService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final CodeService codeService;

    @Autowired
    public ApiController(CodeService service) {
        this.codeService = service;
    }

    @GetMapping(value = "/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Code> getCode(@PathVariable String id) {
        Code code = codeService.getCode(id);
        return ResponseEntity.ok(code);
    }

    @PostMapping(value = "/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> createCode(@RequestBody Code code) {
        UUID id = codeService.addCode(code);
        return ResponseEntity.ok(Collections.singletonMap("id", id.toString()));
//        return ResponseEntity.ok(Collections.singletonMap("id", id.toString().replaceAll("-", "")));
    }

    @GetMapping("/code/latest")
    public ResponseEntity<List<Code>> getLatestCodes() {
        return ResponseEntity.ok(codeService.getLatest());
    }
}
