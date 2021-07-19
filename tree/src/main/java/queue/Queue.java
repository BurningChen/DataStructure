package queue;

/**
 * @author cyj
 */
public interface Queue<E> {
    /**
     * 获取队列的大小
     *
     * @return
     */
    int getSize();

    /**
     * 判断队列是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 将一个数添加入队尾中
     *
     * @param e
     */
    void enqueue(E e);

    /**
     * 去除队首元素,返回被删除的元素
     *
     * @return
     */
    E dequeue();

    /**
     * 获取队列元素
     *
     * @return
     */
    E getFront();
}
