package com.Mutation.Tr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "logging")
@Getter @Setter @ToString
public class Logging{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String remoteAddr;

    @Column
    private String requestUri;

    @Column
    private String time;
}
