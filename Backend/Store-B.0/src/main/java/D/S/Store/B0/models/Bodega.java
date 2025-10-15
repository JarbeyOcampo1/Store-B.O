package D.S.Store.B0.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bodegas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bodega {
    
    //Atributos Bodega
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long bodegaID;

    private String nombreBodega;
    private String ubicacionBodega;
    private String tamanoBodega;
    private String fechaRegistroBodega;
    private String descripcionBodega;
    private String estadoBodega;

    //Relacion con Cliente (muchos a uno)
    @ManyToOne
    @JoinColumn(name = "clienteid", referencedColumnName = "clienteID")
    private Cliente cliente;

};
