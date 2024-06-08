/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafos;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author humberto, douglas e vinicius
 */
public interface Grafo {
    
    /**
     * Adiciona uma nova aresta no grafo. O valor padrão do peso é 1.
     * @param origem
     * @param destino
     * @throws java.lang.Exception Uma exceção é lançada quando não é
     * possível adicionar aresta entre origem e destino, de acordo com limitações
     * da representação computacional do Grafo.
     */
    public void adicionarAresta(Vertice origem, Vertice destino) throws Exception;
    
    /**
     * Adiciona uma nova aresta no grafo com peso definido
     * @param origem
     * @param destino
     * @param peso
     * @throws java.lang.Exception Uma exceção é lançada quando não é
     * possível adicionar aresta entre origem e destino, de acordo com limitações
     * da representação computacional do Grafo.
     */
    public void adicionarAresta(Vertice origem, Vertice destino, double peso) throws Exception;
    
    public ArrayList<Aresta> carregarArestas() throws Exception;
    
    /**
     * Verifica a existência de aresta entre o par origem/destino.
     * @param origem
     * @param destino
     * @return True, se existe aresta entre origem e destino, False, caso contrário.
     * @throws java.lang.Exception Quando não existe origem e/ou destino.
     */
    public boolean existeAresta(Vertice origem, Vertice destino);
    
    /**
     * Indica o grau de um vértice específico.
     * @param vertice
     * @return o grau do vértice
     * @throws java.lang.Exception Quando o vértice não existe.
     */
    public int grauDoVertice(Vertice vertice) throws Exception;
    
    /**
     * Indica a cardinalidade do conjunto V.
     * @return número de vértices do grafo.
     */
    public int numeroDeVertices();
    
    /**
     * Indica a cardinalidade do conjunto A.
     * @return número de arestas do grafo.
     */    
    public int numeroDeArestas();
    
    /**
     * Indica os vértices adjacentes ao vertice indicado.
     * @param vertice
     * @return Uma coleção de vértices adjacentes ao vértice indicado.
     * @throws java.lang.Exception Uma exceção é lançada quando o vértice indicado
     * não existe.
     */
    public ArrayList<Vertice> adjacentesDe(Vertice vertice) throws Exception;
    
    /**
     * Seta o peso da aresta entre os vértices origem e destino.
     * @param origem
     * @param destino
     * @param peso
     * @throws java.lang.Exception Uma exceção é lançada quando o(s) vértice(s)
     * origem e/ou destino não existe(m).
     */
    public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception;
    
    /**
     * Retorna a aresta correspondente a dois vertices
     * @param origem
     * @param destino
     * @throws java.lang.Exception Uma exceção é lançada quando o(s) vértice(s)
     * origem e/ou destino não existe(m).
     */
    public Aresta lerAresta(Vertice origem, Vertice destino) throws Exception;
            
    /**
     * Retorna uma coleção com as arestas existentes entre origem e destino.
     * @param origem
     * @param destino
     * @return Uma coleção com as arestas existentes entre origem e destino.
     * @throws java.lang.Exception Se não existe origem e/ou destino.
     */
    public ArrayList<Aresta> arestasEntre(Vertice origem, Vertice destino) throws Exception;
    
    /**
     * Retorna o conjunto de vértices do grafo.
     * @return vértices do grafo.
     */
    public ArrayList<Vertice> vertices();
    
    public void mostrarGrafo();
}
