package br.com.kauasilveira.todolist.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_Tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(length = 50, unique = true)
    private String titulo;
    private String descricao;
    private LocalDateTime dataDeInicio;
    private LocalDateTime dataDeTermino;
    private String prioridade;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitulo(String titulo) throws Exception{
        if(titulo.length() > 50){
            throw new Exception("O campo titulo deve conter no máximo 50 caracteres");
        }
        this.titulo = titulo;
    }

}
