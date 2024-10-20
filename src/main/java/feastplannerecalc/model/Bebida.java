package feastplannerecalc.model;


import jakarta.persistence.*;

@Entity
@Table(name = "bebidas")
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bebida", nullable = false)
    private String bebida;

    @ManyToOne
    @JoinColumn(name = "tipo", nullable = false)
    private BebidaTipo tipo;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public BebidaTipo getTipo() {
        return tipo;
    }

    public void setTipo(BebidaTipo tipo) {
        this.tipo = tipo;
    }
}
