package org.pro.mockito.webmvctest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryLayer extends JpaRepository<Person,Integer> {

    Optional<Person> findByName(String name);
}
