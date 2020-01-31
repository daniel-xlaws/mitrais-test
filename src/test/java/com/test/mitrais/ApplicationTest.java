package com.test.mitrais;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.mitrais.model.User;
import com.test.mitrais.pojo.RestResult;
import com.test.mitrais.repository.UserRepo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ApplicationTest {
    private static ObjectMapper objectMapper;
    private static RestTemplate restTemplate;

    @Autowired
    private UserRepo usersRepo;

    @BeforeAll
    public static void setupTest() {
        objectMapper = new ObjectMapper();
        restTemplate = new RestTemplate();
    }

    @Test
    public void testRegistration() throws JsonProcessingException {
        RestResult restResult = restTemplate.postForObject(
                "http://localhost:8181/user/register",
                new User() {{
                    setMobileNumber("+628995916859");
                    setFirstName("Daniel");
                    setLastName("Saputro");
                    setMobileNumber("+628995916859");
                    setEmailAddress("daniel.irwanto.saputro@gmail.com");
                    setGender('M');
                }},
                RestResult.class
        );

        System.out.println(objectMapper.writeValueAsString(restResult));
        Assert.assertTrue("Success Register", restResult.isSuccess());
    }
}
