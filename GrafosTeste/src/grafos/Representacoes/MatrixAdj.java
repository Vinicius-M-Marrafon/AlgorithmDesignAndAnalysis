/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.Representacoes;

import grafos.Aresta;
import grafos.Grafo;
import grafos.Vertice;
import java.util.ArrayList;

/**
 *
 * @author vinic
 */
public class MatrixAdj implements Grafo
{
    int V, A; // Numero de Arestas
    // ArrayList<Aresta grafo[][]; // Matriz de Arrays List para tratar Arestas paralelas
    Aresta grafo[][];
    
    
    public MatrixAdj (int V) {
        this.V = V;
        this.A = 0;
        
        // grafo = new ArrayList[this.V][this.V];
        grafo = new Aresta[this.V][this.V];
        
        for (int i = 0; i < this.V; i++)
            for (int j = 0; j < this.V; j++)
                grafo[i][j] = null;
    }
    
    @Override public void adicionarAresta(Vertice origem, Vertice destino) throws Exception
    {
        int a = origem.id();
        int b = destino.id();
        
        if (a < 0 || b < 0 || a >= this.V || b >= this.V) return;
        
        // grafo[a][b].add(new Aresta(origem, destino));
        grafo[a][b] = new Aresta(origem, destino);
        
        this.A++;
    }
    
    @Override public void adicionarAresta(Vertice origem, Vertice destino, double peso) throws Exception
    {
        int a = origem.id();
        int b = destino.id();
        
        if (a < 0 || b < 0 || a >= this.V || b >= this.V) return;
        //grafo[a][b].add(new Aresta(origem, destino, peso));
        grafo[a][b] = new Aresta(origem, destino, peso);
        
        this.A++;
    }
    
    @Override public boolean existeAresta(Vertice origem, Vertice destino)
    {
        int a = origem.id();
        int b = destino.id();
        
        // return !grafo[a][b].isEmpty();
        return grafo[a][b] != null;
    }
    
    @Override public int grauDoVertice(Vertice vertice) throws Exception
    {
        int grau = 0;
        int v = vertice.id();
        for (int i = 0; i < this.V; i++)
            // if (!grafo[i][v].isEmpty()) grau += grafo[i][v].size();
            if (grafo[i][v] != null) grau++;
        
        return grau;
    }
    
    @Override public int numeroDeVertices()
    {
        return this.V;
    }
     
    @Override public int numeroDeArestas()
    {
        return this.A;
    }
    
    public ArrayList<Aresta> carregarArestas() throws Exception
    {
        ArrayList<Aresta> conjuntoAresta = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (grafo[i][j] != null)
                    conjuntoAresta.add(grafo[i][j]);
            }
        }
        return conjuntoAresta;
    }
    
    @Override public ArrayList<Vertice> adjacentesDe(Vertice vertice) throws Exception
    {
        ArrayList<Vertice> adj = new ArrayList<>();
        int v = vertice.id();
        
        for (int i = 0; i < this.V; i++) {
            if (grafo[v][i] != null)
                adj.add(new Vertice(i));
        }
        
        return adj;
    }
    
    @Override public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception
    {
        int a = origem.id();
        int b = destino.id();
        
        grafo[a][b].setarPeso(peso);
    }
    
    @Override public Aresta lerAresta(Vertice origem, Vertice destino) throws Exception {
        int a = origem.id();
        int b = destino.id();
        
        return grafo[a][b];
    }
    
    @Override public ArrayList<Aresta> arestasEntre(Vertice origem, Vertice destino) throws Exception
    {
        // Precisa do DFS
        return null;
    }
   
    @Override public ArrayList<Vertice> vertices()
    {
        ArrayList<Vertice> listaVertices = new ArrayList<>();
        
        for (int i = 0; i < this.V; i++)
            listaVertices.add(new Vertice(i));
        
        return listaVertices;
    }
    
    public void mostrarGrafo() {
        for (int i = 0; i < V; i++) {
            System.out.printf("%d: ", i);
            for (int j = 0; j < V; j++) {
                if (grafo[i][j] != null)
                    System.out.printf("%d=(%.3f) ", grafo[i][j].destino().id(),
                                                     grafo[i][j].peso());
            }
            System.out.println("");
        }
    }
}
