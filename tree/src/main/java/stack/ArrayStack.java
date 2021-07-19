package stack;

import array.ArrayObject;

/**
 * @program: design
 * @description: 数组栈具体实现类
 * @author: cyj
 * @create: 2019-03-20 10:42
 **/
public class ArrayStack<E> implements Stack<E> {

    ArrayObject<E> array;

    public ArrayStack(int capacity) {
        array = new ArrayObject<>(capacity);
    }

    public ArrayStack() {
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
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
