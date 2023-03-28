package br.edu.infnet.crud_devops.services;

import br.edu.infnet.crud_devops.model.Sexo;
import br.edu.infnet.crud_devops.model.Usuario;
import br.edu.infnet.crud_devops.model.UsuarioDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DevOpsServiceTest {

    @Mock
    DevOpsRepository repository;
    DevOpsService devOpsService;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.devOpsService = new DevOpsService(repository);
    }

    @Test
    void save() {
        UsuarioDto daBahia = new UsuarioDto("727228275-49", "Cezão da Bahia", Sexo.MASCULINO);
        Mockito.when(repository.save(daBahia.toUsuario())).thenReturn(daBahia.toUsuario());

        UsuarioDto daBahiaGravado = devOpsService.save(daBahia);

        assertEquals(daBahia.getCpf(), daBahiaGravado.getCpf());
        assertEquals(daBahia.getNome(), daBahiaGravado.getNome());
        assertEquals(daBahia.getSexo(), daBahiaGravado.getSexo());
    }

    @Test
    void find() {
        UsuarioDto daBahia = new UsuarioDto("727228275-49", "Cezão da Bahia", Sexo.MASCULINO);
        Mockito.when(repository.findById("727228275-49")).thenReturn(Optional.ofNullable(daBahia.toUsuario()));

        UsuarioDto usuarioDto = devOpsService.find("727228275-49");

        assertEquals(daBahia.getCpf(), usuarioDto.getCpf());
        assertEquals(daBahia.getNome(), usuarioDto.getNome());
        assertEquals(daBahia.getSexo(), usuarioDto.getSexo());
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(usuarios());

        List<UsuarioDto> list = devOpsService.findAll();

        assertEquals(2, list.size());
    }

    private List<Usuario> usuarios(){
        Usuario u1 = new Usuario("111111111-11", "Fulano", Sexo.MASCULINO);
        Usuario u2 = new Usuario("222222222-22", "Fulana", Sexo.FEMININO);
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(u1);
        usuarios.add(u2);
        return usuarios;
    }

    @Test
    void delete() {
        devOpsService.delete("727228275-49");
        Mockito.verify(repository).deleteById("727228275-49");
    }
}