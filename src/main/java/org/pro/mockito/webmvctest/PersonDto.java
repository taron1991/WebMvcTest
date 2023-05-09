package org.pro.mockito.webmvctest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {


    private String name;
    private String surname;
    private Integer age;
}
