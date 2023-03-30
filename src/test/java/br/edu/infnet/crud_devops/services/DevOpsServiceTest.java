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
        UsuarioDto usuarioNovo = new UsuarioDto("123456789-10", "Usuario Novo", Sexo.MASCULINO);
        Mockito.when(repository.save(usuarioNovo.toUsuario())).thenReturn(usuarioNovo.toUsuario());

        UsuarioDto usuarioNovoGravado = devOpsService.save(usuarioNovo);

        assertEquals(usuarioNovo.getCpf(), usuarioNovoGravado.getCpf());
        assertEquals(usuarioNovo.getNome(), usuarioNovoGravado.getNome());
        assertEquals(usuarioNovo.getSexo(), usuarioNovoGravado.getSexo());
    }

    @Test
    void find() {
        UsuarioDto usuarioOriginal = new UsuarioDto("333333333-33", "Usuario Pesquisado", Sexo.FEMININO);
        Mockito.when(repository.findById("333333333-33")).thenReturn(Optional.ofNullable(usuarioOriginal.toUsuario()));

        UsuarioDto usuarioRetornado = devOpsService.find("333333333-33");

        assertEquals(usuarioOriginal.getCpf(), usuarioRetornado.getCpf());
        assertEquals(usuarioOriginal.getNome(), usuarioRetornado.getNome());
        assertEquals(usuarioOriginal.getSexo(), usuarioRetornado.getSexo());
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
        devOpsService.delete("111111111-11");
        Mockito.verify(repository).deleteById("111111111-11");
    }
}