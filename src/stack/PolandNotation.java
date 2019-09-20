package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式/后缀表达式
 * @author CY
 * @create 2019-09-19 20:04
 */
public class PolandNotation {
    public static void main(String[] args) throws Exception {
        //先定义给逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 - 为了方便，使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //1、先将"3 4 + 5 * 6 -"放到ArrayList中，
        //2、将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
        List<String> rpnList = getListString(suffixExpression);

        int res = calculate(rpnList);
        System.out.println("(3+4)*5-6 = " + res);
    }

    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for(String ele:split){
            list.add(ele);
        }
        return list;
    }

    /**
     * 从左至右扫描，将3和4压入堆栈；
     遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     将5入栈；
     接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
     将6入栈；
     最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     * @param ls
     * @return
     */
    public static int calculate(List<String> ls) throws Exception {
        //创建一个数字栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for(String item: ls){
            //使用正则取出数字
            if(item.matches("\\d+")){
                //入栈
                stack.push(item);
            }else{
                //pop出两个数，并计算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1 /num2;
                }else{
                    throw new Exception("运算符号有误");
                }
                stack.push(String.valueOf(res));
            }
        }
        //最后留下来的就是结果
        return Integer.parseInt(stack.pop());
    }
}
