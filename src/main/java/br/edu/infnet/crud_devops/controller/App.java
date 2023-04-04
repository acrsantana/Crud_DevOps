package br.edu.infnet.crud_devops.controller;

import br.edu.infnet.crud_devops.model.UsuarioDto;

import java.util.List;

public interface App {

    String hello(String cpf);
    UsuarioDto save(UsuarioDto usuarioDto);
    UsuarioDto find(String cpf);
    List<UsuarioDto> findAll();
    void delete(String cpf);
}
