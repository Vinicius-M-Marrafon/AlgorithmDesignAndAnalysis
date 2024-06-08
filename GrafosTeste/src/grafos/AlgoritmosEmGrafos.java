/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

/**
 *
 * @author humberto e douglas
 */
public interface AlgoritmosEmGrafos {

    /**
     * Carrega grafo do arquivo texto.O formato será definido do site da disciplina
     * @param path
     * @param t
     * @return um objeto grafo com as informações representadas no arquivo.
     * @throws java.lang.Exception Caminho inválido ou árquivo fora do padrão.
     * 
     * if representacao == Matriz de adjencia, entao grafo = new CarregaMatrizAdj()
     */
    public Grafo carregarGrafo(String path, TipoDeRepresentacao t) throws Exception;

    /**
     * Realiza busca em largura no grafo 
     * @param v
     * @return as arestas da árvore resultante
     */
    public Collection<Aresta> buscaEmLargura (Vertice v);
    
    
    public double EdmondKart(Vertice s, Vertice t);
    
    public void fluxoMaximo(Vertice s, Vertice t);
    
    /**
     * Realiza a busca em profundidade no grafo
     * @param g Grafo
     * @return as arestas da floresta resultante
     */
    public Collection<Aresta> buscaEmProfundidade ();
    
    /**
     * Função que indica o menor caminho entre dois pontos. Nesta função, é
     * considerada a quantidade de arestas entre origem e destino. Não são
     * considerados os pesos das arestas.
     * @return um vetor com as arestas quem fazem o menor caminho entre a origem
     * e o destino.
     * @throws java.lang.Exception Caso não exista caminho, uma exception é lançada.
     */
    public ArrayList<Aresta> menorCaminho() throws Exception;

    /**
     * Verifica se existe ciclo no grafo.
     * @return True, se existe ciclo, False, em caso contrário.
     */
    public boolean existeCiclo();

    
    /**
     * Retorna a árvore geradora mínima utilizando o algoritmo Kruskall.
     * @return Retorna a árvore geradora mínima utilizando o algoritmo Kruscall.
     */
    public Collection<Aresta> agmUsandoKruskall();
    
    /**
     * Calcula o custo de uma árvore geradora.
     * @param arestas As arestas que compoem a árvore geradora.
     * @return O custo da árvore geradora.
     * @throws java.lang.Exception Se a árvore apresentada não é geradora do grafo.
     */
    public double custoDaArvoreGeradora(Collection<Aresta> arestas) throws Exception;
    
    /**
     * Testa se a árvore é geradora.
     * @param arestas
     * @return True, se a árvore é árvore geradora, False, caso contrário.
     */
    public boolean ehArvoreGeradora( Collection<Aresta> arestas );
    
    /**
     * Retorna (em ordem) as arestas que compoem o caminho mais curto 
     * entre um par de vértices. Esta função considera o pesa das arestas
     * para composição do caminho mais curto.
     * @return As arestas (em ordem) do caminho mais curto.
     */
    public Stack<Aresta> caminhoMaisCurto();
    
    /**
     * Dado um caminho, esta função calcula o custo do caminho.
     * @param arestas
     * @return o custo da caminho.
     * @throws java.lang.Exception Se a sequencia apresentada não é um caminho
     * entre origem e destino.
     */
    public double custoDoCaminho( Stack<Aresta> arestas) throws Exception;
    
    /**
     * Verifica se a sequencia de arestas é caminho entre oriem e destino.
     * @param arestas
     * @return True, se é caminho. False, caso contrário.
     */
    public boolean ehCaminho( ArrayList<Aresta> arestas);
    
    /**
     * Arestas de arvore.
     * @return As arestas de arvore do grafo g.
     */
    public Collection<Aresta> arestasDeArvore();
    
    /**
     * Arestas de retorno.
     * @return As arestas de retorno do grafo g.
     */
    public Collection<Aresta> arestasDeRetorno();
    
    /**
     * Arestas de avanço.
     * @return As arestas de avanço do grafo g.
     */
    public Collection<Aresta> arestasDeAvanco();
    
    /**
     * Arestas de cruzamento.
     * @return As arestas de cruzamento do grafo g.
     */
    public Collection<Aresta> arestasDeCruzamento();

}
