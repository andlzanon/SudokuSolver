package sudoku;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Andre on 23/10/2017.
 */
public class Matriz {

    int [][] matriz;

    public Matriz(){
        matriz = new int[9][9];
    }

    //copia a matriz
    public void copiaMatriz(int [][] matrizCopia){
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                this.matriz[i][j] = matrizCopia[i][j];
            }
        }
    }

    public void copiaMatriz(Matriz matrizCopia){
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                this.matriz[i][j] = matrizCopia.get(i, j);
            }
        }
    }

    public void set(int x, int y, int elem){
        matriz[x][y] = elem;
    }

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
                //trata os espacamntos
                if((j+1)%3 == 0)
                    System.out.printf(matriz[i][j] + "      ");
                else
                    System.out.printf(matriz[i][j] + "   ");
            }
            if((i+1)%3 == 0){
                System.out.println("\n");
            }
            else
                System.out.println();

        }
    }
}
