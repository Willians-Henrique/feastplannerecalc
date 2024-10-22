package feastplannerecalc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resultado_simulacao")
public class ResultadoSimulacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_comida", nullable = false)
    private ComidaTipo tipoComida;

    @Column(name = "quantidade_homens", nullable = false)
    private Integer quantidadeHomens;

    @Column(name = "quantidade_mulheres", nullable = false)
    private Integer quantidadeMulheres;

    @Column(name = "quantidade_criancas", nullable = false)
    private Integer quantidadeCriancas;

    @Column(name = "quantidade_comiloes", nullable = false)
    private Integer quantidadeComiloes;

    @Column(name = "quantidade_nao_comem_carne", nullable = false)
    private Integer quantidadeNaoComemCarne;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComidaTipo getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(ComidaTipo tipoComida) {
        this.tipoComida = tipoComida;
    }

    public Integer getQuantidadeHomens() {
        return quantidadeHomens;
    }

    public void setQuantidadeHomens(Integer quantidadeHomens) {
        this.quantidadeHomens = quantidadeHomens;
    }

    public Integer getQuantidadeMulheres() {
        return quantidadeMulheres;
    }

    public void setQuantidadeMulheres(Integer quantidadeMulheres) {
        this.quantidadeMulheres = quantidadeMulheres;
    }

    public Integer getQuantidadeCriancas() {
        return quantidadeCriancas;
    }

    public void setQuantidadeCriancas(Integer quantidadeCriancas) {
        this.quantidadeCriancas = quantidadeCriancas;
    }

    public Integer getQuantidadeComiloes() {
        return quantidadeComiloes;
    }

    public void setQuantidadeComiloes(Integer quantidadeComiloes) {
        this.quantidadeComiloes = quantidadeComiloes;
    }

    public Integer getQuantidadeNaoComemCarne() {
        return quantidadeNaoComemCarne;
    }

    public void setQuantidadeNaoComemCarne(Integer quantidadeNaoComemCarne) {
        this.quantidadeNaoComemCarne = quantidadeNaoComemCarne;
    }
}
