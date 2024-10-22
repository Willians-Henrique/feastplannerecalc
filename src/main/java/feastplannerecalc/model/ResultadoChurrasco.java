package feastplannerecalc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resultado_churrasco")
public class ResultadoChurrasco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_simulacao", nullable = false)
    private ResultadoSimulacao simulacao;

    // Carnes
    @Column(name = "costela_bovina", nullable = false)
    private Double costelaBovina;

    @Column(name = "prime_rib", nullable = false)
    private Double primeRib;

    @Column(name = "chuleta_paulista", nullable = false)
    private Double chuletaPaulista;

    @Column(name = "costela_suina", nullable = false)
    private Double costelaSuina;

    @Column(name = "bisteca", nullable = false)
    private Double bisteca;

    @Column(name = "pernil_osso", nullable = false)
    private Double pernilComOsso;

    @Column(name = "asinha_coxinha", nullable = false)
    private Double asinhaComCoxinha;

    @Column(name = "tulipa_asa", nullable = false)
    private Double tulipaDaAsa;

    @Column(name = "coxa_sobrecoxa", nullable = false)
    private Double coxaComSobrecoxa;

    @Column(name = "picanha", nullable = false)
    private Double picanha;

    @Column(name = "coxao_mole", nullable = false)
    private Double coxaoMole;

    @Column(name = "alcatra", nullable = false)
    private Double alcatra;

    @Column(name = "fraldinha", nullable = false)
    private Double fraldinha;

    @Column(name = "contra_file", nullable = false)
    private Double contraFile;

    @Column(name = "file_mignon", nullable = false)
    private Double fileMignon;

    @Column(name = "picanha_suina", nullable = false)
    private Double picanhaSuina;

    @Column(name = "lombo", nullable = false)
    private Double lombo;
    
    @Column(name = "linguica", nullable = false)
    private Double linguica;

    @Column(name = "paleta", nullable = false)
    private Double paleta;

    // Acompanhamentos
    @Column(name = "arroz", nullable = false)
    private Double arroz;

    @Column(name = "farofa", nullable = false)
    private Double farofa;

    @Column(name = "vinagrete", nullable = false)
    private Double vinagrete;

    @Column(name = "coracao_frango", nullable = false)
    private Double coracaoFrango;

    @Column(name = "pao_alho", nullable = false)
    private Double paoAlho;

    @Column(name = "pao_frances", nullable = false)
    private Double paoFrances;

    @Column(name = "queijo_coalho", nullable = false)
    private Double queijoCoalho;

    @Column(name = "carvao", nullable = false)
    private Double carvao;

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

    public Double getCostelaBovina() {
        return costelaBovina;
    }

    public void setCostelaBovina(Double costelaBovina) {
        this.costelaBovina = costelaBovina;
    }

    public Double getPrimeRib() {
        return primeRib;
    }

    public void setPrimeRib(Double primeRib) {
        this.primeRib = primeRib;
    }

    public Double getChuletaPaulista() {
        return chuletaPaulista;
    }

    public void setChuletaPaulista(Double chuletaPaulista) {
        this.chuletaPaulista = chuletaPaulista;
    }

    public Double getCostelaSuina() {
        return costelaSuina;
    }

    public void setCostelaSuina(Double costelaSuina) {
        this.costelaSuina = costelaSuina;
    }

    public Double getBisteca() {
        return bisteca;
    }

    public void setBisteca(Double bisteca) {
        this.bisteca = bisteca;
    }

    public Double getPernilComOsso() {
        return pernilComOsso;
    }

    public void setPernilComOsso(Double pernilComOsso) {
        this.pernilComOsso = pernilComOsso;
    }

    public Double getAsinhaComCoxinha() {
        return asinhaComCoxinha;
    }

    public void setAsinhaComCoxinha(Double asinhaComCoxinha) {
        this.asinhaComCoxinha = asinhaComCoxinha;
    }

    public Double getTulipaDaAsa() {
        return tulipaDaAsa;
    }

    public void setTulipaDaAsa(Double tulipaDaAsa) {
        this.tulipaDaAsa = tulipaDaAsa;
    }

    public Double getCoxaComSobrecoxa() {
        return coxaComSobrecoxa;
    }

    public void setCoxaComSobrecoxa(Double coxaComSobrecoxa) {
        this.coxaComSobrecoxa = coxaComSobrecoxa;
    }

    public Double getPicanha() {
        return picanha;
    }

    public void setPicanha(Double picanha) {
        this.picanha = picanha;
    }

    public Double getCoxaoMole() {
        return coxaoMole;
    }

    public void setCoxaoMole(Double coxaoMole) {
        this.coxaoMole = coxaoMole;
    }

    public Double getAlcatra() {
        return alcatra;
    }

    public void setAlcatra(Double alcatra) {
        this.alcatra = alcatra;
    }

    public Double getFraldinha() {
        return fraldinha;
    }

    public void setFraldinha(Double fraldinha) {
        this.fraldinha = fraldinha;
    }

    public Double getContraFile() {
        return contraFile;
    }

    public void setContraFile(Double contraFile) {
        this.contraFile = contraFile;
    }

    public Double getFileMignon() {
        return fileMignon;
    }

    public void setFileMignon(Double fileMignon) {
        this.fileMignon = fileMignon;
    }

    public Double getPicanhaSuina() {
        return picanhaSuina;
    }

    public void setPicanhaSuina(Double picanhaSuina) {
        this.picanhaSuina = picanhaSuina;
    }

    public Double getLombo() {
        return lombo;
    }

    public void setLombo(Double lombo) {
        this.lombo = lombo;
    }
    
    public Double getLinguica() {
        return lombo;
    }

    public void setLinguica(Double linguica) {
        this.linguica = linguica;
    }

    public Double getPaleta() {
        return paleta;
    }

    public void setPaleta(Double paleta) {
        this.paleta = paleta;
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

    public Double getCoracaoFrango() {
        return coracaoFrango;
    }

    public void setCoracaoFrango(Double coracaoFrango) {
        this.coracaoFrango = coracaoFrango;
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

    public Double getCarvao() {
        return carvao;
    }

    public void setCarvao(Double carvao) {
        this.carvao = carvao; 
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
