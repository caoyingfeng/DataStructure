package linkedlist;

/**
 * 约瑟夫问题
 * @author cy
 * @create 2019-08-31 20:52
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circle = new CircleSingleLinkedList();
        circle.addBoy(25);
        circle.showBoy();

        //测试小孩出圈
        circle.countBoy(1,2,25);
    }
}


//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);

    //添加小孩节点，构建成一个环形链表
    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("nums不正确");
            return;
        }
        Boy curBoy = null; //辅助指针，帮助构建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if(i==1){
                first = boy;
                first.setNext(first);//构成一个环
                curBoy = first;
            }else{

                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy(){
        //判断链表是否为空
        if(first == null){
            System.out.println("没有小孩");
            return;
        }
        Boy curBoy = first;
        //有误，当只有一个小孩时，没有输出
//        while(curBoy.getNext() != first){
        while(true){
            System.out.printf("小孩编号为：%d\n",curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums){
        //先对数据进行校验
        if(startNo <1 || first==null || startNo>nums){
            System.out.println("参数有误，重新输入");
            return;
        }
        // 创建辅助指针，帮助完成小孩出圈
        Boy helper = first;
        // helper应指向环形链表的最后一个节点
        while(true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        // 小孩在报数前，先让first和helper移动到指定位置
        for (int i = 0; i < startNo-1; i++) {
            helper = helper.getNext();
            first = first.getNext();
        }
        //当小孩报数时，first和helper同时移动countNum-1次，然后出圈
        //当移动到最后一个小孩时，出圈
        while(true){
            if(first == helper){//最后一个小孩
                break;
            }
            for (int i = 0; i < countNum-1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时，first指向的小孩就是要出圈的小孩
            System.out.printf("小孩%d出圈\n",first.getNo());
            //小孩出圈
            first = first.getNext();
            helper.setNext(first);
        }
        //最后一个小孩出圈
        System.out.printf("最后留下小孩%d\n",first.getNo());
    }
}
//创建一个boy类，表示一个节点
class Boy{
    private int no; //编号
    private Boy next; //指向下一个节点

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}