package br.edu.infnet.crud_devops.model;

import lombok.Data;

@Data
public class UsuarioDto {
    private String cpf;
    private String nome;
    private Sexo sexo;
}
