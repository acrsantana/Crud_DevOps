package br.edu.infnet.crud_devops.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UsuarioDto {
    private String cpf;
    private String nome;
    private Sexo sexo;

    public Usuario toUsuario(){
        return new Usuario(cpf, nome, sexo);
    }
}
