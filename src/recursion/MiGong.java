package recursion;

/**
 * @author CY
 * @create 2019-09-22 14:40
 */
public class MiGong {
    public static void main(String[] args) {
        //创建一个地图,作为迷宫
        int[][] map = new int[8][7];
        //1代表墙，上下为墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右为墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //打印map
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("***************");
        setWay(map,1,1);
        //打印通路
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路
    //说明
//    1、map表示地图
//    2、i、j表示从地图的哪个位置开始出发(1,1)
    //3、如果小球能到map[6][5]位置，说明通路找到
    //4、约定map[i][j]为0表示该点没有走过 ，1表示为墙，2表示通路可以走，3表示该点已经走过，但是走不通
    //5、走迷宫时，需要确定一个策略 下→右→上→左，如果该点不通，再回溯
    /**
     *
     * @param map 表示地图
     * @param i 从哪个位置找
     * @param j
     * @return  找到通路，就返回true,负责返回false+
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5] == 2){ //说明通路找到
            return true;
        }else{

            if(map[i][j] == 0){ //说明还没有走过
                //按照策略下→右→上→左走一下
                //假定该点可以走通
                map[i][j] = 2;
                if(setWay(map, i+1,j)){//向下走
                    return true;
                }else if(setWay(map, i,j+1)){//向右走
                    return true;
                }else if(setWay(map, i-1,j)){//向上走
                    return true;
                }else if(setWay(map, i, j-1)){//向左走
                    return true;
                }else{//此路不通
                    map[i][j] = 3;
                    return false;
                }
            }else{//map[i][j]为其他值
                return false;
            }
        }
    }
}
