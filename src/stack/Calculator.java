package stack;

/**
 * 模拟计算器
 *
 * @author cy
 * @create 2019-09-02 21:29
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "3+2*6-2";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';

        while (true) {
            //得到每一个字符
            ch = expression.substring(index, index + 1).charAt(0);

            //判断ch是什么
            if (operStack.isOper(ch)) {//是运算符
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    //判断优先级
                    //当前操作符优先级小于或等于栈中的，则从数栈中pop两个数，再从符号栈中
                    //pop出一个符号，进行计算，将结果保存到数栈中，然后将当前运算符入栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peak())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //如果当前操作符的优先级大于栈中的操作符，就入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }
            } else {
                //ch是一个数,直接入数字栈
                //numStack.push(ch); 有误，push进去的是字符
                numStack.push(ch - 48);
            }

            index ++;
            //判断index
            if(index == expression.length()){
                break;
            }

            //表达式扫描完毕，顺序从数栈和符号栈中pop出相应的数字和符号，并运行
            while(true){
                if(operStack.isEmpty()){
                    break;
                }
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = operStack.pop();

            }
        }
    }
}

class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1; //栈顶，初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    //增加返回栈顶元素的方法，不进行pop
    public int peak() {
        return stack[top];
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }

        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况（遍历），需要从栈顶开始
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //扩展功能
    //返回运算符的优先级，优先级由程序员确定，优先级使用数字确定
    //数字越大，优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //非运算符
        }
    }

    //判断是不是一个运算符
    public boolean isOper(int val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
        }
        return res;
    }
}
