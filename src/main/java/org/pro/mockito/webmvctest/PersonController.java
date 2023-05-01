package org.pro.mockito.webmvctest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final ServiceLayer serviceLayer;

    @Autowired
    public PersonController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @GetMapping("/getPersons")
    public List<Person> getALlPersons(){
        return serviceLayer.getPersons();
    }

    @GetMapping("/ByName/{name}")
    public Optional<Person> byName(@PathVariable("name") String name){
        return serviceLayer.findByName(name);
    }


    @GetMapping("/ById/{id}")
    public Person byName(@PathVariable("id") int id){
        return serviceLayer.findById(id);
    }

    @PostMapping("/addPerson")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
       return ResponseEntity.ok(serviceLayer.addNewPerosn(person));
    }

    @DeleteMapping("/delById")
    public void deletePersonById(@RequestParam("id") int id){
        serviceLayer.deletePersonById(id);
    }
}
