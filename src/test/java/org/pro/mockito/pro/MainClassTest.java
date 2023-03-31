package org.pro.mockito.pro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.pro.mockito.project.Dependency;
import org.pro.mockito.project.MainClass;
import org.pro.mockito.project.MyMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MainClassTest {

    @MockBean
    Dependency dependency;
    @Autowired
    MainClass mainClass;


    @Test
    void findArrayLength(){
        Mockito.when(dependency.arrayLength()).thenReturn(new int[]{2,24,112});
        Assertions.assertEquals(3,mainClass.findArrayLength());
    }


    @Test
    void findTheGreatestFromAllDataTest() {

        Mockito.when(dependency.retrieveAllData()).thenReturn(new int[]{5, 12, 9});

        Assertions.assertEquals(2, mainClass.findTheGreatestFromAllData());
    }


    @Test
    void sumTestFromArray() {
        MyMath myMath = new MyMath();

        int sum = myMath.sum(new int[]{1, 2, 3, 4, 5});

        Assertions.assertEquals(15, sum);


    }
}