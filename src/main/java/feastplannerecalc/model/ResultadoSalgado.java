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

    // Salgados
    @Column(name = "pizza", nullable = false)
    private Double pizza;
    
    @Column(name = "pizza_queijo", nullable = false)
    private Double pizzaDeQueijo;

    @Column(name = "esfirra", nullable = false)
    private Double esfirra;
    
    @Column(name = "esfirra_queijo", nullable = false)
    private Double esfirraDeQueijo;

    @Column(name = "hamburger", nullable = false)
    private Double hamburger;

    @Column(name = "empadinha", nullable = false)
    private Double empadinha;

    @Column(name = "empadinha_queijo", nullable = false)
    private Double empadinhaDeQueijo;
    
    @Column(name = "coxinha", nullable = false)
    private Double coxinha;

    @Column(name = "kibe", nullable = false)
    private Double kibe;

    @Column(name = "pastel", nullable = false)
    private Double pastel;
    
    @Column(name = "pastel_queijo", nullable = false)
    private Double pastelDeQueijo;

    @Column(name = "bolinha_queijo", nullable = false)
    private Double bolinhaDeQueijo;

    @Column(name = "risoles", nullable = false)
    private Double risoles;
    
    @Column(name = "cigarette", nullable = false)
    private Double cigarette;
    
    @Column(name = "cigarette_queijo", nullable = false)
    private Double cigaretteDeQueijo;

    @Column(name = "croquete", nullable = false)
    private Double croquete;

    // Adicionais
    @Column(name = "arroz", nullable = false)
    private Double arroz;

    @Column(name = "farofa", nullable = false)
    private Double farofa;

    @Column(name = "vinagrete", nullable = false)
    private Double vinagrete;

    @Column(name = "pao_alho", nullable = false)
    private Double paoAlho;

    @Column(name = "pao_frances", nullable = false)
    private Double paoFrances;

    @Column(name = "queijo_coalho", nullable = false)
    private Double queijoCoalho;

    // Itens obrigatórios
    @Column(name = "copo", nullable = false)
    private Double copo;

    @Column(name = "prato", nullable = false)
    private Double prato;

    @Column(name = "papel_toalha", nullable = false)
    private Double papelToalha;

    // Getters e Setters para todas as colunas
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
    
    public Double getPizza() {
        return pizza;
    }

    public void setPizza(Double pizza) {
        this.pizza = pizza;
    }

    public Double getPizzaDeQiejo() {
        return pizzaDeQueijo;
    }

    public void setPizzaDeQueijo(Double pizzaDeQueijo) {
        this.pizzaDeQueijo = pizzaDeQueijo;
    }
    
    public Double getEsfirra() {
        return esfirra;
    }

    public void setEsfirra(Double esfirra) {
        this.esfirra = esfirra;
    }
    
    public Double getEsfirraDeQueijo() {
        return esfirraDeQueijo;
    }

    public void setEsfirraDeQueijo(Double esfirraDeQueijo) {
        this.esfirraDeQueijo = esfirraDeQueijo;
    }

    public Double getHamburger() {
        return hamburger;
    }

    public void setHamburger(Double hamburger) {
        this.hamburger = hamburger;
    }

    public Double getEmpadinha() {
        return empadinha;
    }

    public void setEmpadinha(Double empadinha) {
        this.empadinha = empadinha;
    }
    
    public Double getEmpadinhaDeQueijo() {
        return empadinhaDeQueijo;
    }

    public void setEmpadinhaDeQueijo(Double empadinhaDeQueijo) {
        this.empadinhaDeQueijo = empadinhaDeQueijo;
    }

    public Double getCoxinha() {
        return coxinha;
    }

    public void setCoxinha(Double coxinha) {
        this.coxinha = coxinha;
    }

    public Double getKibe() {
        return kibe;
    }

    public void setKibe(Double kibe) {
        this.kibe = kibe;
    }

    public Double getPastel() {
        return pastel;
    }
    
    public void setPastel(Double pastel) {
        this.pastel = pastel;
    }
    
    public Double getPastelDeQueijo() {
        return pastelDeQueijo;
    }
    
    public void setPastelDeQueijo(Double pastelDeQueijo) {
        this.pastelDeQueijo = pastelDeQueijo;
    }

    public Double getBolinhaDeQueijo() {
        return bolinhaDeQueijo;
    }

    public void setBolinhaDeQueijo(Double bolinhaDeQueijo) {
        this.bolinhaDeQueijo = bolinhaDeQueijo;
    }

    public Double getRisoles() {
        return risoles;
    }

    public void setRisoles(Double risoles) {
        this.risoles = risoles;
    }

    public Double getCigaretteDeQueijo() {
        return cigaretteDeQueijo;
    }

    public void setCigaretteDeQueijo(Double cigaretteDeQueijo) {
        this.cigaretteDeQueijo = cigaretteDeQueijo;
    }

    public Double getCigarette() {
        return cigarette;
    }

    public void setCigarette(Double cigarette) {
        this.cigarette = cigarette;
    }
    
    public Double getCroquete() {
        return croquete;
    }

    public void setCroquete(Double croquete) {
        this.croquete = croquete;
    }

    public Double getArroz() {
        return arroz;
    }

    public void setArroz(Double arroz) {
        this.arroz = arroz;
    }

    public Double getFarofa() {
        return farofa;
    }

    public void setFarofa(Double farofa) {
        this.farofa = farofa;
    }

    public Double getVinagrete() {
        return vinagrete;
    }

    public void setVinagrete(Double vinagrete) {
        this.vinagrete = vinagrete;
    }

    public Double getPaoAlho() {
        return paoAlho;
    }

    public void setPaoAlho(Double paoAlho) {
        this.paoAlho = paoAlho;
    }

    public Double getPaoFrances() {
        return paoFrances;
    }

    public void setPaoFrances(Double paoFrances) {
        this.paoFrances = paoFrances;
    }

    public Double getQueijoCoalho() {
        return queijoCoalho;
    }

    public void setQueijoCoalho(Double queijoCoalho) {
        this.queijoCoalho = queijoCoalho;
    }

    public Double getCopo() {
        return copo;
    }

    public void setCopo(Double copo) {
        this.copo = copo;
    }

    public Double getPrato() {
        return prato;
    }

    public void setPrato(Double prato) {
        this.prato = prato;
    }

    public Double getPapelToalha() {
        return papelToalha;
    }

    public void setPapelToalha(Double papelToalha) {
        this.papelToalha = papelToalha;
    }
}
