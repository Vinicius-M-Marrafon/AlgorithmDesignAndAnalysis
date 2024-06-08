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
public class MatrixInc implements Grafo {
    int V, A, insercaoA; // Numero de Arestas
    // private Aresta grafo
    // Usar vetor de arestas (Mais fácil)
    private Aresta grafo[];
    
    public MatrixInc (int V, int A) {
        this.V = V;
        this.A = A;
        this.insercaoA = 0;
        
        // grafo = new ArrayList[this.V][this.V];
        // grafo = new Aresta[this.V][this.A];
        grafo = new Aresta[this.A];
    }
    
    @Override public void adicionarAresta(Vertice origem, Vertice destino) throws Exception
    {
        int a = origem.id();
        int b = destino.id();
        
        if (a < 0 || b < 0 || a >= this.V || b >= this.V) return;
        
        // grafo[a][b].add(new Aresta(origem, destino));
        grafo[insercaoA++] = new Aresta(origem, destino);
    }
    
    @Override public void adicionarAresta(Vertice origem, Vertice destino, double peso) throws Exception
    {
        int a = origem.id();
        int b = destino.id();
        
        if (a < 0 || b < 0 || a >= this.V || b >= this.V) return;
        
        //grafo[a][b].add(new Aresta(origem, destino, peso));
        grafo[insercaoA++] = new Aresta(origem, destino, peso);
    }
    
    @Override public boolean existeAresta(Vertice origem, Vertice destino)
    {
        int a = origem.id();
        int b = destino.id();
        
        // Verificar colunas
        for (int j = 0; j < this.insercaoA; j++) {
            if (grafo[j].origem().id() == a && grafo[j].destino().id() == b)
                return true;
        }
        return false;
    }
    
    @Override public ArrayList<Aresta> carregarArestas() throws Exception
    {
        ArrayList<Aresta> arestas = new ArrayList<>();
        for (int i = 0; i < insercaoA; i++)
            arestas.add(grafo[i]);
        
        return arestas;
    }
    
    @Override public int grauDoVertice(Vertice vertice) throws Exception
    {
        int grau = 0;
        int v = vertice.id();
        
        // grau do vertice vertice
        for (int i = 0; i < this.insercaoA; i++)
            if (grafo[i].destino().id() == vertice.id())
                grau++;
        
        return grau;
    }
    
    @Override public int numeroDeVertices()
    {
        return this.V;
    }
     
    @Override public int numeroDeArestas()
    {
        return this.insercaoA;
    }
    
    @Override public ArrayList<Vertice> adjacentesDe(Vertice vertice) throws Exception
    {
        ArrayList<Vertice> adj = new ArrayList<>();
        int a = vertice.id();
        
        for (int i = 0; i < this.insercaoA; i++) {
            if (grafo[i].origem().id() == a) {
                adj.add(new Vertice(grafo[i].destino().id()));
            }
        }
        
        return adj;
    }
    
    @Override public Aresta lerAresta(Vertice origem, Vertice Destion) throws Exception {
        
        int a = origem.id();
        int b = Destion.id();
        
        for (int i = 0; i < this.insercaoA; i++) {
            if (grafo[i].origem().id() == a && grafo[i].destino().id() == b)
                return grafo[i];
        }
        
        return null;
    }
    
    @Override public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception
    {
        int a = origem.id();
        int b = destino.id();
        
        // Procurar e setar
        
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
    
    @Override public void mostrarGrafo() {
        for (int i = 0; i < insercaoA; i++) {
            System.out.println("("+grafo[i].origem().id() + ", "+ grafo[i].destino().id() + ")" + "["+grafo[i].fluxo()+"]");
        }
    }
}
