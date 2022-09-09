/*
 *  Author: Vinicius Marrafon
 *  Language: C
 *  O.S.: Windows NT 10.0
 *  Signature: ViMM
 */
#include <limits.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX(x, y) x > y? x : y

// Estruturas da lista
typedef struct Node
{
    void *data;
    size_t sizeNode;
    struct Node *next;
}
Node;

typedef struct Array
{
    Node *ptr, *end;
    size_t n;
}
Array;

// Desclarar como global para simplificacao dos parametros
Array arr = {
    .ptr = (void *)0,
    .end = (void *)0,
    .n = 0
};

// Resultados!
// Melhor combinacao para os motoqueiros
int *bestM1 = (void *)0, *bestM2 = (void *)0;
int bestN1 = 0, bestN2 = 0;

// Funcoes da lista
void append(void *, size_t size);
void *get(int index);
size_t getSize(int index);
void destroy(void);
void display(const char *);
int calculate(void);

// Calcular e armazenar todas as possiveis combinacoes na lista
void combine(int *, int *, int, int, int, int m);
void storeCombinations(int *, int, int);

// Funcao para ordenacao do infile (complexidade O(n + k))
void countSort(int *, int, int);

// Carregamento dinamico do infile para um array
int *loadInFile(char const *, int *);

int main(int argc, char *argv[])
{
    // Validacao da execucao do programa
    if (argc < 2)
    {
        fprintf(stderr, "Usage: %s {_inFile_} [_outfile_](opcional)\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    
    // Abertura do arquivo de saida
    char *output = (argc == 3)? argv[2] : "outfile.txt";
    FILE *outfile = fopen(output, "w");

    // Carregamento do arquivo
    int n = 0;
    int *v = loadInFile(argv[1], &n);

    // Calculo e armazenamento das combinacoes
    for (int x = 1; x < n; x++)
        storeCombinations(v, n, x);

    // Escrito no outfile!
    fprintf(outfile, "==> Melhor Tempo: %d\n", calculate());
    
    // Try the combinations
    fputs("*Moto 1\n", outfile);
    for (int i = 0; i < bestN1; i++)
        fprintf(outfile, "%d\n", bestM1[i]);

    
    fputs("*Moto 2\n", outfile);
    for (int i = 0; i < bestN2; i++)
        fprintf(outfile, "%d\n", bestM2[i]);

    // Free!
    fclose(outfile);
    destroy();
}

int *loadInFile(char const *path, int *n)
{
    // Load V;
    FILE *infile = fopen(path, "r");
    if (infile == (void *)0)
    {
        fprintf(stderr, "Couldn't open file %s\n", path);
        return (void *)0;
        // Throw Exception
    }

    int *v = (void *)0;
    int pos = 0, capacity = 0;
    int maxIn = 0;

    char line[100] = {'\0'};

    while (fgets(line, 99, infile))
    {
        if (pos + 1 > capacity)
        {
            if (capacity == 0) capacity = 16;
            else if (capacity <= INT_MAX/ 2) capacity *= 2;
            else
            {
                free(v);
                return (void *)0;
            }

            int *temp = realloc(v, capacity * sizeof(int32_t));
            v = temp;
        }

        v[pos++] = abs(atoi(line));
        (*n)++;

        if (v[pos - 1] > maxIn) maxIn = v[pos - 1];
    }

    countSort(v, pos, maxIn);
    
    fclose(infile);

    return v;
}

void countSort(int *v, int n, int range)
{
    int *sortV = malloc(n * sizeof(int32_t));
    int count[range + 1];

    memset(count, 0x00, sizeof(count));

    // Count the number of each elements on v
    for (int i = 0; i < n; i++)
        count[v[i]]++;
    
    // Determine the position of each element
    for (int i = 1; i < range + 1; i++)
        count[i] += count[i - 1];

    // Sort
    for (int i = 0; i < n; i++)
    {
        sortV[count[v[i]] - 1] = v[i];
        --count[v[i]];
    }

    memcpy(v, sortV, n * sizeof(int32_t));
    free(sortV);
}

int calculate(void)
{   
    int mid = arr.n/ 2;
    int ans = INT_MAX;
    int dif = INT_MAX;
    
    for (int i = 0; i < mid; i++)
    {    
        int sum1 = 0;
        int sum2 = 0;
        
        int n1 = getSize(i)/ sizeof(int32_t);
        int n2 = getSize(arr.n - i - 1)/ sizeof(int32_t);
        
        int *m1 = (int *)get(i);
        int *m2 = (int *)get(arr.n - i - 1);
        
        int n = MAX(n1, n2);
        
        // printf("n1: %d, n2: %d, => n: %d\n", n1, n2, n);
        for (int u = 0; u < n; u++)
        {
            if (u < n1) sum1 += ((u < n1 - 1)? 2 : 1) * m1[u];
            if (u < n2) sum2 += ((u < n2 - 1)? 2 : 1) * m2[u];
        }
        
        // printf("Sum1: %d, Sum2: %d with difference %d\n", sum1, sum2, abs(sum1 - sum2));
        
        // Podem ter diferencas iguais, mas não necessariamente sera o menor!
        if (abs(sum1 - sum2) <= dif)
        {
            dif = abs(sum1 - sum2);
            if (MAX(sum1, sum2) < ans)
            {
                ans = MAX(sum1, sum2);
                bestM1 = m1;
                bestM2 = m2;
                bestN1 = n1;
                bestN2 = n2;

                // puts("M1:");
                // for (int i = 0; i < n1; i++)
                //     printf("[%d]", m1[i]);
                // puts("\nEnd M1");
                
                // puts("M2:");
                // for (int i = 0; i < n2; i++)
                //     printf("[%d]", m2[i]);
                // puts("\nEnd M2");
            }
        }
    }
    // printf("Shortest Diff.: %d\n", dif);
    return ans;
}

void storeCombinations(int *v, int n, int m)
{
    int data[m];
 
    combine(v, data, 0, n - 1, 0, m);
}

void combine(int *v, int *data, int start, int end, int index, int m)
{
    // end = cte
    if (index == m)
    {
        append((void *)& data[0], (m * sizeof(int32_t)));
        return;
    }

    for (int i = start; i <= end && end - i + 1 >= m - index; i++)
    {
        data[index] = v[i];
        combine(v, data, i + 1, end, index + 1, m);
    }
}

void append(void *data, size_t size)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->sizeNode = size;
    newNode->data = malloc(newNode->sizeNode);
    memcpy(newNode->data, data, newNode->sizeNode);
    
    // always the last
    newNode->next = (void *)0;
    
    if (arr.ptr == (void *)0)
    {  
        arr.ptr = arr.end = newNode;
    }
    else
    {
        arr.end->next = newNode;
        arr.end = newNode;
    }
    
    arr.n++;
}

void *get(int index)
{
    if (index >= arr.n || index < 0) return (void *)0;
    int count = index;
    Node *it = arr.ptr;
    while (count--)
        it = it->next;
    
    return it->data;
}

size_t getSize(int index)
{
    if (index >= arr.n || index < 0) return 0;
    int count = index;
    Node *it = arr.ptr;
    while (count--)
        it = it->next;
    
    return it->sizeNode;
}

void destroy(void)
{
    Node *it = arr.ptr;
    while (it != (void *)0)
    {
        Node *temp = it->next;
        free(it->data);
        free(it);
        it = temp;
    }
}

void display(const char *format)
{
    Node *it = arr.ptr;
    while (it != (void *)0)
    {
        for (int i = 0; i < it->sizeNode/ sizeof (int32_t); i++)
            printf(format, *(int32_t *)(it->data + (i * sizeof(int32_t))));
        puts(" \\");
        it = it->next;
    }
}

