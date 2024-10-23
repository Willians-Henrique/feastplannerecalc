package feastplannerecalc.models;

import java.util.List;

public class SimulacaoSalgado {

    private int homens;
    private int mulheres;
    private int comiloes;
    private int criancas;
    private int vegetarianos;
    
    private List<String> salgadosAssados;
    private List<String> salgadosFritos;
    private List<String> agregados;

    // Construtores, getters e setters
    public SimulacaoSalgado() {}

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

    public List<String> getSalgadosAssados() {
        return salgadosAssados;
    }

    public void setSalgadosAssados(List<String> salgadosAssados) {
        this.salgadosAssados = salgadosAssados;
    }

    public List<String> getSalgadosFritos() {
        return salgadosFritos;
    }

    public void setSalgadosFritos(List<String> salgadosFritos) {
        this.salgadosFritos = salgadosFritos;
    }
    
    public List<String> getAgregados() {
        return agregados;
    }

    public void setAgregados(List<String> agregados) {
        this.agregados = agregados;
    }
    
    
}
