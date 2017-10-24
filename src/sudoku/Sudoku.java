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
        Stack<Matriz> open = new Stack<>();
        Stack<Matriz> closed = new Stack<>();
        
        //Queue<Matriz> open = new LinkedList<>();
        //Queue<Matriz> closed = new LinkedList<>();
        final int tamanho = 9;

        int[][] sudoku = {
                {8, 6, 0, 3, 0, 0, 0, 0, 0},
                {0, 1, 5, 4, 0, 0, 0, 0, 0},
                {0, 3, 9, 5, 0, 2, 0, 0, 0},
                {0, 0, 0, 8, 0, 0, 6, 2, 0},
                {0, 0, 0, 6, 0, 4, 5, 0, 0},
                {6, 2, 0, 9, 7, 5, 0, 8, 1},
                {0, 0, 3, 7, 9, 8, 0, 0, 6},
                {9, 0, 6, 1, 4, 0, 0, 5, 0},
                {0, 0, 8, 2, 0, 6, 0, 3, 7}};

        int[][] sudokuFacil = {
                {4, 3, 5, 0, 0, 9, 7, 1, 8},
                {0, 0, 2, 5, 7, 1, 4, 0, 3},
                {1, 9, 7, 8, 3, 4, 0, 6, 2},
                {8, 2, 6, 1, 9, 5, 3, 4, 7},
                {3, 7, 0, 0, 8, 2, 0, 1, 5},
                {9, 5, 1, 7, 4, 3, 6, 2, 8},
                {5, 1, 9, 3, 2, 6, 8, 7, 4},
                {2, 4, 8, 9, 5, 7, 1, 3, 0},
                {0, 6, 0, 4, 0, 8, 2, 5, 9}};

        int[][] sudokuResolvido = {
                {8, 6, 2, 3, 1, 7, 4, 9, 5},
                {7, 1, 5, 4, 8, 9, 2, 6, 0},
                {4, 3, 9, 5, 6, 2, 7, 1, 8},
                {5, 9, 7, 8, 3, 1, 6, 2, 4},
                {3, 8, 0, 6, 2, 4, 5, 7, 9},
                {6, 2, 4, 9, 7, 5, 3, 8, 1},
                {2, 5, 3, 7, 9, 8, 1, 4, 6},
                {9, 7, 6, 1, 4, 3, 8, 5, 2},
                {0, 4, 8, 2, 5, 6, 9, 3, 0}};

        //sudoku teste
        int[][] sudoku2 = {{1, 0}, {2, 3}};

        /*int [][] sudokuF = {{1,1}, {2,3}};
        Matriz2x2 m = new Matriz2x2();
        m.copiaMatriz(sudokuF);
        if(m.completa()){
            System.out.println("PASSOU");
        }
        else{
            System.out.println("ERRADO");
        }*/

        /*Matriz m = new Matriz();
        m.copiaMatriz(sudokuResolvido);
        if (m.completa()) {
            System.out.println("PASSOU");
        } else {
            System.out.println("ERRADO");
        }*/


        Matriz matriz = new Matriz();
        matriz.copiaMatriz(sudokuResolvido);
        matriz.printMatriz();

        open.add(matriz);


        while (!open.isEmpty()) {
            int x = -1, y = -1;
            Matriz matriz1 = open.pop();
            //Matriz matriz1 = open.remove();

            if(matriz1.proximoBranco()!= null){
                x = matriz1.proximoBranco()[0];
                y = matriz1.proximoBranco()[1];
            }

            if (matriz1.completa()) {
                System.out.println("Matriz resolvida");
                matriz1.printMatriz();
                break;
            }
            else {
                if (x >= 0 && y >= 0) {
                    for (int i = tamanho; i >= 1; i--) {
                        Matriz copia = new Matriz();
                        copia.copiaMatriz(matriz1);
                        copia.set(x, y, i);
                        //System.out.println("Matriz NAO resolvida");
                        //copia.printMatriz();
                        if (!open.contains(copia) && !closed.contains(copia))
                            open.add(copia);
                    }
                    closed.add(matriz1);
                } else {
                    closed.add(matriz1);
                }
            }
            System.out.println("Matriz Parcial");
            matriz1.printMatriz();
            try{
                Runtime.getRuntime().exec("cls");  
            }catch(Exception e){
                
            }
            
        }

        System.out.println("Sudoku Resolvido");
    }
}
