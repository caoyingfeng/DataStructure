package linkedlist;

import java.net.HttpRetryException;

/**
 * @author cy
 * @create 2019-08-31 18:48
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试");
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        /*doubleLinkedList.addNode(hero1);
        doubleLinkedList.addNode(hero2);
        doubleLinkedList.addNode(hero3);
        doubleLinkedList.addNode(hero4);
        doubleLinkedList.list();

        HeroNode2 hero5 = new HeroNode2(4, "公孙胜", "入云龙");
        //修改节点
        doubleLinkedList.update(hero5);
        System.out.println("修改节点后：");
        doubleLinkedList.list();
        System.out.println("删除节点后：");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();
*/
        //测试双向链表按编号添加
        doubleLinkedList.addNodeByOrder(hero3);
        doubleLinkedList.addNodeByOrder(hero2);
        doubleLinkedList.addNodeByOrder(hero1);
        doubleLinkedList.addNodeByOrder(hero4);
        doubleLinkedList.addNodeByOrder(hero4);
        System.out.println("测试第二种添加方式");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList{
    //先初始化一个头节点，头节点不动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");
    //private HeroNode endNode = null;

    public HeroNode2 getHead() {
        return head;
    }

    //显示链表
    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
        }
        //头节点不能动，需要辅助变量来遍历
        HeroNode2 temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加节点到单向链表
    //当不考虑编号顺序时，
    //1、找到当前链表的最后节点 2、将最后这个节点的next指向新的节点
    public void addNode(HeroNode2 heroNode){
        HeroNode2 temp = head;
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
        heroNode.prev = temp;
    }

    //根据排名添加
    public void addNodeByOrder(HeroNode2 heroNode){

        HeroNode2 temp = head;
        while(true){
            if(temp.next == null){
                temp.next = heroNode;
                heroNode.prev = temp;
                return;
            }
            if(temp.next.no > heroNode.no){
                break;
            }
            if(temp.next.no == heroNode.no){
                System.out.println("此编号已存在，添加失败");
                return;
            }
            temp = temp.next;
        }
        temp.next.prev = heroNode;
        heroNode.next = temp.next;
        temp.next = heroNode;
        heroNode.prev = temp;
    }
    //修改节点的信息，根据no编号来修改
    //说明：根据newHeroNode的no来修改
    public void update(HeroNode2 newHeroNode){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("空链表");
            return;
        }
        HeroNode2 temp = head.next;
        boolean isFlag = false; //判断是否为查找的节点
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


    //根据编号删除节点
    public void delete(int n){
        if(head.next == null){
            System.out.println("为空链表，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean isFlag = false;
        while(true){
            if(temp == null){
                break;//没有找到
            }
            if(temp.no == n){
                isFlag = true;
                break;
            }
            temp = temp.next;
        }
        if(isFlag){
            temp.prev.next = temp.next;
            //如果temp是最后一个节点，则temp.next.prev会出现空指针异常
            if(temp.next != null){
                temp.next.prev = temp.prev;
            }

        }else{
            System.out.printf("没有找到编号为%d的节点",n);
            System.out.println();
            return;
        }
    }
}

class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 prev; //指向前一个节点，默认为null

    public HeroNode2(int no, String name, String nickname) {
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
