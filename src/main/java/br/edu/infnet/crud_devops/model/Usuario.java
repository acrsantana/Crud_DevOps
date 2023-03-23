package br.edu.infnet.crud_devops.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    private String cpf;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
}
