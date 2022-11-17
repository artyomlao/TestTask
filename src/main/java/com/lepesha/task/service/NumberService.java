package com.lepesha.task.service;

import com.lepesha.task.dao.NumberDAO;
import com.lepesha.task.dto.NumberDTO;
import com.lepesha.task.dto.SumDTO;
import com.lepesha.task.exception.NumberAlreadyExists;
import com.lepesha.task.exception.NumberIsMissing;
import com.lepesha.task.model.Number;
import com.lepesha.task.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberService {
    private final NumberDAO numberDAO;
    private final Validation validation;

    @Autowired
    public NumberService(NumberDAO numberDAO, Validation validation) {
        this.numberDAO = numberDAO;
        this.validation = validation;
    }

    public void addNumber(NumberDTO numberDTO) throws NumberAlreadyExists {
        if(validation.isEntityExists(numberDTO.getCode())) {
            throw new NumberAlreadyExists("Number with such code already exists");
        }
        Number number = new Number(numberDTO.getCode(), numberDTO.getValue());
        numberDAO.add(number);
    }

    public void removeNumberByCode(String code) throws NumberIsMissing {
        if(!validation.isEntityExists(code)) {
            throw new NumberIsMissing("Number with such code is missing");
        }
        numberDAO.remove(code);
    }

    public Double sumNumbers(SumDTO sumDTO) {
        Number firstNumber = numberDAO.getEntity(sumDTO.getFirst());
        Number secondNumber = numberDAO.getEntity(sumDTO.getSecond());

        Double result = firstNumber.getValue() + secondNumber.getValue();
        return result;
    }

    public Number getNumber(String code) throws NumberIsMissing {
        if(!validation.isEntityExists(code)) {
            throw new NumberIsMissing("Number with such code is missing");
        }
        Number number = numberDAO.getEntity(code);
        return number;
    }
}
