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
import D.S.Store.B0.models.Informe;
import D.S.Store.B0.repositories.InformeRepositories;

@RestController
@RequestMapping("/api/informes")
public class InformeController {
    
    @Autowired
    private InformeRepositories informeRepositories;

    //obtener todos los informes
    @GetMapping
    public List <Informe> getAllInformes () {
        return informeRepositories.findAll();
    };

    //obtener una informe por id
    @GetMapping("/{informeID}")
    public Informe getInformeById (@PathVariable Long informeID) {
        return informeRepositories.findById(informeID).orElse(null);
    };

    //crear informe
    @PostMapping
    public Informe createInforme (@RequestBody Informe informe) {
        return informeRepositories.save(informe);
    };

    //Actualizar informe
    @PutMapping("/{informeID}")
    public Informe updateInforme (@PathVariable Long informeID, @RequestBody Informe informe) {
        informe.setInformeID(informeID);
        return informeRepositories.save(informe);
    };

    //eliminar informe
    @DeleteMapping("/{informeID}")
    public void deleteInforme (@PathVariable Long informeID) {
        informeRepositories.deleteById(informeID);
    };
};
