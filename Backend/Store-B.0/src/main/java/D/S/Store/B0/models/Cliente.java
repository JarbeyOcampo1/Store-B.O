package D.S.Store.B0.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    
    //Atributos Cliente
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long clienteID;

    private String cedulaCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String emailCliente;
    private String telefonoCliente;
    private String direccionCliente;
    private String fechaRegistro;
    private String estadoCliente;
};
