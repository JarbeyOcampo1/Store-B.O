package com.store.b.o.store.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.store.b.o.store.Models.Cliente;
import com.store.b.o.store.Repositories.ClienteRepositories;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteRepositories clienteRepositories;

    //obtener todos los clientes
    @GetMapping
    public List <Cliente> getAllClientes () {
        return clienteRepositories.findAll();
    }

    //obtener un cliente por id
    @GetMapping("/{clienteID}")
    public Cliente getClienteById (@PathVariable Long clienteID) {
        return clienteRepositories.findById(clienteID).orElse(null);
    };

    //Crear cliente
    @PostMapping
    public Cliente createCliente (@RequestBody Cliente cliente) {
        return clienteRepositories.save(cliente);
    };

    //Actualizar cliente
    @PutMapping("/{clienteID}")
    public Cliente updateCliente (@PathVariable Long clienteID, @RequestBody Cliente cliente) {
        cliente.setClienteID(clienteID);
        return clienteRepositories.save(cliente);
    };

    //Eliminar cliente
    @DeleteMapping("/{clienteID}")
    public void deleteCliente (@PathVariable Long clienteID) {
        clienteRepositories.deleteById(clienteID);
    };
};
