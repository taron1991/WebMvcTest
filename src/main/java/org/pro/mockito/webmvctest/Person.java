package org.pro.mockito.webmvctest;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 2,max = 40,message = "error")
    private String name;
    @Column(name = "surname")
    @Size(min = 2,max = 20,message = "error")
    private String surname;
    @Column(name = "age")
    private int age;


    public Person(int id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}
