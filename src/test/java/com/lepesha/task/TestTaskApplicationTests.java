package com.lepesha.task;

import com.lepesha.task.controller.NumberController;
import com.lepesha.task.dao.NumberDAO;
import com.lepesha.task.dto.NumberDTO;
import com.lepesha.task.dto.SumDTO;
import com.lepesha.task.exception.NumberAlreadyExists;
import com.lepesha.task.exception.NumberIsMissing;
import com.lepesha.task.model.Number;
import com.lepesha.task.service.NumberService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TestTaskApplicationTests {
    private NumberDAO numberDAO;

    private NumberService numberService;

    @Autowired
    public TestTaskApplicationTests(NumberService numberService, NumberDAO numberDAO) {
        this.numberService = numberService;
        this.numberDAO = numberDAO;
    }

    @Test
    public void removeAndAdd() {
        try {
            numberService.removeNumberByCode("first");
        } catch (NumberIsMissing e) {
            e.printStackTrace();
        }
        NumberDTO numberDTO = new NumberDTO();
        numberDTO.setCode("first");
        numberDTO.setValue(4.0);
        try {
            numberService.addNumber(numberDTO);
        } catch (NumberAlreadyExists e) {
            e.printStackTrace();
        }

        assertEquals(4, numberDAO.getEntity("first").getValue());
    }

    @Test
    public void checkSum() {
        try {
            numberService.removeNumberByCode("first");
        } catch (NumberIsMissing e) {
        }

        try {
            numberService.removeNumberByCode("second");
        } catch (NumberIsMissing e) {
            e.printStackTrace();
        }

        NumberDTO firstNumber = new NumberDTO();
        firstNumber.setCode("first");
        firstNumber.setValue(4.0);
        try {
            numberService.addNumber(firstNumber);
        } catch (NumberAlreadyExists e) {
        }

        NumberDTO secondNumber = new NumberDTO();
        secondNumber.setCode("second");
        secondNumber.setValue(4.0);

        try {
            numberService.addNumber(secondNumber);
        } catch (NumberAlreadyExists e) {
            e.printStackTrace();
        }

        SumDTO sum = new SumDTO();
        sum.setFirst("first");
        sum.setSecond("second");
        assertEquals(8, numberService.sumNumbers(sum));
    }

}
