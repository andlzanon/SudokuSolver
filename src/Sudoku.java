import java.util.Stack;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.util.Pair;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class Sudoku {
    
    public static void main(String[] args) {
        //Usuario escolhe entre Busca em profundidade informada(Best-first/guloso) ou desinformada
        //ou busca em largura(desinformada)
        Scanner entrada = new Scanner(System.in);
        int funcao = 0;
        while(funcao < 1 || funcao > 3){
            //limpa a tela
            clrscr();
            System.out.println(" Bem-vindo ao Sudoku!\n\n Digite o numero da tecnica a ser utilizada:\n");
            System.out.printf(" 1 - Busca Informada (Best-first/guloso)\n 2 - Busca Desinformada (Em profundidade)\n"
                    + " 3 - Busca Desinformada (Em largura)\n\n-> ");
            funcao = entrada.nextInt();
        }
        //Usuario escolhe nivel de dificuldade do sudoku (5 exemplos)
        int nivel = 0;
        while(nivel < 1 || nivel > 5){
            System.out.println("\n Digite o nivel de dificuldade do sudoku:\n");
            System.out.printf(" 1 - Muito facil\n 2 - Facil\n 3 - Medio\n 4 - Dificil\n 5 - Muito Dificil\n\n-> ");
            nivel = entrada.nextInt();
        }
            
        final int tamanho = 9;
        int contador = 0; //numero de iteracoes
        
        //Exemplos
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
        //sudoku medio
        int[][] sudokuMedio = {
                {8, 6, 0, 3, 0, 0, 0, 0, 0},
                {0, 1, 5, 4, 0, 0, 0, 0, 0},
                {0, 3, 9, 5, 0, 2, 0, 0, 0},
                {0, 0, 0, 8, 0, 0, 6, 2, 0},
                {0, 0, 0, 6, 0, 4, 5, 0, 0},
                {6, 2, 0, 9, 7, 5, 0, 8, 1},
                {0, 0, 3, 7, 9, 8, 0, 0, 6},
                {9, 0, 6, 1, 4, 0, 0, 5, 0},
                {0, 0, 8, 2, 0, 6, 0, 3, 7}};
        //sudoku dificil
        int[][] sudokuDificil = {
                {1, 0, 0, 0, 0, 0, 0, 0, 5},
                {0, 9, 8, 5, 0, 3, 7, 4, 0},
                {0, 4, 5, 0, 0, 0, 6, 1, 0},
                {0, 1, 0, 0, 9, 0, 0, 3, 0},
                {0, 0, 0, 8, 0, 1, 0, 0, 0},
                {0, 8, 0, 0, 3, 0, 0, 9, 0},
                {0, 2, 7, 0, 0, 0, 3, 6, 0},
                {0, 3, 1, 9, 0, 6, 4, 2, 0},
                {9, 0, 0, 0, 0, 0, 0, 0, 8}};
        //sudoku mais dificil
        int[][] sudokuMuitoDificil = {
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
        //copia algum exemplo inicial de sudoku para a matriz, de acordo com a escolha do usuario
        switch (nivel){
            case 1:
                matriz.copiaMatriz(sudokuQuaseResolvido);
                break;
            case 2:
                matriz.copiaMatriz(sudokuFacil);
                break;
            case 3:
                matriz.copiaMatriz(sudokuMedio);
                break;
            case 4:
                matriz.copiaMatriz(sudokuDificil);
                break;
            case 5:
                matriz.copiaMatriz(sudokuMuitoDificil);
                break;
        }
        //limpa tela e imprime inicial = start
        clrscr();
        System.out.println("Estado inicial...");
        matriz.printMatriz();
        for(int i = 0; i < 2; i++){
            if(i!=0)
                System.out.print(" Pressione enter para iniciar");
            entrada.nextLine();
        }
	        
        //a partir do algoritmo de busca em profundidade passado no slide 30 da aula 6
        //define as pilhas open e close para os próximo e visitados respectivamente        
        if(funcao != 3){//se nao for busca em largura
            Stack<Matriz> openS = new Stack<>();
            Stack<Matriz> closedS = new Stack<>();
            //open = [start]
            openS.add(matriz);
            
            while (!openS.isEmpty()) {
                int x = -1, y = -1;
                //x = pop
                Matriz matriz1 = openS.pop();
                //verifica se existem espacos em branco, ou seja iguais 0 na matriz
                if(matriz1.proximoBranco()!= null){
                    x = matriz1.proximoBranco()[0];
                    y = matriz1.proximoBranco()[1];
                }
                //x é objetivo retorne sucesso
                if (matriz1.completa()) {
                    clrscr();
                    System.out.println("Matriz resolvida");
                    matriz1.printMatriz();
                    contador++;
                    break;
                } else {
                    if (x >= 0 && y >= 0) {
                        //gera os filhos
                        for (int i = 1; i <= tamanho; i++) {
                            Matriz copia = new Matriz();
                            //cria matriz de copia
                            copia.copiaMatriz(matriz1);

                            if(funcao == 2)
                                /*busca desinformada gera de 1 a 9 todos os filhos */ 
                                copia.set(x, y, i);
                            else{
                                //busca informada: qtadeDeNumeros gera a quantidades de numeros que existem 
                                //para cada numero de 1 a 9 
                                copia.qtadeDeNumeros();
                                //ordena o vetor de menor para maior em quantidade de aparicoes
                                copia.menorEmQuantidade();
                                //seta a partir do numero com maior numero de aparicoes
                                copia.set(x, y, copia.getArray().get(i-1).getKey());
                            }
                            /*comandos p printar filhos
                            System.out.println("Matriz NAO resolvida");
                            copia.printMatriz();
                            se nao tiver sido vizitada e se nao tiver conflitos, adiciona na open */
                            if ((!openS.contains(copia) || !closedS.contains(copia)) && copia.semConflitos()){
                                openS.add(copia);
                            }
                        }
                        //push(closed, X) 
                        closedS.add(matriz1);
                    } else {
                        //push(closed, X) caso nao tenha espacos vazios e nao seja sudoku resolvido
                        closedS.add(matriz1);
                    }
                }
                try { Thread.sleep (5); } catch (InterruptedException ex) {}
                //limpa tela
                clrscr();
                //imprime matriz que passou
                System.out.println("Matriz Parcial");
                matriz1.printMatriz();
                contador++;
            }
        //funcao == 3 (Busca em Largura)
        } else {
            //filas
            Queue<Matriz> openQ = new LinkedList<>();
            Queue<Matriz> closedQ = new LinkedList<>();
            //open = [start]
            openQ.add(matriz);
            
            while (!openQ.isEmpty()) {
                int x = -1, y = -1;
                Matriz matriz1 = openQ.remove();
                //verifica se existem espacos em branco, ou seja iguais 0 na matriz
                if(matriz1.proximoBranco()!= null){
                    x = matriz1.proximoBranco()[0];
                    y = matriz1.proximoBranco()[1];
                }
                //x é objetivo retorne sucesso
                if (matriz1.completa()) {
                    clrscr();
                    System.out.println("Matriz resolvida");
                    matriz1.printMatriz();
                    contador++;
                    break;
                }
                else {
                    if (x >= 0 && y >= 0) {
                        //gera os filhos
                        for (int i = 1; i <= tamanho; i++) {
                            Matriz copia = new Matriz();
                            //cria matriz de copia
                            copia.copiaMatriz(matriz1);

                            /*busca desinformada gera de 1 a 9 todos os filhos */ 
                            copia.set(x, y, i);
                            /*comandos p printar filhos
                            System.out.println("Matriz NAO resolvida");
                            copia.printMatriz();
                            se nao tiver sido vizitada e se nao tiver conflitos, adiciona na open */
                            if ((!openQ.contains(copia) || !closedQ.contains(copia)) && copia.semConflitos()){
                                openQ.add(copia);
                            }
                        }
                        //push(closed, X) 
                        closedQ.add(matriz1);
                    } else {
                        //push(closed, X) caso nao tenha espacos vazios e nao seja sudoku resolvido
                        closedQ.add(matriz1);
                    }
                }
                try { Thread.sleep (5); } catch (InterruptedException ex) {}
                //limpa tela
                clrscr();
                //imprime matriz que passou
                System.out.println("Matriz Parcial");
                matriz1.printMatriz();
                contador++;
            }
        }
        //fim da execucao. Sudoku resolvido
        System.out.println("Sudoku Resolvido");
        System.out.println("Iteracoes: "+contador);
    }
    
    public static void clrscr(){
    //Limpa a tela
        try {
            //Windows
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            //Linux
            } else {
                System.out.print("\033[H\033[2J");
		System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {}
    }
}

class Matriz {
    
    private int [][] matriz;
    //array de pares em que a chave e o numero de 1 a 9 e o valor sua quantidade de aparicoes
    List<Pair<Integer, Integer>> qtadePorNum;
    
    public Matriz(){
        matriz = new int[9][9];
        qtadePorNum = new ArrayList<>();
    }

    //copia a matriz a partir de uma matriz vetorial
    public void copiaMatriz(int [][] matrizCopia){
        for(int i = 0; i < 9; i++){
            System.arraycopy(matrizCopia[i], 0, this.matriz[i], 0, 9);
        }
    }

    //copia matriz a partir da mesma classe
    public void copiaMatriz(Matriz matrizCopia){
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                this.matriz[i][j] = matrizCopia.get(i, j);
            }
        }
    }
    
    //retorna o array de pares
    public List<Pair<Integer, Integer>> getArray(){
        return qtadePorNum;
    }

    //seta o valor especificado em x, y na matriz
    public void set(int x, int y, int elem){
        matriz[x][y] = elem;
    }

    //retorna o valor especificado em x, y na matriz
    public int get(int x, int y){
        return matriz[x][y];
    }

    //metodo que retorna o quadrante/grupo dado uma posicao
    public int getQuadrante(int x, int y){
        if(x>=0 && x<=2){
            if(y>=0 && y<=2)
                return 1;
            if(y>=3 && y<=5)
                return 2;
            if(y>=6 && y<=8)
                return 3;
        }

        if(x>=3 && x<=5){
            if(y>=0 && y<=2)
                return 4;
            if(y>=3 && y<=5)
                return 5;
            if(y>=6 && y<=8)
                return 6;
        }

        if(x>=6 && x<=8){
            if(y>=0 && y<=2)
                return 7;
            if(y>=3 && y<=5)
                return 8;
            if(y>=6 && y<=8)
                return 9;
        }
        return -1;
    }

    //verifica se e possivel colocar elemento no local x,y
    public boolean verificaLinhaEColuna(int x, int y){
        for(int i = 0; i < 9; i++){
            if(matriz[x][y] == matriz[x][i] && y!=i){
                return false;
            }
        }

        for (int j = 0; j < 9; j++){
            if(matriz[x][y] == matriz[j][y] && x!=j){
                return false;
            }
        }

        return true;
    }

    //verifica se e possivel colocar um elemento na posicao x,y considerando seu quadrante
    public boolean verificaQuadrante(int x, int y){
        int quadrante = getQuadrante(x, y);

        switch (quadrante){
            case 1:
                //se ja existe o elemento no quadrante 1: x de 0 a 2 e y de 0 a 2
                //seguintes sao analogos
                for(int i = 0; i <= 2; i++){
                    for(int j = 0; j <= 2; j++){
                        if(matriz[x][y] == matriz[i][j])
                            if(x!=i && y!=j)
                                return false;
                    }
                }
                return true;

            case 2:
                for(int i = 0; i <= 2; i++){
                    for(int j = 3; j <= 5; j++){
                        if(matriz[x][y] == matriz[i][j])
                            if(x!=i && y!=j)
                                return false;
                    }
                }
                return true;

            case 3:
                for(int i = 0; i <= 2; i++){
                    for(int j = 6; j <= 8; j++){
                        if(matriz[x][y] == matriz[i][j])
                            if(x!=i && y!=j)
                                return false;

                    }
                }
                return true;

            case 4:
                for(int i = 3; i <= 5; i++){
                    for(int j = 0; j <= 2; j++){
                        if(matriz[x][y] == matriz[i][j])
                            if(x!=i && y!=j)
                                return false;
                    }
                }
                return true;

            case 5:
                for(int i = 3; i <= 5; i++){
                    for(int j = 3; j <= 5; j++){
                        if(matriz[x][y] == matriz[i][j])
                            if(x!=i && y!=j)
                                return false;
                    }
                }
                return true;

            case 6:
                for(int i = 3; i <= 5; i++){
                    for(int j = 6; j <= 8; j++){
                        if(matriz[x][y] == matriz[i][j])
                            if(x!=i && y!=j)
                                return false;
                    }
                }
                return true;

            case 7:
                for(int i = 6; i <= 8; i++){
                    for(int j = 0; j <= 2; j++){
                        if(matriz[x][y] == matriz[i][j])
                           if(x!=i && y!=j)
                                return false;
                    }
                }
                return true;

            case 8:
                for(int i = 6; i <= 8; i++){
                    for(int j = 3; j <= 5; j++){
                        if(matriz[x][y] == matriz[i][j])
                            if(x!=i && y!=j)
                                return false;
                    }
                }
                return true;

            case 9:
                for(int i = 6; i <= 8; i++){
                    for(int j = 6; j <= 8; j++){
                        if(matriz[x][y] == matriz[i][j])
                            if(x!=i && y!=j)
                                return false;
                    }
                }
                return true;

            default:
                return false;
        }
    }

    //metodo verifica se o sudoku esta pronto
    public boolean completa(){
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(!verificaLinhaEColuna(i,j) || !verificaQuadrante(i,j) || matriz[i][j] == 0){
                    return false;
                }

            }
        }

        return true;
    }
    
    //metodo verifica se o sudoku esta sem conflitos. Nao considera os espacos em branco
    public boolean semConflitos(){
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(matriz[i][j] != 0){
                    if(!verificaQuadrante(i,j) || !verificaLinhaEColuna(i, j)){
                        return false;
                    }   
                }
            }
        }

        return true;
    }


    //retorna a posicao em que esta o valor branco
    public int[] proximoBranco(){
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(matriz[i][j] == 0){
                    int [] aux = {i, j};
                    return aux;
                }
            }
        }
        return null;
    }

    //metodo que desenha a matriz do sudoku
    public void printMatriz(){
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(matriz[i][j] != 0){
                    //trata os espacamntos
                    if((j+1)%3 == 0)
                        System.out.printf(matriz[i][j] + "     ");
                    else
                        System.out.printf(matriz[i][j] + "   ");
                }else{
                    if((j+1)%3 == 0)
                        System.out.printf("      ");
                    else
                        System.out.printf("    ");
                }
            }
            if((i+1)%3 == 0){
                System.out.println("\n");
            }
            else
                System.out.println();

        }
    }
    
    /**
     * para cada posicao do arrayList e colocado a quantidade de elementos de i+1
     */
    public void qtadeDeNumeros(){
        for(int i = 0; i < 9; i++){
            qtadePorNum.add(i, new Pair(i+1, numeroDeElementos(i+1)));
        }
    }
    
   /**
    * 
    * @param x valor que quer se saber a quantidade de elementos
    * @return numero de elementos de x
    */
    private int numeroDeElementos(int x){
        int count = 0;
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(matriz[i][j] == x)
                    count++;
            }
        }
        
        return count;
    }
    
    //ordena o valor de Pares em ordem crescente pelo menor numero de chaves
    public void menorEmQuantidade(){
       qtadePorNum.sort(new Matriz.PairValueComparator());
    }
    
    //classe que orderna um Array de pares
    private class PairValueComparator implements Comparator<Pair<Integer, Integer>> {
        @Override
        public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
            return p1.getValue().compareTo(p2.getValue());
        }
    }
}