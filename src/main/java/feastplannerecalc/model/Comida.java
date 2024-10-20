package feastplannerecalc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comidas")
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_tipo_comida")
    private ComidaTipo tipoComida;

    @ManyToOne
    @JoinColumn(name = "id_categoria_carne")
    private ComidaCategoriaCarne categoriaCarne;

    @ManyToOne
    @JoinColumn(name = "id_categoria_salgado")
    private ComidaCategoriaSalgado categoriaSalgado;

    @Column(name = "opcao_sem_carne")
    private Boolean opcaoSemCarne;

    @Column(name = "aproveitamento")
    private Double aproveitamento;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ComidaTipo getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(ComidaTipo tipoComida) {
        this.tipoComida = tipoComida;
    }

    public ComidaCategoriaCarne getCategoriaCarne() {
        return categoriaCarne;
    }

    public void setCategoriaCarne(ComidaCategoriaCarne categoriaCarne) {
        this.categoriaCarne = categoriaCarne;
    }

    public ComidaCategoriaSalgado getCategoriaSalgado() {
        return categoriaSalgado;
    }

    public void setCategoriaSalgado(ComidaCategoriaSalgado categoriaSalgado) {
        this.categoriaSalgado = categoriaSalgado;
    }

    public Boolean getOpcaoSemCarne() {
        return opcaoSemCarne;
    }

    public void setOpcaoSemCarne(Boolean opcaoSemCarne) {
        this.opcaoSemCarne = opcaoSemCarne;
    }

    public Double getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(Double aproveitamento) {
        this.aproveitamento = aproveitamento;
    }
}
