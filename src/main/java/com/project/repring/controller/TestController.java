package com.project.repring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Test", description = "스웨거 테스트입니다.")
public class TestController {

    @Operation(summary = "요약", description = "설명")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping("/hello")
    public ResponseEntity<String> getTest(@Parameter(description = "파라미터", required = true, example = "예시")
                                          @RequestParam String name) {

        return ResponseEntity.ok("Hello " + name);
    }
}
