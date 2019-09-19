package queue;

import java.util.Scanner;

/**
 * @author cy
 * @create 2019-08-29 16:26
 */

@SuppressWarnings("ALL")
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);
        char key = ' '; //接收用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean isFlag = true;
        while (isFlag) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队列中的头部数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    circleArrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    isFlag = false;
                    break;
                case 'a':
                    System.out.println("请输入数据");
                    int value = scanner.nextInt();
                    circleArrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        value = circleArrayQueue.getQueue();
                        System.out.println("取出数据为：" + value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        value = circleArrayQueue.headQueue();
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

class CircleArrayQueue {
    private int maxSize; //队列的最大容量
    private int front; //队列头,含义调整，front指向队列第一个元素，初始值为0
    private int rear; //队列尾，含义调整，rear指向队列最后一个元素的下一个位置，初始值为0
    private int[] arr; //用于存放数据的数组

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断队列是否为满
    public boolean isFull() {
        //注意点1
        return (rear + 1) % maxSize == front;
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
        //直接添加数据
        arr[rear] = data;
        //rear后移，考虑取模，注意点2
        rear = (rear + 1) % maxSize;
    }

    //取出队列中的数据
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取出数据");
        }
        //取出数据
        int value = arr[front];
        //front后移，考虑取模
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        //从front开始遍历，遍历多少个元素，难点，注意点3
        //队列中有效的数据个数为(rear+maxSize-front)%maxSize
        for (int i = front; i < (front + size()); i++) {
            System.out.printf("arr[%d]=%d\t", i % maxSize, arr[i % maxSize]);
        }
        System.out.println();
    }

    //求出当前队列有效的数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据,注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
}
