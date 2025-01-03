package com.Mutation.Tr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter @Setter @ToString
public class GuestBoard extends BaseEntity{

    @Id
    @Column @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String ip;

    @Column
    private String realIp;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    @Lob
    private String content;
}
