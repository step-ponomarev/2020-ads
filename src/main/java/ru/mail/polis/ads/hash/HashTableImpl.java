package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HashTableImpl<K, V> implements HashTable<K, V> {
    private static final float MAX_LOAD_FACTOR = 0.75f;

    private int arraySize;
    private int elementAmount;
    private List<LinkedList<Node<K, V>>> buckets;

    private static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<K, V> node = (Node<K, V>) o;
            return key.equals(node.key) &&
                    value.equals(node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    public HashTableImpl() {
        elementAmount = 0;
        arraySize = 16;
        this.buckets = new ArrayList<LinkedList<Node<K, V>>>(arraySize);
        createBuckets();
    }

    @Nullable
    @Override
    public V get(@NotNull K key) {
        Node<K, V> node = getNodeByKey(key);

        return node == null ? null : node.getValue();
    }

    @Override
    public void put(@NotNull K key, @NotNull V value) {
        List<Node<K, V>> bucket = getBucketByHash(key.hashCode());

        Node<K, V> node = new Node<K, V>(key, value);

        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).getKey().equals(key)) {
                bucket.set(i, node);
                return;
            }
        }

        bucket.add(node);
        elementAmount++;

        if (getLoadFactor() > MAX_LOAD_FACTOR) {
            grow();
        }
    }

    @Nullable
    @Override
    public V remove(@NotNull K key) {
        List<Node<K, V>> bucket = getBucketByHash(key.hashCode());

        if (bucket == null || isEmpty()) {
            return null;
        }

        Node<K, V> removedNode = getNodeByKey(key);

        LinkedList<Node<K, V>> newBucket = bucket.stream()
                .filter(node -> !node.key.equals(key))
                .collect(Collectors.toCollection(LinkedList::new));

        setBucketByHashCode(key.hashCode(), newBucket);

        if (newBucket.size() != bucket.size()) {
            elementAmount--;
        }

        return removedNode == null ? null : removedNode.getValue();
    }

    @Override
    public int size() {
        return elementAmount;
    }

    @Override
    public boolean isEmpty() {
        return elementAmount == 0;
    }

    private Node<K, V> getNodeByKey(@NotNull Object o) {
        List<Node<K, V>> bucket = getBucketByHash(o.hashCode());

        if (bucket == null) {
            return null;
        }

        for (Node<K, V> node : bucket) {
            if (node.getKey().equals(o)) {
                return node;
            }
        }

        return null;
    }

    private List<Node<K, V>> getBucketByHash(int hash) {
        int i = hash % arraySize;

        return i < arraySize ? buckets.get(i) : null;
    }

    private void setBucketByHashCode(int hash, LinkedList<Node<K, V>> newBucket) {
        int i = hash % arraySize;

        buckets.set(i, newBucket);
    }

    private float getLoadFactor() {
        return (float) elementAmount / (float) arraySize;
    }

    private void grow() {
        List<LinkedList<Node<K, V>>> tmp = this.buckets;

        arraySize *= 2;

        this.buckets = new ArrayList<>(arraySize);
        createBuckets();

        elementAmount = 0;

        moveElements(tmp);
    }

    private void createBuckets() {
        for (int i = 0; i < arraySize; i++) {
            this.buckets.add(i, new LinkedList<>());
        }
    }

    private void moveElements(List<? extends List<Node<K, V>>> list) {
        list.forEach(l -> {
            l.forEach(n -> {
                this.put(n.getKey(), n.getValue());
            });
        });
    }
}
