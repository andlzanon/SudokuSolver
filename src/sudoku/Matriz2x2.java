package sudoku;

/**
 * Created by Andre on 23/10/2017.
 */
public class Matriz2x2 {

    int [][] matriz;

    public Matriz2x2(){
        matriz = new int[2][2];
    }

    //copia a matriz
    public void copiaMatriz(int [][] matrizCopia){
        for(int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                this.matriz[i][j] = matrizCopia[i][j];
            }
        }
    }

    public void copiaMatriz(Matriz2x2 matrizCopia){
        for(int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
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

    //verifica se e possivel colocar elemento no local x,y
    public boolean verificaLinhaEColuna(int x, int y){
        if(matriz[x][y] == 0)
            return false;

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                if(matriz[x][y] == matriz[i][j])
                    if(x!=i || y!=j)
                        return false;
            }
        }

        return true;
    }


    //metodo verifica se o sudoku esta pronto
    public boolean completa(){
        for(int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                if(!verificaLinhaEColuna(i, j))
                    return false;
            }
        }
        return true;
    }

    public int[] proximoBranco(){
        for(int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                if(matriz[i][j] == 0){
                    int [] aux = {i, j};
                    return aux;
                }
            }
        }
        int[] aux = {-1,-1};
        return aux;
    }

    //metodo que desenha a matriz do sudoku
    public void printMatriz(){
        for(int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                //trata os espacamntos
                System.out.printf(matriz[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
