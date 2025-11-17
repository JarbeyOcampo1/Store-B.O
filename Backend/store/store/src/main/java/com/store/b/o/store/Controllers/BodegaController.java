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
import com.store.b.o.store.Models.Bodega;
import com.store.b.o.store.Repositories.BodegaRepositories;

@RestController
@RequestMapping("/api/bodegas")
public class BodegaController {

    @Autowired
    private BodegaRepositories bodegaRepositories;

    // obtener todos los clientes
    @GetMapping
    public List<Bodega> getAllBodegas() {
        return bodegaRepositories.findAll();
    }

    // obtener una bodega por id
    @GetMapping("/{bodegaID}")
    public Bodega getBodegaById(@PathVariable Long bodegaID) {
        return bodegaRepositories.findById(bodegaID).orElse(null);
    };

    // Crear bodega
    @PostMapping
    public Bodega createBodega(@RequestBody Bodega bodega) {
        return bodegaRepositories.save(bodega);
    };

    // Actualizar bodega
    @PutMapping("/{bodegaID}")
    public Bodega updateBodega(@PathVariable Long bodegaID, @RequestBody Bodega bodega) {
        bodega.setBodegaID(bodegaID);
        return bodegaRepositories.save(bodega);
    };

    // Eliminar bodega
    @DeleteMapping("/{bodegaID}")
    public void deleteBodega(@PathVariable Long bodegaID) {
        bodegaRepositories.deleteById(bodegaID);
    };
};
