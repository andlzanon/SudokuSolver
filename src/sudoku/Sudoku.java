package sudoku;
/**
 *
 * @author Andre
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class Sudoku {

    public static void main(String[] args) {
        //a partir do algoritmo de busca em profundidade passado no slide 30 da aula 6
        //define as pilhas open e close para os próximo e visitados respectivamente
        Stack<Matriz> open = new Stack<>();
        Stack<Matriz> closed = new Stack<>();
        
        //fila caso queria busca em largura
        //Queue<Matriz> open = new LinkedList<>();
        //Queue<Matriz> closed = new LinkedList<>();
        final int tamanho = 9;

        //sudoku 1
        int[][] sudokuDificil = {
                {8, 6, 0, 3, 0, 0, 0, 0, 0},
                {0, 1, 5, 4, 0, 0, 0, 0, 0},
                {0, 3, 9, 5, 0, 2, 0, 0, 0},
                {0, 0, 0, 8, 0, 0, 6, 2, 0},
                {0, 0, 0, 6, 0, 4, 5, 0, 0},
                {6, 2, 0, 9, 7, 5, 0, 8, 1},
                {0, 0, 3, 7, 9, 8, 0, 0, 6},
                {9, 0, 6, 1, 4, 0, 0, 5, 0},
                {0, 0, 8, 2, 0, 6, 0, 3, 7}};

        //sudoku facil
        int[][] sudokuFacil = {
                {4, 3, 5, 0, 0, 9, 7, 8, 1},
                {0, 0, 2, 5, 7, 1, 4, 0, 3},
                {1, 9, 7, 8, 3, 4, 0, 6, 2},
                {8, 2, 6, 1, 9, 5, 3, 4, 7},
                {3, 7, 0, 0, 8, 2, 0, 1, 5},
                {9, 5, 1, 7, 4, 3, 6, 2, 8},
                {5, 1, 9, 3, 2, 6, 8, 7, 4},
                {2, 4, 8, 9, 5, 7, 1, 3, 0},
                {0, 6, 0, 4, 0, 8, 2, 5, 9}};

        //sudoku quase completo
        int[][] sudokuQuaseResolvido = {
                {8, 6, 2, 3, 1, 7, 4, 9, 5},
                {7, 1, 5, 4, 8, 9, 2, 6, 0},
                {4, 3, 9, 5, 6, 2, 7, 1, 8},
                {5, 9, 7, 8, 3, 1, 6, 2, 4},
                {3, 8, 0, 6, 2, 4, 5, 7, 9},
                {6, 2, 4, 9, 7, 5, 3, 8, 1},
                {2, 5, 3, 7, 9, 8, 1, 4, 6},
                {9, 7, 6, 1, 4, 3, 8, 5, 2},
                {0, 4, 8, 2, 5, 6, 9, 3, 0}};
        
       //sudoku mais dificil 2
        int[][] sudokuDificil2 = {
                {0, 0, 0, 2, 0, 7, 0, 0, 0},
                {4, 1, 0, 6, 0, 8, 0, 9, 7},
                {6, 0, 0, 0, 0, 0, 0, 0, 2},
                {0, 3, 0, 0, 6, 0, 0, 4, 0},
                {9, 0, 0, 5, 0, 1, 0, 0, 3},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 8},
                {5, 9, 0, 7, 8, 4, 0, 1, 6},
                {7, 2, 0, 0, 0, 0, 0, 5, 9}};


        //cria uma matriz que na vdd e um sudoku.
        Matriz matriz = new Matriz();
        //copia o sudokuResolvido para matriz
        matriz.copiaMatriz(sudokuDificil2);
        //imprime inicial = start
        matriz.printMatriz();
        
        //inicio do algoritmo de Busca em Profundidade
        //open = [start]
        open.add(matriz);

   while (!open.isEmpty()) {
            int x = -1, y = -1;
            //x = pop
            Matriz matriz1 = open.pop();
            //caso fila usar comando abaixo:
            //Matriz matriz1 = open.remove();

            //verifica se existem espacos em branco, ou seja iguais 0 na matriz
            if(matriz1.proximoBranco()!= null){
                x = matriz1.proximoBranco()[0];
                y = matriz1.proximoBranco()[1];
            }

            //x é objetivo retorne sucesso
            if (matriz1.completa()) {
                System.out.println("Matriz resolvida");
                matriz1.printMatriz();
                break;
            }
            else {
                if (x >= 0 && y >= 0) {
                    //gera os filhos
                    for (int i = 1; i <= tamanho; i++) {
                        Matriz copia = new Matriz();
                        //cria matriz de copia
                        copia.copiaMatriz(matriz1);
                        
                        /*busca desinformada gera de 1 a 9 todos os filhos  
                        copia.set(x, y, i); */
                        
                        /*busca informada: qtadeDeNumeros gera a quantidades de numeros que existem */
                        //para cada numero de 1 a 9 
                        copia.qtadeDeNumeros();
                        //ordena o vetor de menor para maior em quantidade de aparicoes
                        copia.menorEmQuantidade();
                        //seta a partir do numero com maior numero de aparicoes
                        copia.set(x, y, copia.getArray().get(i-1).getKey()); 
                        
                        /*comandos p printar filhos
                        System.out.println("Matriz NAO resolvida");
                        copia.printMatriz();
                        se nao tiver sido vizitada e se nao tiver conflitos, adiciona na open */
                        if ((!open.contains(copia) || !closed.contains(copia)) && copia.semConflitos()){
                            open.add(copia);
                        }
                            
                    }
                    //push(closed, X) 
                    closed.add(matriz1);
                } else {
                    //push(closed, X) caso nao tenha espacos vazios e nao seja sudoku resolvido
                    closed.add(matriz1);
                }
            }
            //imprime matriz que passou
            System.out.println("Matriz Parcial");
            matriz1.printMatriz();
            //limpa cmg. TODO: nao esta funcionando esse comando
            try{
                Runtime.getRuntime().exec("cls");  
            }catch(Exception e){
                
            }
        }
        //fim da execucao. Sudoku resolvido
        System.out.println("Sudoku Resolvido");
    }
}