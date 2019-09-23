package sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author CY
 * @create 2019-09-22 17:12
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] array = new int[]{3,6,1,0,5};
//        bubbleSort(array);

        //测试冒泡排序的时间
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
//        System.out.println(Arrays.toString(arr));
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间是：" + date1Str);

        bubbleSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前时间是：" + date2Str);
    }

    public static void bubbleSort(int[] array){
        boolean flag = true;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                if(array[j] > array[j+1]){
                    flag = false;
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
            if(flag == true){
                break;
            }else{
                flag = false;
            }
        }
//        System.out.println(Arrays.toString(array));
    }

}
