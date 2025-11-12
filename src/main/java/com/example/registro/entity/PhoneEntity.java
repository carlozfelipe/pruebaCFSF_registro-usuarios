package com.example.registro.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "phones")
public class PhoneEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private Integer citycode;
    private Integer contrycode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

	    // getters y setters
    
}
