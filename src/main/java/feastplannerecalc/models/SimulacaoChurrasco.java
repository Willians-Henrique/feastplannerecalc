package feastplannerecalc.models;

import java.util.List;

public class SimulacaoChurrasco {

    private int homens;
    private int mulheres;
    private int comiloes;
    private int criancas;
    private int vegetarianos;
    
    private List<String> carnesComOsso;
    private List<String> carnesSemOsso;
    private List<String> agregados;

    // Construtores, getters e setters
    public SimulacaoChurrasco() {}

    public int getHomens() {
        return homens;
    }

    public void setHomens(int homens) {
        this.homens = homens;
    }

    public int getMulheres() {
        return mulheres;
    }

    public void setMulheres(int mulheres) {
        this.mulheres = mulheres;
    }

    public int getComiloes() {
        return comiloes;
    }

    public void setComiloes(int comiloes) {
        this.comiloes = comiloes;
    }

    public int getCriancas() {
        return criancas;
    }

    public void setCriancas(int criancas) {
        this.criancas = criancas;
    }

    public int getVegetarianos() {
        return vegetarianos;
    }

    public void setVegetarianos(int vegetarianos) {
        this.vegetarianos = vegetarianos;
    }

    public List<String> getCarnesComOsso() {
        return carnesComOsso;
    }

    public void setCarnesComOsso(List<String> carnesComOsso) {
        this.carnesComOsso = carnesComOsso;
    }

    public List<String> getCarnesSemOsso() {
        return carnesSemOsso;
    }

    public void setCarnesSemOsso(List<String> carnesSemOsso) {
        this.carnesSemOsso = carnesSemOsso;
    }
    
    public List<String> getAgregados() {
        return agregados;
    }

    public void setAgregados(List<String> agregados) {
        this.agregados = agregados;
    }
    
    
}
