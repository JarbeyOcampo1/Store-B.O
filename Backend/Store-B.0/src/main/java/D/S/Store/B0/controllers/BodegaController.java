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
import D.S.Store.B0.models.Bodega;
import D.S.Store.B0.repositories.BodegaRepositories;

@RestController
@RequestMapping("/api/bodegas")
public class BodegaController {

    @Autowired
    private BodegaRepositories bodegaRepositories;

    //obtener todas las bodegas
    @GetMapping
    @PreAuthorize("hasAnyRole('GERENTE','EMPLEADO')")
    public List <Bodega> getAllBodegas () {
        return bodegaRepositories.findAll();
    };

    //obtener una bodega por id
    @GetMapping("/{bodegaID}")
    @PreAuthorize("hasAnyRole('GERENTE','EMPLEADO')")
    public Bodega getBodegaById (@PathVariable Long bodegaID) {
        return bodegaRepositories.findById(bodegaID).orElse(null);
    };

    //crear bodega
    @PostMapping
    @PreAuthorize("hasAnyRole('GERENTE','EMPLEADO')")
    public Bodega createBodega (@RequestBody Bodega bodega) {
        return bodegaRepositories.save(bodega);
    };

    //Actualizar cliente
    @PutMapping("/{bodegaID}")
    @PreAuthorize("hasAnyRole('GERENTE','EMPLEADO')")
    public Bodega updateBodega (@PathVariable Long bodegaID, @RequestBody Bodega bodega) {
        bodega.setBodegaID(bodegaID);
        return bodegaRepositories.save(bodega);
    };

    //eliminar bodega
    @DeleteMapping("/{bodegaID}")
    @PreAuthorize("hasRole('GERENTE')")
    public void deleteBodega (@PathVariable Long bodegaID) {
        bodegaRepositories.deleteById(bodegaID);
    };
};
