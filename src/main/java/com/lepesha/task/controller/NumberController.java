package com.lepesha.task.controller;

import com.lepesha.task.dao.NumberDAO;
import com.lepesha.task.dto.NumberDTO;
import com.lepesha.task.dto.SumDTO;
import com.lepesha.task.exception.NumberAlreadyExists;
import com.lepesha.task.exception.NumberIsMissing;
import com.lepesha.task.responseEntity.BasicResponse;
import com.lepesha.task.responseEntity.SumResponse;
import com.lepesha.task.service.NumberService;
import com.lepesha.task.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberController {
    private final NumberService numberService;
    private final NumberDAO numberDAO;

    @Autowired
    public NumberController(NumberService numberService, NumberDAO numberDAO) {
        this.numberService = numberService;
        this.numberDAO = numberDAO;
    }

    @PostMapping("/add")
    public ResponseEntity<BasicResponse> add(@RequestBody NumberDTO numberDTO) {
        try {
            numberService.addNumber(numberDTO);
        } catch (NumberAlreadyExists e) {
            return new ResponseEntity<>(new BasicResponse(10), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(new BasicResponse(0), HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<BasicResponse> remove(@RequestBody NumberDTO numberDTO) {
        try {
            numberService.removeNumberByCode(numberDTO.getCode());
        } catch (NumberIsMissing e) {
            return new ResponseEntity<>(new BasicResponse(11), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(new BasicResponse(0), HttpStatus.OK);
    }

    @PostMapping("/sum")
    public ResponseEntity<SumResponse> sum(@RequestBody SumDTO sumDTO) {
        try {
            numberService.getNumber(sumDTO.getFirst());
            numberService.getNumber(sumDTO.getSecond());
        } catch (NumberIsMissing e) {
            return new ResponseEntity<>(new SumResponse(12), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Double result = numberService.sumNumbers(sumDTO);
        return new ResponseEntity<>(new SumResponse(0, result), HttpStatus.OK);
    }

}
