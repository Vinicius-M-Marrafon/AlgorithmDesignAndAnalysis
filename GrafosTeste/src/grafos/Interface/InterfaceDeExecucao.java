/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package grafos.Interface;

import grafos.*;
import grafos.Exceptions.FileUploadErrorException;
import grafos.Representacoes.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author vinic
 */
public class InterfaceDeExecucao extends javax.swing.JFrame implements AlgoritmosEmGrafos {
    
    /**
     * Global Variables
     */
    private final int BRANCO    =   10;
    private final int CINZA     =   20;
    private final int PRETO     =   30;
    
    private Chronometer cronometro;
    
    private String arquivoDoGrafo;
    private TipoDeRepresentacao representacao;
    private Grafo grafo;
    private int[] descoberto;
    private int[] coloracao;
    private int[] finalizado;
    private int[] antecedentes;
    private int tempo;
    private StringBuilder result;
    
    // Variáveis para análise e auxílio
    private Vertice origemVertice, destinoVertice;
    private ArrayList<Aresta> ArvoreGeradoraMin; // Armazena a arvore geradora mínima
    private ArrayList<Integer> verticesMinimos; // Armazena o conjunto de aresta que compoe o caminho minimo
    private ArrayList<Aresta> caminhoMinimo;
    private ArrayList<Aresta> arestasRetorno, arestasArvore, arestasAvanco, arestasCruzamento;
    private Set<Integer> arvoreDeProfundidadeTemp;
    
    /**
     * Getteres e Setteres das variáveis globais
     */
    
    public void iniciarEstruturas() throws FileUploadErrorException {
        try {
            descoberto = new int[grafo.numeroDeVertices()];
            finalizado = new int[grafo.numeroDeVertices()];
            antecedentes = new int[grafo.numeroDeVertices()];
            coloracao = new int[grafo.numeroDeVertices()];
            tempo = 0;
            result = new StringBuilder();
        }
        catch (Exception ex) {
            // System.exit(1); // Fecha a interface de configuração tambem
            throw new FileUploadErrorException("Arquivo selecionado não é suportado!");
        }
    }
    
    public void reiniciarEstruturas() {
        for (int i = 0; i < grafo.numeroDeVertices(); i++) {
            descoberto[i] = finalizado[i] = antecedentes[i] = -1;
            coloracao[i] = BRANCO;
        }
        tempo = 0;
        result = new StringBuilder();
    }
    /**
     * Creates new form InterfaceDeExecucao
     * @param arquivoDoGrafo = arquivo referente a estrutura do grafo
     * @param tipo = Tipo de Representacao do grafo
     */
    public InterfaceDeExecucao(String arquivoDoGrafo, TipoDeRepresentacao tipo) throws FileUploadErrorException {
        initComponents();
        this.arquivoDoGrafo = arquivoDoGrafo;
        this.representacao = tipo;
        this.ArvoreGeradoraMin = new ArrayList<>();
        this.caminhoMinimo = new ArrayList<>();
        this.verticesMinimos = new ArrayList<>();
        this.cronometro = new Chronometer();
        this.arestasRetorno = new ArrayList<>();
        this.arestasArvore = new ArrayList<>();
        this.arestasAvanco = new ArrayList<>();
        this.arestasCruzamento = new ArrayList<>();
        this.arvoreDeProfundidadeTemp = new HashSet<>();
        this.origemVertice = new Vertice(OrigemBusca.getSelectedIndex());
        this.destinoVertice = new Vertice(DestinoBusca.getSelectedIndex());
        
        if (representacao == TipoDeRepresentacao.MATRIZ_DE_ADJACENCIA)
            try {
                Representacao.setText("Matriz de Adjacência");
                grafo = carregarGrafo(arquivoDoGrafo, TipoDeRepresentacao.MATRIZ_DE_ADJACENCIA);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao corregar o arquivo", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        else if (representacao == TipoDeRepresentacao.MATRIZ_DE_INCIDENCIA)
            try {
                Representacao.setText("Matriz de Incidência");
                grafo = carregarGrafo(arquivoDoGrafo, TipoDeRepresentacao.MATRIZ_DE_INCIDENCIA);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Erro ao corregar o arquivo", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        else if (representacao == TipoDeRepresentacao.LISTA_DE_ADJACENCIA)
            try {
                Representacao.setText("Lista de Adjacência");
                grafo = carregarGrafo(arquivoDoGrafo, TipoDeRepresentacao.LISTA_DE_ADJACENCIA);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao corregar o arquivo", "Alert", JOptionPane.ERROR_MESSAGE);
            }

        iniciarEstruturas();
        // grafo.mostrarGrafo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        DFS = new javax.swing.JRadioButton();
        BFS = new javax.swing.JRadioButton();
        AGM = new javax.swing.JRadioButton();
        Dijkstra = new javax.swing.JRadioButton();
        Start = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Terminal = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        Representacao = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        OrigemBusca = new javax.swing.JComboBox<>();
        DestinoBusca = new javax.swing.JComboBox<>();
        FM = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(DFS);
        DFS.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        DFS.setText("Busca em Largura (DFS)");

        buttonGroup1.add(BFS);
        BFS.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        BFS.setText("Busca em Profundidade (BFS)");

        buttonGroup1.add(AGM);
        AGM.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        AGM.setText("Árvore Geradora Mínima (AGM)");

        buttonGroup1.add(Dijkstra);
        Dijkstra.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        Dijkstra.setText("Algoritmo de Dijkstra");

        Start.setBackground(new java.awt.Color(255, 255, 255));
        Start.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        Start.setText("Executar");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });

        Terminal.setBackground(new java.awt.Color(0, 0, 0));
        Terminal.setColumns(20);
        Terminal.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        Terminal.setForeground(new java.awt.Color(255, 255, 255));
        Terminal.setRows(5);
        Terminal.setName("Output"); // NOI18N
        jScrollPane1.setViewportView(Terminal);

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jLabel1.setText("Terminal");

        Representacao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Representacao.setText("Matriz De Incidência");

        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jLabel2.setText("Origem da busca:");

        jLabel3.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        jLabel3.setText("Destino da busca:");

        OrigemBusca.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        OrigemBusca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        OrigemBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrigemBuscaActionPerformed(evt);
            }
        });

        DestinoBusca.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        DestinoBusca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        DestinoBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DestinoBuscaActionPerformed(evt);
            }
        });

        buttonGroup1.add(FM);
        FM.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        FM.setText("Fluxo Máximo (Ford-Fulkerson)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Start)
                            .addComponent(AGM)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DFS)
                                    .addComponent(BFS))
                                .addGap(72, 72, 72)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(OrigemBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Dijkstra)
                                    .addComponent(FM))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DestinoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(167, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Representacao)
                .addGap(229, 229, 229))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Representacao)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(DFS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BFS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AGM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Dijkstra)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OrigemBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DestinoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FM))
                .addGap(6, 6, 6)
                .addComponent(Start)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
        Terminal.setText("");
        result=new StringBuilder();
        if (BFS.isSelected()) {
            // Pegar do usuário
            cronometro.start();
            buscaEmLargura(origemVertice);
            cronometro.stop();
            Terminal.append("\n\nTempo de Execução (BFS): "+cronometro.getTime()+" ms\n");
        } else if (DFS.isSelected()) {
            cronometro.start();
            buscaEmProfundidade();
            cronometro.stop();
            Terminal.append("\n\nTempo de Execução (DFS): "+cronometro.getTime()+" ms\n");
        } else if (AGM.isSelected()) {
            cronometro.start();
            agmUsandoKruskall();
            cronometro.stop();
            Terminal.append("\n\nTempo de Execução (AGM-Kruskall): "+cronometro.getTime()+" ms\n");
        } else if (Dijkstra.isSelected()) {
            try {
                
                cronometro.start();
                menorCaminho();
                cronometro.stop();
                Terminal.append("\n\nTempo de Execução (Dijkstra): "+cronometro.getTime()+" ms\n");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (FM.isSelected()) {
            cronometro.start();
            // EdmondKart(origemVertice, destinoVertice);
            fluxoMaximo(origemVertice, destinoVertice);
            cronometro.stop();
            Terminal.append("\n\nTempo de Execução (Ford-Fulkerson): "+cronometro.getTime()+" ms\n");
        }
    }//GEN-LAST:event_StartActionPerformed

    private void OrigemBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrigemBuscaActionPerformed
        origemVertice = new Vertice(OrigemBusca.getSelectedIndex());
    }//GEN-LAST:event_OrigemBuscaActionPerformed

    private void DestinoBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DestinoBuscaActionPerformed
        destinoVertice = new Vertice(DestinoBusca.getSelectedIndex());
    }//GEN-LAST:event_DestinoBuscaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton AGM;
    private javax.swing.JRadioButton BFS;
    private javax.swing.JRadioButton DFS;
    private javax.swing.JComboBox<String> DestinoBusca;
    private javax.swing.JRadioButton Dijkstra;
    private javax.swing.JRadioButton FM;
    private javax.swing.JComboBox<String> OrigemBusca;
    private javax.swing.JLabel Representacao;
    private javax.swing.JButton Start;
    private javax.swing.JTextArea Terminal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public Grafo carregarGrafo(String path, TipoDeRepresentacao t) throws Exception {
        Grafo g = null;
        
        File fptr = new File(arquivoDoGrafo);
        FileManager fileManager = new FileManager();
        ArrayList<String> content = fileManager.stringReaderWithoutHeader(arquivoDoGrafo);

        try {
            Scanner fileReader = new Scanner(fptr);
            int V = fileReader.nextInt();
            String[] opcaoVertices = new String[V];
               
            if (t == TipoDeRepresentacao.MATRIZ_DE_ADJACENCIA) {
                g = new MatrixAdj(V);
            } else if (t == TipoDeRepresentacao.MATRIZ_DE_INCIDENCIA) {
                g = new MatrixInc(V, V * V);
            } else if (t == TipoDeRepresentacao.LISTA_DE_ADJACENCIA) {
                g = new ListAdj(V);
            } else {
                return null;
            }
            
            for (int i = 0; i < V; i++) {
                try {
                    opcaoVertices[i] = Integer.toString(i);
                    String filter0[] = content.get(i).split(" ", 2); // Eliminar o vertice
                    Vertice origem = new Vertice(Integer.parseInt(filter0[0]));
                    String filter1 = filter0[1];

                    String filter2[] = filter1.split(";"); // Separar as arestas

                    for (String x : filter2) {
                        x = x.trim();
                        String filter3[] = x.split("-");
                        Vertice destino = new Vertice(Integer.parseInt(filter3[0]));
                        double peso = Double.parseDouble(filter3[1]);
                        g.adicionarAresta(origem, destino, peso);
                    }
                }
                catch (Exception ex){}
            }
            
            OrigemBusca.setModel(new javax.swing.DefaultComboBoxModel<>(opcaoVertices));
            DestinoBusca.setModel(new javax.swing.DefaultComboBoxModel<>(opcaoVertices));
        }
        catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        }
        
        return g;
    }

    @Override
    public Collection<Aresta> buscaEmLargura(Vertice inicio) {
        reiniciarEstruturas();
        Queue<Pair<Vertice, Integer>> q = new LinkedList<>();
        int steps[] = new int[grafo.numeroDeVertices()];
        
        for (int i = 0; i < grafo.numeroDeVertices(); i++) {
            steps[i] = Integer.MAX_VALUE;
        }
        
        steps[inicio.id()] = 0;
        
        q.add(new Pair<>(inicio, 0));
        coloracao[inicio.id()] = CINZA;
        
        while (!q.isEmpty())
        {
            Vertice u = q.peek().getFirst();
            int distAtual = q.peek().getSecond();
            
            descoberto[u.id()] = ++tempo;
            
            q.poll();
            
            try {
                grafo.adjacentesDe(u).forEach(v -> {
                    if (coloracao[v.id()] == BRANCO && v.id() != u.id()) {
                        coloracao[v.id()] = CINZA;
                        steps[v.id()] = distAtual+1;
                        q.add(new Pair<>(v, distAtual+1));
                        antecedentes[v.id()] = u.id();
                    }
                });
            }
            catch (Exception ex) {
            }
        }
        
        result.append("Distâncias[]: ");
        for (int i = 0; i < grafo.numeroDeVertices(); i++) {
            if (steps[i] == Integer.MAX_VALUE) {
                result.append("INF");
            }
            else result.append(steps[i]);
            result.append(", ");
        }
        
        result.append("\nPais[]: ");
        for (int i = 0; i < grafo.numeroDeVertices(); i++) {
            if (antecedentes[i] != -1)
                result.append(antecedentes[i]);
            else result.append("NULL");
            result.append(", ");
        }
        
        Terminal.setText(result.toString());
        
        return null;
    }

    @Override public double EdmondKart(Vertice s, Vertice t) {
        reiniciarEstruturas();
        Queue<Vertice> q = new LinkedList<>();
        q.add(s);
        
        Aresta[] caminho = new Aresta[grafo.numeroDeVertices()];
        caminho[s.id()] = new Aresta(s, s, 0);
        
        while (!q.isEmpty()) {
            Vertice atual = q.peek();
            q.poll();
            try {
                grafo.adjacentesDe(atual).forEach(v -> {
                    try { 
                        double cap = grafo.lerAresta(atual, v).capacidade();
                        if (coloracao[v.id()] == BRANCO && v.id() != atual.id() && cap > 0) {
                            coloracao[v.id()] = CINZA;
                            q.add(v);
                            caminho[v.id()] = new Aresta(atual, v, grafo.lerAresta(atual, v).peso());
                        }
                    }
                    catch(Exception ex) {}
                });
            }
            catch (Exception ex) {
            }
        }
        
        if (caminho[t.id()] == null) {
            return 0.00;
        }
        
        // Alterar fluxo
        double gargalo = Double.MAX_VALUE;
        
        Terminal.append("Path:\n");
        // Terminal.append("Caminho s -> t:\n\n");
        for (Aresta aresta = caminho[t.id()]; aresta.destino().id() != s.id(); aresta = caminho[aresta.origem().id()]) {
            Terminal.append("("+aresta.origem().id()+", "+aresta.destino().id()+")["+aresta.peso()+"]\n");
            gargalo = Math.min(gargalo, aresta.peso());
        }
        Terminal.append("End Path:\n");
        
        for (Aresta aresta = caminho[t.id()]; aresta.destino().id() != s.id(); aresta = caminho[aresta.origem().id()]) {
            try {
                double fluxoAresta = grafo.lerAresta(aresta.origem(), aresta.destino()).fluxo();
                grafo.lerAresta(aresta.origem(), aresta.destino()).setarFluxo(fluxoAresta + gargalo);
            }
            catch (Exception ex){}
        }
        
        // grafo.mostrarGrafo();
        return gargalo;
    }
    
    @Override public void fluxoMaximo(Vertice s, Vertice t) {
        if (s.id() == t.id()) {
            Terminal.append("Fluxo Maximo: "); 
        }
        else {
            // Reniciar fluxo
            try {
                ArrayList<Aresta> arestas = grafo.carregarArestas();
                for (Aresta aresta : arestas) {
                    aresta.setarFluxo(0);
                }
            }
            catch (Exception ex) {
            }

            double fluxo;
            double fluxoMaximo = 0.00;
            do {
                reiniciarEstruturas();
                fluxo = EdmondKart(s, t);
                fluxoMaximo += fluxo;
            }
            while (fluxo != 0.00);
            Terminal.append("Fluxo Maximo: "+fluxoMaximo);
            // Escrever resultados
        }
    }
    
    /*******************************************************************************************************************/
    @Override
    public Collection<Aresta> buscaEmProfundidade() {
        arestasArvore.clear();
        arestasRetorno.clear();
        arestasCruzamento.clear();
        arestasAvanco.clear();
        reiniciarEstruturas();
        
        buscarNoGrafo(origemVertice);
        arvoreDeProfundidadeTemp.clear();
        
        grafo.vertices().forEach(vertice -> {
            if (coloracao[vertice.id()] == BRANCO) {
                // System.out.println("Buscar "+vertice.id());
                buscarNoGrafo(vertice);
                arvoreDeProfundidadeTemp.clear();
            }
        });
        
        result.append("\nTempo de Descoberta: ");
        for (int i = 0; i < grafo.numeroDeVertices(); i++) {
            result.append(descoberto[i]);
            result.append(", ");
        }
        result.append("\nTempo de Finalizacao: ");
        for (int i = 0; i < grafo.numeroDeVertices(); i++) {
            result.append(finalizado[i]);
            result.append(", ");
        }
        
        result.append("\nArestas De Árvore: ");
        for (int i = 0; i < arestasArvore.size(); i++) {
            result.append("(");
            result.append(arestasArvore.get(i).origem().id());
            result.append("-");
            result.append(arestasArvore.get(i).destino().id());
            result.append("), ");
        }
        result.append("\nArestas De Retorno: ");
        for (int i = 0; i < arestasRetorno.size(); i++) {
            result.append("(");
            result.append(arestasRetorno.get(i).origem().id());
            result.append("-");
            result.append(arestasRetorno.get(i).destino().id());
            result.append("), ");
        }
        result.append("\nArestas De Cruzamento: ");
        for (int i = 0; i < arestasCruzamento.size(); i++) {
            result.append("(");
            result.append(arestasCruzamento.get(i).origem().id());
            result.append("-");
            result.append(arestasCruzamento.get(i).destino().id());
            result.append("), ");
        }
        result.append("\nArestas De Avanço: ");
        for (int i = 0; i < arestasAvanco.size(); i++) {
            result.append("(");
            result.append(arestasAvanco.get(i).origem().id());
            result.append("-");
            result.append(arestasAvanco.get(i).destino().id());
            result.append("), ");
        }
        
        Terminal.setText(result.toString());
        
        return null;
    }
    
    private void buscarNoGrafo(Vertice vertice) {
        arvoreDeProfundidadeTemp.add(vertice.id());
        coloracao[vertice.id()] = CINZA;
        descoberto[vertice.id()] = ++tempo;
        
        result.append(vertice.id());
        result.append(", ");

        try {
            grafo.adjacentesDe(vertice).forEach(u -> {
                try {
                    if (coloracao[u.id()] == PRETO) {
                        if (arvoreDeProfundidadeTemp.contains(u.id())) {
                            if (descoberto[u.id()] > descoberto[vertice.id()] + 1 && grafo.grauDoVertice(u) > 1) {
                                arestasAvanco.add(new Aresta(vertice, u));
                            } else {
                                arestasCruzamento.add(new Aresta(vertice, u));
                            }
                        }
                        else arestasCruzamento.add(new Aresta(vertice, u));
                    } else if (coloracao[u.id()] == CINZA) {
                        arestasRetorno.add(new Aresta(vertice, u));
                    }
                    else if (coloracao[u.id()] == BRANCO) {
                        arestasArvore.add(new Aresta(vertice, u));
                        buscarNoGrafo(u);
                    }
                }
                catch (Exception ex) {}
            });
        }
        catch (Exception ex){
        }

        coloracao[vertice.id()] = PRETO;
        finalizado[vertice.id()] = ++tempo;
        
    }
    /*******************************************************************************************************************/
    
    // Dijkstra
    @Override
    public ArrayList<Aresta> menorCaminho() throws Exception {
        reiniciarEstruturas();
        caminhoMinimo.clear();
        verticesMinimos.clear();
        
        PriorityQueue<Pair<Vertice, Double>> pq = new PriorityQueue<>();
        double[] distancias = new double[grafo.numeroDeVertices()];
        
        for (int i = 0; i < grafo.numeroDeVertices(); i++) {
            distancias[i] = Double.MAX_VALUE;
        }
        
        distancias[origemVertice.id()] = 0.0;
        pq.add(new Pair<>(origemVertice, 0.0));
        
        while (!pq.isEmpty())
        {
            Vertice a = pq.peek().getFirst();
            pq.poll();
            
            if (coloracao[a.id()] == BRANCO) {
                coloracao[a.id()] = CINZA;
                
                grafo.adjacentesDe(a).forEach(adj -> {
                    try {
                        // Relaxacao
                        double peso = grafo.lerAresta(a, adj).peso();
                        if (distancias[adj.id()] > distancias[a.id()] + peso) {
                            distancias[adj.id()] = distancias[a.id()] + peso;
                            pq.add(new Pair<>(adj, distancias[adj.id()]));
                            antecedentes[adj.id()] = a.id();
                        }
                    } catch (Exception ex) {
                    }
                });
            }
        }
        
        result.append("Distâncias[]: ");
        for (int i = 0; i < grafo.numeroDeVertices(); i++) {
            if (distancias[i] == Double.MAX_VALUE) {
                result.append("INF"); 
            }
            else result.append(distancias[i]);
            result.append(", ");
        }
        
        result.append("\nPais[]: ");
        for (int i = 0; i < grafo.numeroDeVertices(); i++) {
            if (antecedentes[i] != -1) {
                Vertice inicio = new Vertice(i);
                Vertice fim = new Vertice(antecedentes[i]);
                
                result.append(antecedentes[i]);
                caminhoMinimo.add(new Aresta(fim, inicio, grafo.lerAresta(fim, inicio).peso()));
            }
            else result.append("NULL");
            result.append(", ");
            verticesMinimos.add(antecedentes[i]);
        }
        
        Stack<Aresta> s = caminhoMaisCurto();
        
        double custo =  custoDoCaminho(s);
        if (custo != Double.MAX_VALUE) { 
            result.append("Custo do caminho minimo: ");
            result.append(custo);

            result.append("\nConjunto de Aresta que compoe o caminho:\n");
            while (!s.isEmpty()) {
                result.append("("+s.peek().origem().id() +", "+s.peek().destino().id()+"), ");
                s.pop();
            }
        }
        else if (origemVertice.id() != destinoVertice.id()) result.append("Não há caminho entre: "+origemVertice.id()+" até "+destinoVertice.id());
        
        Terminal.setText(result.toString());
        
        return null;
    }

    // DFS iterativo
    @Override
    public boolean existeCiclo() {
        reiniciarEstruturas();
        
        for (int i = 0; i < grafo.numeroDeVertices(); i++) {
            if (coloracao[i] == BRANCO)
                if (buscarCiclo(new Vertice(i))) return true;
        }
        return false;
    }
    
    public boolean buscarCiclo(Vertice v) {
        Stack<Vertice> pilha = new Stack<>();
        pilha.push(v);
        
        while (!pilha.empty()) {
            Vertice a = pilha.peek();
            pilha.pop();

            if (coloracao[a.id()] == BRANCO)
            {
                coloracao[a.id()] = CINZA;
                try {
                    grafo.adjacentesDe(a).forEach(u -> {
                        pilha.push(u);
                    });
                
                    coloracao[a.id()] = PRETO;
                }
                catch (Exception ex){
                }
                
            } else return true;
        }
        return false;
    }
        
    @Override
    public Collection<Aresta> agmUsandoKruskall() {
        // Ja foi calculado
        if (ArvoreGeradoraMin.isEmpty()) {
            try {
                // Criar o conjunto
                ConjuntoDisjunto conjunto = new ConjuntoDisjunto(grafo.numeroDeVertices());

                // Ordenar Arestas por peso
                ArrayList<Aresta> conjuntoArestas = grafo.carregarArestas();

                Collections.sort(conjuntoArestas);

                conjuntoArestas.forEach(aresta -> {
                    int a = aresta.origem().id();
                    int b = aresta.destino().id();
                    
                    int conjuntoA = conjunto.acharParentesco(a);
                    int conjuntoB = conjunto.acharParentesco(b);

                    if (conjuntoA != conjuntoB) {
                        ArvoreGeradoraMin.add(aresta);
                        conjunto.juntarConjuntos(conjuntoA, conjuntoB);
                    }
                });
            }
            catch (Exception ex) {
            }
        }
        
        result.append("Conjunto de Arestas:\n");
        ArvoreGeradoraMin.forEach(A -> {
            result.append("("+ A.origem().id() + "-" + A.destino().id()+ ")" + "\n");
        });
        
        try {
            result.append("Peso da AGM: " + custoDaArvoreGeradora(ArvoreGeradoraMin));
        }
        catch (Exception ex) {}
        
        Terminal.setText(result.toString());
        return null;
    }

    @Override
    public double custoDaArvoreGeradora(Collection<Aresta> arestas) throws Exception {
        if (!ArvoreGeradoraMin.isEmpty()) {
            double peso = 0.0;
            
            for (int i = 0; i < ArvoreGeradoraMin.size(); i++) {
                peso += ArvoreGeradoraMin.get(i).peso();
            }
            
            return peso;
        } else {
            throw new Exception("AGM nao executado");
        }
    }

    @Override
    public boolean ehArvoreGeradora(Collection<Aresta> arestas) {
        // Arestas compoe um ciclo?
        return existeCiclo();
    }

    // Retornar um pilha de Arestas
    @Override
    public Stack<Aresta> caminhoMaisCurto() {
        Stack<Aresta> trajetoria = new Stack<>();
        
        int walker = destinoVertice.id();
        result.append("\nCaminho Minímo entre "+origemVertice.id()+" até "+destinoVertice.id()+"\n");
        try {
            while (walker != origemVertice.id() && walker != -1) {
                if (verticesMinimos.get(walker) != -1) {
                    trajetoria.push(new Aresta(new Vertice(verticesMinimos.get(walker)), new Vertice(walker)));
                }
                walker = verticesMinimos.get(walker);
            }
        }
        catch (Exception ex) {
            trajetoria.clear();
        }
        
        return trajetoria;
    }

    @Override
    public double custoDoCaminho(Stack<Aresta> arestas) throws Exception {
        if (arestas.isEmpty()) return Double.MAX_VALUE;
        
        double custo = 0.0;
        ArrayList<Aresta> temp = new ArrayList<>();
        
        arestas.forEach(A -> {
            temp.add(A);
        });
        
        for (int u = 0; u < temp.size(); u++) {
            Vertice inicio = temp.get(u).origem();
            Vertice fim = temp.get(u).destino();
            
            custo += grafo.lerAresta(inicio, fim).peso();
        }
        return custo;
    }

    @Override
    public boolean ehCaminho(ArrayList<Aresta> arestas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Aresta> arestasDeArvore() {
        return arestasArvore;
    }

    @Override
    public Collection<Aresta> arestasDeRetorno() {
        return arestasRetorno;
    }

    @Override
    public Collection<Aresta> arestasDeAvanco() {
        return arestasAvanco;
    }

    @Override
    public Collection<Aresta> arestasDeCruzamento() {
        return arestasCruzamento;
    }

    // Algoritmos em Grafos
}
