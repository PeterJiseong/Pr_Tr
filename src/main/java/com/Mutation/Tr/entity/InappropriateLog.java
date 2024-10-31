package com.Mutation.Tr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "inappropriateLogg")
@Getter @Setter @ToString
public class InappropriateLog extends Log{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

}
