package feastplannerecalc.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import feastplannerecalc.database.HibernateUtil;
import jakarta.persistence.*;


/**
 * Classe que representa as comidas disponíveis para a simulação, podendo ser categorizadas 
 * em tipos de comidas, carnes ou salgados. Também inclui informações sobre o aproveitamento
 * da comida e se há opção sem carne.
 * 
 * A tabela mapeada é "comidas".
 */
@Entity
@Table(name = "comidas")
public class Comida {

    /**
     * Identificador único da comida.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da comida.
     * Este campo é obrigatório.
     */
    @Column(name = "nome", nullable = false)
    private String nome;

    /**
     * Tipo de comida (ex: churrasco, salgado).
     * Representa a chave estrangeira para a tabela "comidas_tipos".
     */
    @ManyToOne
    @JoinColumn(name = "id_tipo_comida")
    private ComidaTipo tipoComida;

    /**
     * Categoria da carne (ex: bovina, suína).
     * Representa a chave estrangeira para a tabela "comidas_categoria_carne".
     */
    @ManyToOne
    @JoinColumn(name = "id_categoria_carne")
    private ComidaCategoriaCarne categoriaCarne;

    /**
     * Categoria do salgado (ex: assado, frito).
     * Representa a chave estrangeira para a tabela "comidas_categoria_salgado".
     */
    @ManyToOne
    @JoinColumn(name = "id_categoria_salgado")
    private ComidaCategoriaSalgado categoriaSalgado;

    /**
     * Informa se há uma opção sem carne disponível para essa comida.
     */
    @Column(name = "opcao_sem_carne")
    private Boolean opcaoSemCarne;

    /**
     * Percentual de aproveitamento da comida, ou seja, a quantidade que pode 
     * ser efetivamente consumida após o preparo (ex: rendimento após o cozimento).
     */
    @Column(name = "aproveitamento")
    private Double aproveitamento;

    // Getters e Setters

    /**
     * Obtém o identificador único da comida.
     * 
     * @return id da comida.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único da comida.
     * 
     * @param id Novo identificador da comida.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome da comida.
     * 
     * @return nome da comida.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da comida.
     * 
     * @param nome Novo nome da comida.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o tipo da comida (ex: churrasco, salgado).
     * 
     * @return tipo de comida.
     */
    public ComidaTipo getTipoComida() {
        return tipoComida;
    }

    /**
     * Define o tipo da comida.
     * 
     * @param tipoComida Novo tipo da comida.
     */
    public void setTipoComida(ComidaTipo tipoComida) {
        this.tipoComida = tipoComida;
    }

    /**
     * Obtém a categoria da carne (ex: bovina, suína).
     * 
     * @return categoria da carne.
     */
    public ComidaCategoriaCarne getCategoriaCarne() {
        return categoriaCarne;
    }

    /**
     * Define a categoria da carne.
     * 
     * @param categoriaCarne Nova categoria da carne.
     */
    public void setCategoriaCarne(ComidaCategoriaCarne categoriaCarne) {
        this.categoriaCarne = categoriaCarne;
    }

    /**
     * Obtém a categoria do salgado (ex: assado, frito).
     * 
     * @return categoria do salgado.
     */
    public ComidaCategoriaSalgado getCategoriaSalgado() {
        return categoriaSalgado;
    }

    /**
     * Define a categoria do salgado.
     * 
     * @param categoriaSalgado Nova categoria do salgado.
     */
    public void setCategoriaSalgado(ComidaCategoriaSalgado categoriaSalgado) {
        this.categoriaSalgado = categoriaSalgado;
    }

    /**
     * Obtém a informação se há uma opção sem carne disponível.
     * 
     * @return true se houver opção sem carne, false caso contrário.
     */
    public Boolean getOpcaoSemCarne() {
        return opcaoSemCarne;
    }

    /**
     * Define se há uma opção sem carne para essa comida.
     * 
     * @param opcaoSemCarne Novo valor indicando se há ou não opção sem carne.
     */
    public void setOpcaoSemCarne(Boolean opcaoSemCarne) {
        this.opcaoSemCarne = opcaoSemCarne;
    }

    /**
     * Obtém o percentual de aproveitamento da comida após o preparo.
     * 
     * @return aproveitamento da comida.
     */
    public Double getAproveitamento() {
        return aproveitamento;
    }

    /**
     * Define o percentual de aproveitamento da comida após o preparo.
     * 
     * @param aproveitamento Novo valor do aproveitamento da comida.
     */
    public void setAproveitamento(Double aproveitamento) {
        this.aproveitamento = aproveitamento;
    }
    
    public static List<Comida> carregarTodasComidas() {
        List<Comida> listaComidas = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            listaComidas = session.createQuery("FROM Comida", Comida.class).list();
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return listaComidas;
    }

    @Override
    public String toString() {
        return String.format("Comida{id=%d, nome='%s', tipo=%s, categoriaCarne=%s, categoriaSalgado=%s, opcaoSemCarne=%s, aproveitamento=%.2f}",
                id, nome, tipoComida, categoriaCarne, categoriaSalgado, opcaoSemCarne, (aproveitamento != null ? aproveitamento : 1.0));
    }

    public double calcularQuantidadeAproveitada(double quantidadeBase) {
        return quantidadeBase * (aproveitamento != null ? aproveitamento : 1.0);
    }
}