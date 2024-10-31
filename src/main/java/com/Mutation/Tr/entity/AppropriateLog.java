package com.Mutation.Tr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "appropriateLog")
@Getter @Setter @ToString
public class AppropriateLog extends Log{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


}
