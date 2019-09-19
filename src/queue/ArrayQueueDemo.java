package queue;

import java.util.Scanner;

/**
 * @author cy
 * @create 2019-08-29 15:05
 */
@SuppressWarnings("Duplicates")
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' '; //接收用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队列中的头部数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入数据");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        value = arrayQueue.getQueue();
                        System.out.println("取出数据为：" + value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        value = arrayQueue.headQueue();
                        System.out.println("头部数据为：" + value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("输入有误，重新输入");
                    break;
            }
        }
        System.out.println("程序退出。。。");
    }
}

class ArrayQueue{
    private int maxSize; //队列的最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr; //用于存放数据的数组

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; //指向队列的头部，front指向队列头的前一个位置
        rear = -1; //指向队列的尾部，rear指向队列的最后一个数据
    }

    //判断队列是否为满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int data) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }
        rear++;
        arr[rear] = data;
    }

    //取出队列中的数据
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
//            System.out.println("队列为空，不能取出数据");
//            return -1;
            //通过抛出异常来提示
            throw new RuntimeException("队列为空，不能取出数据");
        }
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    /*public void showQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }

        for (int i = front + 1; i <= rear; i++) {
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
    }*/

    //显示队列的所有数据
    public void showQueue(){
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i <arr.length; i++) {
            System.out.printf("arr[%d] = %d",i,arr[i]);
            System.out.println();
        }
    }

    //显示队列的头数据,注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front + 1];
    }

}
