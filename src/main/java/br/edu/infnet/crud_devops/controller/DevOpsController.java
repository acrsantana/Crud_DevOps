package br.edu.infnet.crud_devops.controller;

import br.edu.infnet.crud_devops.model.UsuarioDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class DevOpsController {

    private static final Logger log = LoggerFactory.getLogger(DevOpsController.class);
    private final App devOpsService;

    public DevOpsController(App devOpsService) {

        this.devOpsService = devOpsService;
    }

    @PostMapping(produces = "application/json")
    public UsuarioDto save(@RequestBody UsuarioDto usuario) {
        log.info("save got a request");
        return devOpsService.save(usuario);
    }

    @GetMapping("/{cpf}")
    public UsuarioDto findById(@PathVariable("cpf") String cpf) {
        log.info("findById got a request");
        return devOpsService.find(cpf);
    }

    @GetMapping
    public List<UsuarioDto> findAll(){
        log.info("findAll got a request");
        return devOpsService.findAll();
    }

    @DeleteMapping("/{cpf}")
    public void deleteById(@PathVariable("cpf") String cpf) {
        log.info("deleteById got a request");
        devOpsService.delete(cpf);
    }

    @GetMapping("/hello/{cpf}")
    public String hello(@PathVariable("cpf") String cpf){
        log.info("hello got a request");
        return devOpsService.hello(cpf);
    }
}
