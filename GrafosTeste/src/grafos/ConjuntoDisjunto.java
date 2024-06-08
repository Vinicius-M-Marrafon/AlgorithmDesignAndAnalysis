/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos;

/**
 *
 * @author vinic
 */
public class ConjuntoDisjunto {
    private int[] parentes;
    private int[] grau;
    private int V;
    
    public ConjuntoDisjunto(int V) {
        this.V = V;
        parentes = new int[V + 1];
        grau = new int[V + 1];
 
        for (int i = 0; i <= V; i++)
        {
            grau[i] = 0; // Todos possui grau zero inicialmente (só o próprio vertice)
            parentes[i] = i; // Todos são parentes apenas de si mesmo
        }
    }
    
    public int acharParentesco(int destino)
    {
        if (destino != parentes[destino])
            parentes[destino] = acharParentesco(parentes[destino]);
        return parentes[destino];
    }
    
    public void juntarConjuntos(int X, int Y)
    {
        X = acharParentesco(X);
        Y = acharParentesco(Y);
        
        if (grau[X] > grau[Y]) {
            parentes[Y] = X;
        }
        else {
            parentes[X] = Y;
        }
        
        if (grau[X] == grau[Y])
            grau[Y]++;
    }
 
}
