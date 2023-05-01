package org.pro.mockito.webmvctest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PersonController.class)
//будем тестировать только слой контроллеров тоесть создаться бин контроллера только
class PersonControllerTest {

    /*Mock-MVC позволяет нам тестировать обработку запросов Spring-MVC без запуска реального сервера
    * То есть при выполнении тестов сетевое соединение не создается*/
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayer;


    @Test
    void addPersonTestMethod() throws Exception {
        Person person = new Person(3,"name","surname",23);
        Mockito.when(serviceLayer.addNewPerosn(person)).thenReturn(person);
        mockMvc.perform(post("/api/person/addPerson").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(person)))  //сериализуем в стринг
                .andExpect(status().isOk());

        /*contentType способ передачи контента
        ObjectMapper
Этот класс преобразовывает объект в JSON-строку.
        * */
    }


    @Test
    void getPersonByIdTestMethod() throws Exception {
        Person person = new Person(9,"Igor","surname",23);
        Mockito.when(serviceLayer.findById(9)).thenReturn(person);
        mockMvc.perform(get("/api/person/ById/{id}",9).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Igor"));
    }


    @Test
    void getAllPersonsMethod() throws Exception {
        List<Person> people = List.of(new Person(9, "name", "surname", 23), new Person(19, "name2", "surname2", 11));

        Mockito.when(serviceLayer.getPersons()).thenReturn(people);
        mockMvc.perform(get("/api/person/getPersons").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].name").value("name2"));
    }


    @Test
    void deleteMethodTest() throws Exception {
        Person person = new Person(9,"name","surname",23);
        Mockito.doNothing().when(serviceLayer).deletePersonById(9);

        /*через request body*/
        mockMvc.perform(delete("/api/person/delById?id=9").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(status().isOk());

    }


    @Test
    void getPersonByNameTestMethod() throws Exception {
        Person person = new Person(9,"Igor","Vdovin",23);
        Mockito.when(serviceLayer.findByName("Igor")).thenReturn(Optional.of(person));
        mockMvc.perform(get("/api/person/ByName/{name}","Igor").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname").value("Vdovin"));
    }

}