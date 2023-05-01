package org.pro.mockito.webmvctest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLayer {

    private final RepositoryLayer repositoryLayer;

    @Autowired
    public ServiceLayer(RepositoryLayer repositoryLayer) {
        this.repositoryLayer = repositoryLayer;
    }

    public List<Person> getPersons() {
        return repositoryLayer.findAll();
    }

    public Person addNewPerosn(Person person) {
        return repositoryLayer.save(person);
    }

    public void deletePersonById(int id) {
         repositoryLayer.deleteById(id);
    }

    public Optional<Person> findByName(String name) {
        return repositoryLayer.findByName(name);
    }

    public Person findById(int id) {
        return repositoryLayer.findById(id).get();
    }
}
