package br.edu.infnet.crud_devops.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor @NoArgsConstructor @Data
public class Usuario {
    @Id
    private String cpf;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    public UsuarioDto toDto(){
        return new UsuarioDto(cpf, nome, sexo);
    }
}
