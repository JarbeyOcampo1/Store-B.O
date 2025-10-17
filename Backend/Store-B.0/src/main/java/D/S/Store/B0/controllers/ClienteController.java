package D.S.Store.B0.controllers;

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
import org.springframework.security.access.prepost.PreAuthorize;
import D.S.Store.B0.models.Cliente;
import D.S.Store.B0.repositories.ClienteRepositories;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepositories clienteRepositories;

    //obtener todos los clientes
    @GetMapping
    @PreAuthorize("hasAnyRole('GERENTE','EMPLEADO')")
    public List <Cliente> getAllClientes () {
        return clienteRepositories.findAll();
    };

    //obtener un cliente por id
    @GetMapping("/{clienteID}")
    @PreAuthorize("hasAnyRole('GERENTE','EMPLEADO')")
    public Cliente getClienteById (@PathVariable Long clienteID) {
        return clienteRepositories.findById(clienteID).orElse(null);
    };

    //Crear cliente
    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public Cliente createCliente (@RequestBody Cliente cliente) {
        return clienteRepositories.save(cliente);
    };

    //Actualizar cliente
    @PutMapping("/{clienteID}")
    @PreAuthorize("hasRole('GERENTE')")
    public Cliente updateCliente (@PathVariable Long clienteID, @RequestBody Cliente cliente) {
        cliente.setClienteID(clienteID);
        return clienteRepositories.save(cliente);
    };

    //Eliminar cliente
    @DeleteMapping("/{clienteID}")
    @PreAuthorize("hasRole('GERENTE')")
    public void deleteCliente (@PathVariable Long clienteID) {
        clienteRepositories.deleteById(clienteID);
    };
};
