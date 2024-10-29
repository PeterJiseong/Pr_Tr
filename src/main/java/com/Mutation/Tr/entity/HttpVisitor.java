package com.Mutation.Tr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "HttpVisitor")
@Getter @Setter @ToString
public class HttpVisitor extends BaseTimeEntity {

    @Id
    private long sessionId;

    @Column
    private long visitorCount;


}
