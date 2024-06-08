/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafos;

/**
 *
 * @author humberto, douglas e vinicius mari marrafon (Comparable)
 */
public class Aresta implements Comparable<Aresta> {

    private Vertice origem;
    private Vertice destino;
    private double peso, fluxo;
    
    public Aresta( Vertice origem, Vertice destino ){
        this.origem = origem;
        this.destino = destino;
        this.peso = 1;
        this.fluxo = 0.0;
    }
    
    public Aresta( Vertice origem, Vertice destino, double peso ){
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
        this.fluxo = 0.0;
    }

    public Vertice origem() {
        return origem;
    }

    public void setarOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice destino() {
        return destino;
    }

    public void setarDestino(Vertice destino) {
        this.destino = destino;
    }

    public double peso() {
        return peso;
    }

    public void setarPeso(double peso) {
        this.peso = peso;
    }

    public double fluxo() {
        return fluxo;
    }

    public void setarFluxo(double fluxo) {
        this.fluxo = fluxo;
    }
    
    public double capacidade() {
        return this.peso - this.fluxo;
    }
    
    // Comparacao por peso - Ordenação em ordem crescente
    @Override
    public int compareTo(Aresta o) {
       return Double.compare(-o.peso(), -this.peso());
    }
    
}
