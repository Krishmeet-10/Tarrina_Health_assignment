package com.tarrinahealthassignment.Tarrina_Health_Assignment.controller;

import com.tarrinahealthassignment.Tarrina_Health_Assignment.dto.AssessmentRequestDto;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.dto.AssessmentResponseDto;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @PostMapping
    public AssessmentResponseDto assessCustomer(@RequestBody AssessmentRequestDto requestDto) {
        return assessmentService.assessCreditRisk(requestDto);
    }
}
