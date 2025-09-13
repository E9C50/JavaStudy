package algorithm;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 编程题 2: 基于 LRU 缓存淘汰策略的并发缓存
 * 题目：实现一个线程安全的 LRU (Least Recently Used) 缓存。该缓存需要具备以下功能:
 * 能够存储键值对，并支持 get (key) 和 put (key, value) 操作。
 * 当缓存达到最大容量时，需要淘汰最久未使用的键值对。
 * 所有操作都必须是线程安全的。
 * 请编写测试用例，证明其 LRU 淘汰策略和线程安全特性。
 * 考察点:
 * LinkedHashMap 的原理和使用。
 * 如何利用 ConcurrentHashMap 或 ReentrantLock 等工具实现线程安全。
 * 设计合理的测试用例来验证缓存的正确性，包括并发读写、容量限制和淘汰机制。
 */

/**
 * 线程安全的 LRU 缓存实现
 * 使用 ConcurrentHashMap 和双向链表实现 LRU 缓存
 * 使用 ReentrantReadWriteLock 实现读写锁，提升并发性能
 *
 * @param <K> 键类型
 * @param <V> 值类型
 */
public class ConcurrentLRUCache<K, V> {
    private final int capacity;
    private final ConcurrentHashMap<K, Node<K, V>> cache;
    private final DoublyLinkedList<K, V> list;
    private final ReentrantReadWriteLock lock;

    public ConcurrentLRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("容量必须大于0");
        }
        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>();
        this.list = new DoublyLinkedList<>();
        this.lock = new ReentrantReadWriteLock();
    }

    public V get(K key) {
        lock.readLock().lock();
        try {
            Node<K, V> node = cache.get(key);
            if (node == null) {
                return null;
            }
            // 移动到链表头部表示最近使用
            list.moveToHead(node);
            return node.value;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            if (cache.containsKey(key)) {
                // 如果键已存在，更新值并移动到头部
                Node<K, V> node = cache.get(key);
                node.value = value;
                list.moveToHead(node);
            } else {
                // 如果缓存已满，移除最久未使用的元素
                if (cache.size() >= capacity) {
                    Node<K, V> tail = list.removeTail();
                    if (tail != null) {
                        cache.remove(tail.key);
                    }
                }
                // 添加新节点到头部
                Node<K, V> newNode = new Node<>(key, value);
                cache.put(key, newNode);
                list.addToHead(newNode);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
            return cache.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    public boolean containsKey(K key) {
        lock.readLock().lock();
        try {
            return cache.containsKey(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    // 双向链表节点
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // 双向链表实现
    private static class DoublyLinkedList<K, V> {
        private final Node<K, V> head;
        private final Node<K, V> tail;

        DoublyLinkedList() {
            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.next = tail;
            tail.prev = head;
        }

        void addToHead(Node<K, V> node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        void removeNode(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        void moveToHead(Node<K, V> node) {
            removeNode(node);
            addToHead(node);
        }

        Node<K, V> removeTail() {
            if (tail.prev == head) {
                return null; // 链表为空
            }
            Node<K, V> last = tail.prev;
            removeNode(last);
            return last;
        }
    }
}