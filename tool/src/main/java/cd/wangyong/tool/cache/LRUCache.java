package cd.wangyong.tool.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author andy
 * @since 2020/11/10
 */
public class LRUCache {

    /**
     * 双向链表结点定义，用于实现LRU策略
     */
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode post;
    }

    /**
     * Map实现kv缓存
     */
    private Map<Integer, DLinkedNode> cache = new ConcurrentHashMap<>();

    private int count; // 缓存元素个数
    private int capacity; // 缓存容量
    private DLinkedNode head, tail; // 头尾指针

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.post = null;

        head.post = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if(node == null) return -1; // should raise exception here.

        moveToHead(node); // 由于命中了，所以需要移到头部
        return node.value;
    }

    public void put(int key, int value) {
        // 如果存在，则更新缓存，并维护好双向链表头部
        DLinkedNode node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
            return;
        }

        // 构造结点并插入
        DLinkedNode newNode = new DLinkedNode();
        newNode.key = key;
        newNode.value = value;

        // 缓存中添加结点
        cache.put(key, newNode);
        // 双向链表中插入结点
        addNode(newNode);
        ++count;

        // 执行淘汰，将最久未使用的结点删除
        if(count > capacity){
            // pop the tail
            DLinkedNode tail = popTail();
            cache.remove(tail.key);
            --count;
        }
    }

    /**
     * 辅助函数：添加结点
     */
    private void addNode(DLinkedNode node){
        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    /**
     * 辅助函数：删除结点
     */
    private void removeNode(DLinkedNode node){
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;

        pre.post = post;
        post.pre = pre;
    }

    /**
     * 移动到头部
     */
    private void moveToHead(DLinkedNode node){
        removeNode(node);
        addNode(node);
    }

    /**
     * 从尾部删除
     */
    private DLinkedNode popTail(){
        DLinkedNode res = tail.pre;
        removeNode(res);
        return res;
    }
}
