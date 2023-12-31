package br.com.kauasilveira.todolist.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_Users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    //@Column(name = "Usuario") -> altero o nome da column desta forma
    @Column(unique = true) // Testa se ja existe no banco de dados
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
