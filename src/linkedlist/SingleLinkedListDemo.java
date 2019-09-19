package linkedlist;

import sun.security.util.Length;

import java.util.Stack;

/**
 * @author cy
 * @create 2019-08-29 19:09
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        SingleLinkedList list = new SingleLinkedList();
//        list.addNode(hero1);
//        list.addNode(hero2);
//        list.addNode(hero3);
//        list.addNode(hero4);
//        list.list();

        list.addByOrder(hero1);
        list.addByOrder(hero4);
        list.addByOrder(hero2);
        list.addByOrder(hero3);
//        list.addByOrder(hero3);
        list.list();
        System.out.println("**********************");
        //逆序打印链表
        System.out.println("逆序打印链表");
        reversePrint(list.getHead());
        System.out.println("*********************");
        //反转单链表
        reverseList(list.getHead());
        System.out.println("链表反转后：");
        list.list();

        System.out.println("*************");

        System.out.println(findLastIndexNode(list.getHead(),3));

        int len = getLength(list.getHead());
        System.out.println("链表个数为：" + len);
        System.out.println("**********************");
        HeroNode hero5 = new HeroNode(3,"李逵","不知道");
        HeroNode hero6 = new HeroNode(6,"李逵","不知道");
        list.update(hero5);
        list.update(hero6);
        list.list();
        System.out.println("******************");
        list.delete(3);
        list.delete(1);
        list.delete(2);
        list.list();
    }

    //逆序打印链表，利用栈的特点
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode cur = head.next;
        //定义一个栈，用于存放节点数据
        Stack<HeroNode> stack = new Stack<>();
        while(true){
            if(cur == null){
                break;
            }
            stack.push(cur);
            cur = cur.next;
        }

        int size = stack.size();

        for (int i = 0; i < size; i++) {
            System.out.println(stack.pop());
        }
    }

    //反转单链表，难点
    public static void reverseList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，则直接返回
        if(head.next == null || head.next.next == null){
            return;
        }

        //定义一个辅助指针，遍历原来的链表
        HeroNode cur = head.next;
        //定义一个指针，指向当前节点的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历当前链表，每遍历一个节点，将其取出，并放在新的链表reverseHead的最前端
        while(cur != null){
            next = cur.next;

            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        //将head.next 指向reverseHead.next
        head.next = reverseHead.next;

    }

    //获取单链表节点个数（如果带头节点，需求不统计头节点）
    public static int getLength(HeroNode head){
        if(head.next == null){
            System.out.println("空链表");
            return 0;
        }

        int length = 0;
        HeroNode cur = head.next;
        while(cur != null){
            length ++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表中的倒数第k个节点
    //如果找到了返回该节点，没找到返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index){
        if(head.next == null){
            return null;
        }
        //获取总长度
        int size = getLength(head);
        //index校验
        if(index <= 0 || index > size){
            System.out.println("inex不合法");
            return null;
        }
        //查找size-index的节点
       /* int n = 0;
        HeroNode temp = head;
        while(true){
            if(n == (size-index)){
                break;d
            }
            temp = temp.next;
            n++;
        }
        return temp;*/

        HeroNode cur = head.next;
        for (int i = 0; i < size-index; i++) {
            cur = cur.next;
        }
        return cur;
    }
}

class SingleLinkedList{
    //先初始化一个头节点，头节点不动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");
    //private HeroNode endNode = null;

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //当不考虑编号顺序时，
    //1、找到当前链表的最后节点 2、将最后这个节点的next指向新的节点
    public void addNode(HeroNode heroNode){
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true){
            //初始写法有问题
            /*temp = temp.next;
            if(temp.next == null){
                break;
            }*/
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //第二种添加方式：根据排名添加到指定位置
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        //boolean isFlag = false; //标识添加的编号是否存在，存在则添加不了
        while(true){
            if(temp.next == null){
                temp.next = heroNode;
                break;
            }
            if(temp.next.no > heroNode.no){
                heroNode.next = temp.next;
                temp.next = heroNode;
                break;
            }
            if(temp.next.no == heroNode.no){
                System.out.println(heroNode.no + "编号已存在，插入失败");
                break;
            }
            temp = temp.next;
        }
    }

    //修改节点的信息，根据no编号来修改
    //说明：根据newHeroNode的no来修改
    public void update(HeroNode newHeroNode){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("空链表");
            return;
        }
        HeroNode temp = head.next;
        boolean isFlag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == newHeroNode.no){
                isFlag = true;
                break;
            }
            temp = temp.next;
        }
        if(isFlag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.printf("%d编号不存在,修改失败",newHeroNode.no);
            System.out.println();
        }
    }

    //删除节点
    public void delete(int n){
        if(head.next == null){
            System.out.println("为空链表，无法删除");
            return;
        }
        HeroNode temp = head;
        boolean isFlag = false;
        while(true){
            if(temp.next == null){
                break;//没有找到
            }
            if(temp.next.no == n){
                isFlag = true;
                break;
            }
            temp = temp.next;
        }
        if(isFlag){
            temp.next = temp.next.next;
        }else{
            System.out.printf("没有找到编号为%d的节点",n);
            System.out.println();
            return;
        }
    }



    //初始显示链表
    /*public void list(){
        HeroNode temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
            System.out.println(temp);
        }
    }*/

    //显示链表
    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
        }
        //头节点不能动，需要辅助变量来遍历
        HeroNode temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
