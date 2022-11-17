package com.lepesha.task.validation;

import com.lepesha.task.dao.NumberDAO;
import com.lepesha.task.dto.NumberDTO;
import com.lepesha.task.exception.NumberAlreadyExists;
import com.lepesha.task.model.Number;
import com.lepesha.task.responseEntity.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Validation {
    private NumberDAO numberDAO;

    @Autowired
    public Validation(NumberDAO numberDAO) {
        this.numberDAO = numberDAO;
    }

    public boolean isEntityExists(String code){
        Number number = numberDAO.getEntity(code);
        if(number != null) {
            return true;
        }
        return false;
    }
}
