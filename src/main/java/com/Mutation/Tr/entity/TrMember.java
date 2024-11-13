package com.Mutation.Tr.entity;

import com.Mutation.Tr.constant.MemberRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TrMember")
@Getter @Setter @ToString
public class TrMember {

    @Id
    @Column @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String trId;

    @Column
    private String memberName;

    @Column
    private String phoneNumber;

    @Column
    private int age;

    @Column
    private String gender;

    @Column
    private MemberRole role;

}
