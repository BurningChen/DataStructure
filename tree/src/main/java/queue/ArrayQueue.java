package queue;

import array.ArrayObject;

/**
 * @program: design
 * @description: Array的队列实现类
 * @author: cyj
 * @create: 2019-03-20 14:16
 **/
public class ArrayQueue<E> implements Queue<E> {
    private ArrayObject<E> array;

    public ArrayQueue(int capacity) {
        array = new ArrayObject<>(capacity);
    }

    public ArrayQueue() {
        array = new ArrayObject<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void enqueue(E e) {
        //入队在队尾入队
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        //时间复杂度为O(n),删除队首元素之后，需要将队列元素往前移一位，数据量一上去就会非常耗时间，因此需要改进
        //下文会对循环队列进行介绍
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}

