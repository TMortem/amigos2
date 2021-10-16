package com.unitec.amigos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControladorUsuario
{
    @Autowired DepositorioUsuario repoUsuario;


    // Guardar usuario en MongoDB
    @PostMapping ("/usuario")
    public Estatus guardar (@RequestBody String json) throws Exception
    {
        //Leer y convertir el objeto a java
        ObjectMapper mapper=new ObjectMapper();
        Usuario u=mapper.readValue(json, Usuario.class);
        // Usuario en json se guardará en mongo DB
        repoUsuario.save(u);
        // Objeto tipo Estatus
        Estatus estatus=new Estatus();
        estatus.setSuccess(true);
        estatus.setMensaje("Te registraste correctamente");
        return estatus;
    }


    // Metodo que representa cada uno de los estados a transferir
    @GetMapping("/usuario/{id}")
    public Usuario obtenerPorId(@PathVariable String id)
    {
        // Leer un usuario con el metodo find by id pasando como argumento el id que se desea
        Usuario u = repoUsuario.findById(id).get();
        return u;
    }

    @GetMapping("/usuario")
    public List<Usuario> buscarTodos()
    {
        return repoUsuario.findAll();
    }

    //Metodo para actualizar
    @PutMapping("/usuario")
    public Estatus actualizar(@RequestBody String json) throws  Exception
    {
        // Verificar que exista
        ObjectMapper mapper = new ObjectMapper();
        Usuario u = mapper.readValue(json, Usuario.class);
        Estatus e = new Estatus();
        if (repoUsuario.findById(u.getEmail()).isPresent())
        {
            repoUsuario.save(u);
            e.setMensaje("Usuario actualizado con éxito");
            e.setSuccess(true);
        }
        else
        {
            e.setMensaje("No se pudo actualizar correctamente");
            e.setSuccess(false);
        }
        return e;
    }

    @DeleteMapping("/usuario/{id}")
    public Estatus borrar (@PathVariable String id)
    {
        Estatus estatus = new Estatus();
        if (repoUsuario.findById(id).isPresent())
        {
            repoUsuario.deleteById(id);
            estatus.setSuccess(true);
            estatus.setMensaje("Usuario Borrado con Éxito");
        }
        else
        {
            estatus.setSuccess(false);
            estatus.setMensaje("Este Usuario no Existe");
        }
        return estatus;
    }

}
