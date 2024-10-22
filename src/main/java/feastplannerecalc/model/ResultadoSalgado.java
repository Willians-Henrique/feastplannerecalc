package feastplannerecalc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resultado_salgado")
public class ResultadoSalgado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_simulacao", nullable = false)
    private ResultadoSimulacao simulacao;

    @ManyToOne
    @JoinColumn(name = "id_tipo_salgado", nullable = false)
    private ComidaCategoriaSalgado tipoSalgado;

    @Column(name = "quantidade", nullable = false)
    private Double quantidade;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResultadoSimulacao getSimulacao() {
        return simulacao;
    }

    public void setSimulacao(ResultadoSimulacao simulacao) {
        this.simulacao = simulacao;
    }

    public ComidaCategoriaSalgado getTipoSalgado() {
        return tipoSalgado;
    }

    public void setTipoSalgado(ComidaCategoriaSalgado tipoSalgado) {
        this.tipoSalgado = tipoSalgado;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
