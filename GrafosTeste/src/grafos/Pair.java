/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package grafos;



/**
 *
 * @author vinic
 */
// Comparable deve ser implementado para qualquer utilizacao da classe em um
// algoritmo de ordenacao

public class Pair<U, V> implements Comparable<Pair> {
    U first;
    V second;
    
    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
    
    public void setFirst(U first) {
        this.first = first;
    }
    
    public void setSecond(V second) {
        this.second = second;
    }
    
    public U getFirst() {
        return this.first;
    }
    
    public V getSecond() {
        return this.second;
    }

    @Override public int compareTo(Pair a)
    {
        return Double.compare((Double)a.getSecond(), (Double)this.getSecond());
    }
}
