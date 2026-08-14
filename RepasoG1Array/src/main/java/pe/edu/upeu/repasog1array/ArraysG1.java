package pe.edu.upeu.repasog1array;
public class ArraysG1 {
    public static int[] ejemploVector(){
        int[] v={15,20,16,12,8};
        System.out.println(v.length);
        v[2]=10;
        return v;
    }
    public static void imprimeVector(int[] v){
        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i]+"\t");
        }
    }

    public static void imprimeMatriz(int [][] m){
        for (int f = 0; f < m.length; f++) {
            for (int c = 0; c < m[0].length; c++) {
                System.out.print(m[f][c]+"\t");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        imprimeVector(ejemploVector());
        System.out.println("");
        int[][] m={{2,3},{4,6}, {9,10}};
        imprimeMatriz(m);
    }
}
