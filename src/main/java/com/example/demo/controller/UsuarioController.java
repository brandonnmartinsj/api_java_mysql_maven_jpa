package com.example.demo.controller;


import com.example.demo.controller.model.UsuarioModel;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping(path = "/api/usuarios")
    public Iterable<UsuarioModel> findAll(){
        return repository.findAll();
    }


    @GetMapping(path = "/api/usuario/{codigo}")
    public ResponseEntity consultar (@PathVariable("codigo") Integer codigo) {

        return repository.findById(codigo)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping(path = "/api/usuario/salvar")
    public UsuarioModel salvar(@RequestBody UsuarioModel usuario) {

        return repository.save(usuario);
    }

    @DeleteMapping(path ={"/api/usuario/{codigo}"})
    public ResponseEntity <?> delete(@PathVariable Integer codigo) {
        return repository.findById(codigo)
                .map(record -> {
                    repository.deleteById(codigo);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
