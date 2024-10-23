package feastplannerecalc.util;

import feastplannerecalc.model.Bebida;
import feastplannerecalc.model.BebidaQuantidadePadrao;
import feastplannerecalc.model.BebidaTipo;
import feastplannerecalc.model.Comida;
import feastplannerecalc.model.ComidaCategoriaCarne;
import feastplannerecalc.model.ComidaCategoriaSalgado;
import feastplannerecalc.model.ComidaQuantidadePadrao;
import feastplannerecalc.model.ComidaTipo;
import feastplannerecalc.model.Pessoa;
import feastplannerecalc.model.PessoaCategoria;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.List;

public class DataInitializer {

    // Método para inicializar todos os dados
    public static void initializeData(Session session) {
    	initializePessoas(session);
        initializePessoasCategoria(session);
        initializeTiposBebida(session);
        initializeBebidas(session);
        initializeBebidasQuantidadePadrao(session);
        initializeComidasCategoriaCarne(session);
        initializeComidasCategoriaSalgado(session);
        initializeComidasQuantidadePadrao(session);
        initializeTiposComida(session);
        initializeComidas(session);
        
        // Adicionar chamadas para inicializar as outras tabelas aqui
    }

    // Método para inicializar tipos de bebida
    private static void initializeTiposBebida(Session session) {
        List<String> tiposDeBebida = Arrays.asList("Alcoólico", "Não Alcoólico");

        // Verifica se já existem registros
        if (session.createQuery("select count(t) from BebidaTipo t", Long.class).uniqueResult() == 0) {
            Transaction tx = session.beginTransaction();
            try {
                for (String tipo : tiposDeBebida) {
                    BebidaTipo bebidaTipo = new BebidaTipo();
                    bebidaTipo.setTipo(tipo);
                    session.persist(bebidaTipo);
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    // Método para inicializar bebidas
    private static void initializeBebidas(Session session) {
        List<Object[]> bebidas = Arrays.asList(
            new Object[]{"Cerveja", "Alcoólico"},
            new Object[]{"Vinho", "Alcoólico"},
            new Object[]{"Vodka", "Alcoólico"},
            new Object[]{"Drink", "Alcoólico"},
            new Object[]{"Cerveja Sem Álcool", "Não Alcoólico"},
            new Object[]{"Água", "Não Alcoólico"},
            new Object[]{"Suco", "Não Alcoólico"},
            new Object[]{"Refrigerante", "Não Alcoólico"}
        );

        if (session.createQuery("select count(b) from Bebida b", Long.class).uniqueResult() == 0) {
            Transaction tx = session.beginTransaction();
            try {
                for (Object[] bebidaData : bebidas) {
                    String nomeBebida = (String) bebidaData[0];
                    String tipoBebida = (String) bebidaData[1];

                    BebidaTipo bebidaTipo = session.createQuery("from BebidaTipo where tipo = :tipo", BebidaTipo.class)
                        .setParameter("tipo", tipoBebida).uniqueResult();

                    if (bebidaTipo != null) {
                        Bebida bebida = new Bebida();
                        bebida.setBebida(nomeBebida);
                        bebida.setTipo(bebidaTipo); // Associando o tipo corretamente
                        session.persist(bebida);
                    }
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }
    
 // Método para inicializar quantidades de bebidas padrão
    private static void initializeBebidasQuantidadePadrao(Session session) {
        List<Object[]> bebidasQuantidades = Arrays.asList(
            new Object[]{"Cerveja", 1500},
            new Object[]{"Vinho", 50},
            new Object[]{"Vodka", 50},
            new Object[]{"Drink", 100},
            new Object[]{"Cerveja Sem Álcool", 500},
            new Object[]{"Água", 1000},
            new Object[]{"Suco", 500},
            new Object[]{"Refrigerante", 1000}
        );

        // Verifica se já existem registros
        if (session.createQuery("select count(b) from BebidaQuantidadePadrao b", Long.class).uniqueResult() == 0) {
            Transaction tx = session.beginTransaction();
            try {
                for (Object[] bebidaQuantidadeData : bebidasQuantidades) {
                    String nomeBebida = (String) bebidaQuantidadeData[0];
                    int quantidadePadrao = (int) bebidaQuantidadeData[1];

                    // Busca o objeto Bebida correspondente pelo nome
                    Bebida bebida = session.createQuery("from Bebida where bebida = :nome", Bebida.class)
                            .setParameter("nome", nomeBebida)
                            .uniqueResult();

                    if (bebida != null) {
                        BebidaQuantidadePadrao bebidaQuantidadePadrao = new BebidaQuantidadePadrao();
                        bebidaQuantidadePadrao.setBebida(bebida); // Relaciona com a bebida
                        bebidaQuantidadePadrao.setQuantidade(quantidadePadrao); // Define a quantidade padrão
                        session.persist(bebidaQuantidadePadrao);
                    }
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    

    // Método para inicializar Comidas
    private static void initializeComidas(Session session) {
        List<Object[]> comidasData = Arrays.asList(
            new Object[]{"Pizza", 2, 0, 1, true, 1.0},
            new Object[]{"Esfirra", 2, 0, 1, true, 1.0},
            new Object[]{"Hamburger", 2, 0, 1, false, 1.0},
            new Object[]{"Empadinha", 2, 0, 1, true, 1.0},
            new Object[]{"Coxinha", 2, 0, 2, false, 1.0},
            new Object[]{"Kibe", 2, 0, 2, false, 1.0},
            new Object[]{"Pastel", 2, 0, 2, true, 1.0},
            new Object[]{"Bolinha de Queijo", 2, 0, 2, true, 1.0},
            new Object[]{"Risóles", 2, 0, 2, false, 1.0},
            new Object[]{"Cigarette", 2, 0, 2, true, 1.0},
            new Object[]{"Croquete", 2, 0, 2, false, 1.0},
            new Object[]{"Costela Bovina", 1, 1, 0, false, 0.5},
            new Object[]{"Prime Rib", 1, 1, 0, false, 0.6},
            new Object[]{"Chuleta Paulista", 1, 1, 0, false, 0.6},
            new Object[]{"Costela Suína", 1, 2, 0, false, 0.4},
            new Object[]{"Bisteca", 1, 2, 0, false, 0.7},
            new Object[]{"Pernil com Osso", 1, 2, 0, false, 0.7},
            new Object[]{"Asinha com Coxinha", 1, 3, 0, false, 0.7},
            new Object[]{"Tulipa da Asa", 1, 3, 0, false, 0.6},
            new Object[]{"Coxa com Sobrecoxa", 1, 3, 0, false, 0.8},
            new Object[]{"Picanha", 1, 4, 0, false, 1.0},
            new Object[]{"Coxão Mole", 1, 4, 0, false, 1.0},
            new Object[]{"Alcatra", 1, 4, 0, false, 1.0},
            new Object[]{"Fraldinha", 1, 4, 0, false, 1.0},
            new Object[]{"Contra Filé", 1, 4, 0, false, 1.0},
            new Object[]{"File Mignon", 1, 5, 0, false, 1.0},
            new Object[]{"Picanha Suina", 1, 5, 0, false, 1.0},
            new Object[]{"Lombo", 1, 5, 0, false, 1.0},
            new Object[]{"Linguiça", 1, 5, 0, false, 1.0},
            new Object[]{"Paleta", 1, 5, 0, false, 1.0},
            new Object[]{"Arroz", 1, 6, 0, false, 1.0},
            new Object[]{"Farofa", 1, 6, 0, false, 1.0},
            new Object[]{"Vinagrete", 1, 6, 0, false, 1.0},
            new Object[]{"Coração de Frango", 1, 6, 0, false, 1.0},
            new Object[]{"Pão de Alho", 1, 6, 0, false, 1.0},
            new Object[]{"Pão Francês", 1, 6, 0, false, 1.0},
            new Object[]{"Queijo Coalho", 1, 6, 0, false, 1.0},
            new Object[]{"Carvão", 1, 6, 0, false, 1.5},
            new Object[]{"Copo", 3, 0, 0, false, 5.0},
            new Object[]{"Prato", 3, 0, 0, false, 5.0},
            new Object[]{"Papel Toalha", 3, 0, 0, false, 10.0}
        );

     // Verifica se já existem registros
        if (session.createQuery("select count(c) from Comida c", Long.class).uniqueResult() == 0) {
            Transaction tx = session.beginTransaction();
            try {
                for (Object[] comidaData : comidasData) {
                    // Atribuímos os valores da comidaData às variáveis
                    String nomeComida = (String) comidaData[0];
                    int id_tipo_comida = (int) comidaData[1];
                    int id_categoria_carne = (int) comidaData[2];
                    int id_categoria_salgado = (int) comidaData[3];
                    boolean opcaoSemCarne = (boolean) comidaData[4];
                    double aproveitamento = (double) comidaData[5];

                    // Criar um novo objeto Comida e setar todos os atributos
                    Comida comida = new Comida();
                    comida.setNome(nomeComida);

                    // Busca e define os relacionamentos com os tipos e categorias
                    ComidaTipo tipoComida = session.find(ComidaTipo.class, id_tipo_comida);
                    comida.setTipoComida(tipoComida);

                    ComidaCategoriaCarne categoriaCarne = session.find(ComidaCategoriaCarne.class, id_categoria_carne);
                    comida.setCategoriaCarne(categoriaCarne);

                    ComidaCategoriaSalgado categoriaSalgado = session.find(ComidaCategoriaSalgado.class, id_categoria_salgado);
                    comida.setCategoriaSalgado(categoriaSalgado);

                    // Definir os outros atributos
                    comida.setOpcaoSemCarne(opcaoSemCarne);
                    comida.setAproveitamento(aproveitamento);

                    // Persiste a comida no banco de dados
                    session.persist(comida);
                }              
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }

   
 // Método para inicializar ComidasCategoriaCarne
    private static void initializeComidasCategoriaCarne(Session session) {
        // Lista de categorias de carne a serem inseridas
        List<String> categoriasCarne = Arrays.asList(
            "Bovino com Osso",
            "Suíno com Osso",
            "Frango com Osso",
            "Bovino Sem Osso",
            "Suíno Sem Osso",
            "Agregados"
        );

        // Verifica se já existem registros
        if (session.createQuery("select count(c) from ComidaCategoriaCarne c", Long.class).uniqueResult() == 0) {
            Transaction tx = session.beginTransaction();
            try {
                for (String categoria : categoriasCarne) {
                    // Verifica se a categoria de carne já existe no banco
                    ComidaCategoriaCarne existente = session.createQuery("from ComidaCategoriaCarne where categoria = :categoria", ComidaCategoriaCarne.class)
                        .setParameter("categoria", categoria)
                        .uniqueResult();

                    // Se não existir, cria um novo registro
                    if (existente == null) {
                        ComidaCategoriaCarne novaCategoria = new ComidaCategoriaCarne();
                        novaCategoria.setCategoria(categoria);
                        session.persist(novaCategoria);
                    }
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    
	 // Método para inicializar ComidasCategoriaSalgado
	    private static void initializeComidasCategoriaSalgado(Session session) {
	        // Lista de categorias de salgados a serem inseridas
	        List<String> categoriasSalgado = Arrays.asList("Assados", "Fritos");
	
	        // Verifica se já existem registros
	        if (session.createQuery("select count(c) from ComidaCategoriaSalgado c", Long.class).uniqueResult() == 0) {
	            Transaction tx = session.beginTransaction();
	            try {
	                for (String categoria : categoriasSalgado) {
	                    // Verifica se a categoria de salgado já existe no banco
	                    ComidaCategoriaSalgado existente = session.createQuery("from ComidaCategoriaSalgado where categoria = :categoria", ComidaCategoriaSalgado.class)
	                        .setParameter("categoria", categoria)
	                        .uniqueResult();
	
	                    // Se não existir, cria um novo registro
	                    if (existente == null) {
	                        ComidaCategoriaSalgado novaCategoria = new ComidaCategoriaSalgado();
	                        novaCategoria.setCategoria(categoria);
	                        session.persist(novaCategoria);
	                    }
	                }
	                tx.commit();
	            } catch (Exception e) {
	                if (tx != null) {
	                    tx.rollback();
	                }
	                e.printStackTrace();
	            }
	        }
	    }

	 // Método para inicializar quantidade de comida padrão
	    private static void initializeComidasQuantidadePadrao(Session session) {
	        // Lista de quantidades padrão a serem inseridas
	        List<Object[]> quantidadesPadrao = Arrays.asList(
	            new Object[]{1, 500, 15},
	            new Object[]{2, 400, 12},
	            new Object[]{3, 300, 10},
	            new Object[]{4, 200, 6}
	        );

	        // Verifica se já existem registros
	        if (session.createQuery("select count(q) from ComidaQuantidadePadrao q", Long.class).uniqueResult() == 0) {
	            Transaction tx = session.beginTransaction();
	            try {
	                for (Object[] dados : quantidadesPadrao) {
	                    int idPessoasCategoria = (int) dados[0];
	                    int quantidadeCarne = (int) dados[1];
	                    int quantidadeSalgado = (int) dados[2];

	                    // Busca a entidade correspondente ao idPessoasCategoria
	                    PessoaCategoria pessoasCategoria = session.find(PessoaCategoria.class, idPessoasCategoria);
	                    
	                    // Se a categoria de pessoas existir
	                    if (pessoasCategoria != null) {
	                        // Verifica se a quantidade padrão já existe
	                        ComidaQuantidadePadrao existente = session.createQuery("from ComidaQuantidadePadrao where pessoasCategoria.id = :idPessoasCategoria", ComidaQuantidadePadrao.class)
	                            .setParameter("idPessoasCategoria", idPessoasCategoria)
	                            .uniqueResult();

	                        // Se não existir, cria um novo registro
	                        if (existente == null) {
	                            ComidaQuantidadePadrao novaQuantidade = new ComidaQuantidadePadrao();
	                            novaQuantidade.setPessoasCategoria(pessoasCategoria); // Passa o objeto correto
	                            novaQuantidade.setQuantidadeCarne(quantidadeCarne);
	                            novaQuantidade.setQuantidadeSalgado(quantidadeSalgado);
	                            session.persist(novaQuantidade);
	                        }
	                    }
	                }
	                tx.commit();
	            } catch (Exception e) {
	                if (tx != null) {
	                    tx.rollback();
	                }
	                e.printStackTrace();
	            }
	        }
	    }
    
 // Método para inicializar os tipos de Comidas
    private static void initializeTiposComida(Session session) {
        // Lista de tipos de comida a serem inseridos
        List<String> tiposComida = Arrays.asList("Churrasco", "Salgado", "Obrigatório");

        // Verifica se já existem registros
        if (session.createQuery("select count(t) from ComidaTipo t", Long.class).uniqueResult() == 0) {
            Transaction tx = session.beginTransaction();
            try {
                for (String tipo : tiposComida) {
                    // Verifica se o tipo de comida já existe no banco
                    ComidaTipo existente = session.createQuery("from ComidaTipo where tipo = :tipo", ComidaTipo.class)
                        .setParameter("tipo", tipo)
                        .uniqueResult();

                    // Se não existir, cria um novo registro
                    if (existente == null) {
                        ComidaTipo novoTipo = new ComidaTipo();
                        novoTipo.setTipo(tipo);
                        session.persist(novoTipo);
                    }
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    
 // Método para inicializar Pessoas
    private static void initializePessoas(Session session) {
        // Lista de tipos de pessoas a serem inseridos
        List<String> tiposPessoas = Arrays.asList("Come Carne", "Não Come Carne");

        // Verifica se já existem registros
        if (session.createQuery("select count(p) from Pessoa p", Long.class).uniqueResult() == 0) {
            Transaction tx = session.beginTransaction();
            try {
                for (String tipo : tiposPessoas) {
                    // Verifica se o tipo de pessoa já existe
                    Pessoa existente = session.createQuery("from Pessoa where tipo = :tipo", Pessoa.class)
                            .setParameter("tipo", tipo)
                            .uniqueResult();

                    // Se não existir, cria um novo registro
                    if (existente == null) {
                        Pessoa novaPessoa = new Pessoa();
                        novaPessoa.setTipo(tipo);
                        session.persist(novaPessoa);
                    }
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    
 // Método para inicializar PessoasCategoria
    private static void initializePessoasCategoria(Session session) {
        // Lista de categorias de pessoas a serem inseridas
        List<String> categoriasPessoas = Arrays.asList("Comilão", "Homem", "Mulher", "Criança");

        // Verifica se já existem registros
        if (session.createQuery("select count(pc) from PessoaCategoria pc", Long.class).uniqueResult() == 0) {
            Transaction tx = session.beginTransaction();
            try {
                for (String categoria : categoriasPessoas) {
                    // Verifica se a categoria já existe
                    PessoaCategoria existente = session.createQuery("from PessoaCategoria where categoria = :categoria", PessoaCategoria.class)
                            .setParameter("categoria", categoria)
                            .uniqueResult();

                    // Se não existir, cria um novo registro
                    if (existente == null) {
                        PessoaCategoria novaCategoria = new PessoaCategoria();
                        novaCategoria.setCategoria(categoria);
                        session.persist(novaCategoria);
                    }
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
        }
    }

}
