package com.lordalucard90.howtotest.spring.databases.entity;


import com.lordalucard90.howtotest.spring.databases.Application;
import com.lordalucard90.howtotest.spring.databases.repository.SimpleEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
//@ActiveProfiles("h2")
@ActiveProfiles("mysql")
//@ActiveProfiles("postgres")
@Transactional
public class SimpleEntityTest {
    @Autowired
    private SimpleEntityRepository simpleEntityRepository;

    @Test
    public void givenSimpleEntityRepository_whenSaveAndRetrieveEntity_thenOK() {
        SimpleEntity simpleEntity = simpleEntityRepository.save(SimpleEntity.builder().text("test").build());
        SimpleEntity foundEntity = simpleEntityRepository.getOne(simpleEntity.getId());

        assertNotNull(foundEntity);
        assertEquals(simpleEntity.getText(), foundEntity.getText());
    }
}