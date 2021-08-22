package com.sx.data.array;

public class ArrayTest {
    public static void main(String[] args) {

        testLinkedList();
    }

    public static void testArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            arrayList.add(i);
        }

//        java.util.ArrayList
//        arrayList.add(1,0);
//        final int remove = arrayList.remove(2);
//        System.out.println("删除的是:" + remove);
        System.out.println(arrayList);
    }

    public static void testLinkedList() {
        List<Integer> list = new LinkedList<>();
        list.add(20);
        list.add(0, 10);
        System.out.println("listSize:"+list.size());
        list.add(list.size(), 30);

        list.remove(1);

        System.out.println(list);
    }
}
