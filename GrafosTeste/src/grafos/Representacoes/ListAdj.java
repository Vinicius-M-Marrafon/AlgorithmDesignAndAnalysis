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
public class ListAdj implements Grafo {
    int V, A;
    ArrayList<Aresta> grafo[];
    
    public ListAdj (int V) {
        this.V = V;
        this.A = 0;
        this.grafo = new ArrayList[this.V];
        for (int i = 0; i < this.V; i++) {
            grafo[i] = new ArrayList<>();
        }
    }
    
    @Override
    public void adicionarAresta(Vertice origem, Vertice destino) throws Exception
    {
        int a = origem.id();
        int b = destino.id();
        
        if (a < 0 || b < 0 || a >= this.V || b >= this.V) return;
        
        grafo[a].add(new Aresta(origem, destino));
        A++;
    }
    
    @Override
    public void adicionarAresta(Vertice origem, Vertice destino, double peso) throws Exception
    {
        int a = origem.id();
        int b = destino.id();
        
        if (a < 0 || b < 0 || a >= this.V || b >= this.V) return;
        
        grafo[a].add(new Aresta(origem, destino, peso));
        A++;
    }
    
    // Fazer com Exceptions
    @Override
    public Aresta lerAresta(Vertice origem, Vertice Destion) throws Exception {
        int a = origem.id();
        int b = Destion.id();
        
        if (a < 0 || b < 0 || a >= this.V || b >= this.V) return null;
        
        for (int i = 0; i < grafo[a].size(); i++) {
            if (grafo[a].get(i).destino().id() == b) return grafo[a].get(i);
        }
        // Id nao necessariamente simboliza a aresta
        return null;
    }
    
    @Override
    public boolean existeAresta(Vertice origem, Vertice destino)
    {
        int a = origem.id();
        int b = destino.id();
        
        if (a < 0 || b < 0 || a >= this.V || b >= this.V) return false;
        return grafo[a].get(b) != null;
    }
    
    @Override
    public int grauDoVertice(Vertice vertice) throws Exception
    {
        int grau = 0;
        int v = vertice.id();
        for (int i = 0; i < this.V; i++)
            if (grafo[i].get(v) != null) grau++;
        
        return grau;
    }
    
    @Override
    public int numeroDeVertices()
    {
        return this.V;
    }
     
    @Override
    public int numeroDeArestas()
    {
        return this.A;
    }
    
    @Override
    public ArrayList<Aresta> carregarArestas() throws Exception
    {
        ArrayList<Aresta> conjuntoAresta = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            grafo[i].forEach(adj -> {
                conjuntoAresta.add(adj);
            });
        }
        return conjuntoAresta;
    }
    
    @Override
    public ArrayList<Vertice> adjacentesDe(Vertice vertice) throws Exception
    {
        ArrayList<Vertice> adj = new ArrayList<>();
        grafo[vertice.id()].forEach(aresta -> {
            adj.add(aresta.destino());
        });
        
        return adj;
    }
    
    @Override
    public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception
    {
        int a = origem.id();
        int b = destino.id();
        
        grafo[a].get(b).setarPeso(peso);
    }
    
    @Override
    public ArrayList<Aresta> arestasEntre(Vertice origem, Vertice destino) throws Exception
    {
        return null;
    }
   
    @Override
    public ArrayList<Vertice> vertices()
    {
        ArrayList<Vertice> listaVertices = new ArrayList<>();
        
        for (int i = 0; i < this.V; i++)
            listaVertices.add(new Vertice(i));
        
        return listaVertices;
    }
    
    @Override
    public void mostrarGrafo() {
        for (int i = 0; i < this.V; i++) {
            System.out.printf("%d: ", i);
            grafo[i].forEach(aresta -> {
               System.out.printf("%d=(%.3f) ", aresta.destino().id(), aresta.peso());
            });
            System.out.println("");
        }
    }
}
