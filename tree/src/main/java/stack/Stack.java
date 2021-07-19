package stack;

/**
 * @author cyj
 */
public interface Stack<E> {
    /**
     * 获取栈的长度
     *
     * @return
     */
    int getSize();

    /**
     * 判断栈是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 将一个数压入栈中
     *
     * @param e
     */
    void push(E e);

    /**
     * 移除栈顶元素,返回被删除的元素
     *
     * @return
     */
    E pop();

    /**
     * 获取栈顶元素
     *
     * @return
     */
    E peek();
}

