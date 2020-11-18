package com.loanlander.lending_engine.domain.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity
public final class User {
    @Id
    private String  username ;
    private  String firstname ;
    private  String lastname ;
    private  int age ;
    private  String occupation ;
}
