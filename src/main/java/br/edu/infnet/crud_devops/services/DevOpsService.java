package br.edu.infnet.crud_devops.services;

import br.edu.infnet.crud_devops.controller.App;
import br.edu.infnet.crud_devops.model.Usuario;
import br.edu.infnet.crud_devops.model.UsuarioDto;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DevOpsService implements App {

    private static final Logger log = LoggerFactory.getLogger(DevOpsService.class);
    private final DevOpsRepository repository;
    private final Random random = new Random();

    public DevOpsService(DevOpsRepository repository) {
        this.repository = repository;
    }

    @Observed(name = "user.save",
            contextualName = "saving-user",
            lowCardinalityKeyValues = {"user", "user2"})
    @Override
    public UsuarioDto save(UsuarioDto usuarioDto) {
        log.info("Salvando 0 usuario {}", usuarioDto.getNome());
        try {
            Thread.sleep(random.nextLong(200L)); // simulates latency
            Usuario usuario = repository.save(usuarioDto.toUsuario());
            return usuario.toDto();
        }
        catch (InterruptedException e) {
            log.error("Execução interrompida, tente novamente.");
            throw new RuntimeException(e);
        }
    }

    @Observed(name = "user.find",
            contextualName = "finding-user",
            lowCardinalityKeyValues = {"user", "user2"})
    @Override
    public UsuarioDto find(String cpf) {
        log.info("Buscando usuario com cpf = {}", cpf);
        try {
            Thread.sleep(random.nextLong(200L)); // simulates latency
            Usuario usuario = repository.findById(cpf).orElseThrow();
            return usuario.toDto();
        }
        catch (InterruptedException e) {
            log.error("Execução interrompida, tente novamente.");
            throw new RuntimeException(e);
        }
    }

    @Observed(name = "user.findAll",
            contextualName = "finding-all-users",
            lowCardinalityKeyValues = {"user", "user2"})
    @Override
    public List<UsuarioDto> findAll() {

        log.info("Buscando todos os usuarios");
        try {
            Thread.sleep(random.nextLong(200L)); // simulates latency
            List<Usuario> usuarios = repository.findAll();
            return usuarios.stream().map(Usuario::toDto).toList();
        }
        catch (InterruptedException e) {
            log.error("Execução interrompida, tente novamente.");
            throw new RuntimeException(e);
        }
    }

    @Observed(name = "user.delete",
            contextualName = "deleting-user",
            lowCardinalityKeyValues = {"user", "user2"})
    @Override
    public void delete(String cpf) {

        log.info("Apagando usuario com o cpf = <{}>", cpf);
        try {
            Thread.sleep(random.nextLong(200L)); // simulates latency
            repository.deleteById(cpf);
        }
        catch (InterruptedException e) {
            log.error("Execução interrompida, tente novamente.");
            throw new RuntimeException(e);
        }
    }
}
