package br.edu.infnet.crud_devops.services;

import br.edu.infnet.crud_devops.controller.App;
import br.edu.infnet.crud_devops.model.UsuarioDto;
import br.edu.infnet.crud_devops.model.Usuario;
import br.edu.infnet.crud_devops.repository.DevOpsRepository;
import io.micrometer.observation.annotation.Observed;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DevOpsService implements App {

    private static final Logger log = LoggerFactory.getLogger(DevOpsService.class);
    private final DevOpsRepository repository;
    ModelMapper mapper = new ModelMapper();
    private final Random random = new Random();

    public DevOpsService(DevOpsRepository repository) {
        this.repository = repository;
    }

    @Observed(name = "user.save",
            contextualName = "saving-user",
            lowCardinalityKeyValues = {"user", "user2"})
    @Override
    public UsuarioDto save(UsuarioDto usuarioDto) {
        log.info("Salvando usuario com cpf = <{}>", usuarioDto.getCpf());
        try {
            Thread.sleep(random.nextLong(200L)); // simulates latency
            Usuario usuario = repository.save(mapper.map(usuarioDto, Usuario.class));
            return mapper.map(usuario, UsuarioDto.class);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Observed(name = "user.find",
            contextualName = "finding-user",
            lowCardinalityKeyValues = {"user", "user2"})
    @Override
    public UsuarioDto find(String cpf) {
        log.info("Buscando usuario com cpf = <{}>", cpf);
        try {
            Thread.sleep(random.nextLong(200L)); // simulates latency
            Usuario usuario = repository.findById(cpf).orElseThrow();
            return mapper.map(usuario, UsuarioDto.class);
        }
        catch (InterruptedException e) {
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
            return usuarios.stream().map(usuario -> mapper.map(usuario, UsuarioDto.class)).toList();
        }
        catch (InterruptedException e) {
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
            throw new RuntimeException(e);
        }
    }
}
