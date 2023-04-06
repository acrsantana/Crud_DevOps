package br.edu.infnet.crud_devops.services;

import br.edu.infnet.crud_devops.controller.App;
import br.edu.infnet.crud_devops.model.Sexo;
import br.edu.infnet.crud_devops.model.Usuario;
import br.edu.infnet.crud_devops.model.UsuarioDto;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DevOpsService implements App {

    private static final Logger log = LoggerFactory.getLogger(DevOpsService.class);
    private final DevOpsRepository repository;
    private final String URL = "http://hellodevops:8081/hello";
    private final ObservationRegistry registry;
    private final RestTemplate restTemplate;
    private final Random random = new Random();

    public DevOpsService(DevOpsRepository repository, ObservationRegistry registry, RestTemplate restTemplate) {
        this.repository = repository;
        this.registry = registry;
        this.restTemplate = restTemplate;
    }

    @Override
    public String hello(String cpf) {
        log.info("Hello usuario");
        Optional<Usuario> byId = repository.findById(cpf);
        if (byId.isEmpty()){
            throw new RuntimeException();
        }
        UsuarioDto dto = byId.get().toDto();
        Observation observation = Observation.createNotStarted("hello.devops.request", registry)
                .lowCardinalityKeyValue("userType", dto.getSexo().name())
                .highCardinalityKeyValue("userId", cpf)
                .contextualName("span-hello-user")
                .start();
        log.info("Realizando requisição para HelloDevOps");

//        String response = restTemplate.getForObject("http://hellodevops:8081/hello", String.class, cpf);
        String response = restTemplate.postForObject(URL, dto, String.class);

        log.info("{}, {}", response, dto.getNome());


        observation.stop();
        return response;
    }

    @Override
    public UsuarioDto save(UsuarioDto usuarioDto) {
        log.info("Salvando 0 usuario {}", usuarioDto.getNome());
        Usuario usuario = repository.save(usuarioDto.toUsuario());
        return usuario.toDto();
    }

    @Override
    public UsuarioDto find(String cpf) {
        log.info("Buscando usuario com cpf = {}", cpf);
        Usuario usuario = repository.findById(cpf).orElseThrow();
        return usuario.toDto();
    }

    @Override
    public List<UsuarioDto> findAll() {

        log.info("Buscando todos os usuarios");
        List<Usuario> usuarios = repository.findAll();
        return usuarios.stream().map(Usuario::toDto).toList();
    }

    @Override
    public void delete(String cpf) {

        log.info("Apagando usuario com o cpf = <{}>", cpf);
        repository.deleteById(cpf);
    }
}
