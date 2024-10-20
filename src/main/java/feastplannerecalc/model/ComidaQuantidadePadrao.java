package feastplannerecalc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comidas_quantidade_padrao")
public class ComidaQuantidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoas_categoria", nullable = false)
    private PessoaCategoria pessoasCategoria;

    @Column(name = "quantidade_carne", nullable = false)
    private Integer quantidadeCarne;

    @Column(name = "quantidade_salgado", nullable = false)
    private Integer quantidadeSalgado;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PessoaCategoria getPessoasCategoria() {
        return pessoasCategoria;
    }

    public void setPessoasCategoria(PessoaCategoria pessoasCategoria) {
        this.pessoasCategoria = pessoasCategoria;
    }

    public Integer getQuantidadeCarne() {
        return quantidadeCarne;
    }

    public void setQuantidadeCarne(Integer quantidadeCarne) {
        this.quantidadeCarne = quantidadeCarne;
    }

    public Integer getQuantidadeSalgado() {
        return quantidadeSalgado;
    }

    public void setQuantidadeSalgado(Integer quantidadeSalgado) {
        this.quantidadeSalgado = quantidadeSalgado;
    }
}
