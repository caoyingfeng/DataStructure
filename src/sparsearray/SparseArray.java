package sparsearray;

/**
 * @author cy
 * @create 2019-08-29 13:37
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转换为稀疏数组
        //1、得到原数组中非0值的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
//        System.out.println(sum);

        //2、创建对应的稀疏数组
        int[][] sparse = new int[sum + 1][3];
        sparse[0][0] = chessArr1.length;
        sparse[0][1] = chessArr1[0].length;
        sparse[0][2] = sum;

        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if(chessArr1[i][j] != 0){
                    count ++;
                    sparse[count][0] = i;
                    sparse[count][1] = j;
                    sparse[count][2] = chessArr1[i][j];
                }
            }
        }

        System.out.println(" 稀疏数组为：");
        for (int i = 0; i < sparse.length; i++) {
            for (int j = 0; j < sparse[i].length; j++) {
                System.out.printf("%d\t",sparse[i][j]);
            }
            System.out.println();
        }

        //3、将稀疏数组还原为原数组
        int row1 = sparse[0][0];
        int col1 = sparse[0][1];
        int[][] chessArr2 = new int[row1][col1];

        System.out.println("还原数组为：");
        for (int i = 1; i <sparse.length; i++) {
            int row2 = sparse[i][0];
            int col2 = sparse[i][1];
            chessArr2[row2][col2] = sparse[i][2];
        }
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }

}
