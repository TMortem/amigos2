package com.unitec.amigos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControladorHola
{

    @Autowired DepositorioUsuario repoUsuario;
    @PostMapping("/usuario")
    public void guardar(@RequestBody String json) throws Exception {

    }

    // Este recurso es un Hola Mundo de un servicio REST con metodo GET
    @GetMapping("/hola")
    public String saludar()
    {
        return "Hola desde servicio REST";
    }
}
