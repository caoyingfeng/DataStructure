package recursion;

/**
 * 8皇后问题
 *
 * @author CY
 * @create 2019-09-22 16:17
 */
public class Queue8 {
    //表示多少个皇后
    int max = 8;
    //定义数组array，保存皇后的位置
    int[] array = new int[max];

    int count = 0;//记录打印次数，即多少种方法

    public static void main(String[] args) {
        Queue8 queue = new Queue8();
        queue.check(0);
        System.out.println("共" + queue.count);
    }

    //用于放置第n个皇后
    //注意：
    private void check(int n) {
        if (n == max) {//n=8说明8个皇后已然放好
            print();
            return;
        }
        //依次放入8个皇后，判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前皇后放入第i个位置
            array[n] = i;
            //判断是否冲突
            if (judge(n)) {
                //不冲突,放下一个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突或者递归结束后，开始回溯，则继续尝试下一个位置
        }
    }

    /**
     * 用于判断第n个皇后是否跟之前的冲突
     *
     * @param n
     * @return false表示冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //array[n] == array[i]表示在同一列
            //Math.abs(n -i) == Math.abs(array[n] -array[i])表示在斜线上
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //用于输出皇后的位置
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
