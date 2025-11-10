package com.example.registro.entity;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(length = 36)
    private String id;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneEntity> phones = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
        this.created = LocalDateTime.now();
        this.modified = this.created;
        this.lastLogin = this.created;
        this.isActive = true;
    }

	    // getters y setters
    
}
