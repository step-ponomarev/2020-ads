package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

<<<<<<< HEAD
public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    private final double LOAD_FACTOR = 0.75;

    private int elementsNumber;

    private int arrSize = 16;

    private static class Node<Key, Value> {
        private Key key;
        private Value value;
        private Node<Key, Value> next;

        Node(Key key, Value value) {
=======
public class HashTableImpl<K, V> implements HashTable<K, V> {
    private static final float MAX_LOAD_FACTOR = 0.75f;

    private int arraySize;
    private int elementAmount;
    private List<LinkedList<Node<K, V>>> buckets;

    private static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
>>>>>>> 7109ed8f6d6139fb0fe0fab8de8dbbad3dd2a54d
            this.key = key;
            this.value = value;
        }
    }

<<<<<<< HEAD
    private Node<Key, Value>[] nodes = new Node[arrSize];

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int index = findIndex(key.hashCode());

        if (nodes[index] != null) {

            Node<Key, Value> node = nodes[index];
=======
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
>>>>>>> 7109ed8f6d6139fb0fe0fab8de8dbbad3dd2a54d

            while (node != null && !node.key.equals(key)) {
                node = node.next;
            }

            return node == null ? null : node.value;
        }

<<<<<<< HEAD
        return null;
=======
    public HashTableImpl() {
        elementAmount = 0;
        arraySize = 16;
        this.buckets = new ArrayList<LinkedList<Node<K, V>>>(arraySize);
        createBuckets();
>>>>>>> 7109ed8f6d6139fb0fe0fab8de8dbbad3dd2a54d
    }

    @Override
<<<<<<< HEAD
    public void put(@NotNull Key key, @NotNull Value value) {
        int index = findIndex(key.hashCode());
=======
    public V get(@NotNull K key) {
        Node<K, V> node = getNodeByKey(key);
>>>>>>> 7109ed8f6d6139fb0fe0fab8de8dbbad3dd2a54d

        if (nodes[index] == null) {
            nodes[index] = new Node<>(key, value);
        } else {

<<<<<<< HEAD
            Node<Key, Value> node = nodes[index];

            while (node.next != null || node.key.equals(key)) {

                if (node.key.equals(key)) {
                    node.key = key;
                    node.value = value;
                    return;
                }
                node = node.next;
=======
    @Override
    public void put(@NotNull K key, @NotNull V value) {
        List<Node<K, V>> bucket = getBucketByHash(key.hashCode());

        Node<K, V> node = new Node<K, V>(key, value);

        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).getKey().equals(key)) {
                bucket.set(i, node);
                return;
>>>>>>> 7109ed8f6d6139fb0fe0fab8de8dbbad3dd2a54d
            }

            node.next = new Node<>(key, value);
        }
        elementsNumber++;
        resize();
    }

    @Override
<<<<<<< HEAD
    public @Nullable Value remove(@NotNull Key key) {
        int index = findIndex(key.hashCode());
=======
    public V remove(@NotNull K key) {
        List<Node<K, V>> bucket = getBucketByHash(key.hashCode());
>>>>>>> 7109ed8f6d6139fb0fe0fab8de8dbbad3dd2a54d

        if (nodes[index] != null) {
            Node<Key, Value> node = nodes[index];

            while (nodes[index] != null) {

                if (nodes[index].key.equals(key)) {

<<<<<<< HEAD
                    Value valueToReturn = node.value;
                    nodes[index] = nodes[index].next;
                    elementsNumber--;

                    if (!node.key.equals(key)) {
                        nodes[index] = node;
                    }

                    return valueToReturn;
                }

                nodes[index] = nodes[index].next;
            }

=======
        Node<K, V> removedNode = getNodeByKey(key);

        LinkedList<Node<K, V>> newBucket = bucket.stream()
                .filter(node -> !node.key.equals(key))
                .collect(Collectors.toCollection(LinkedList::new));

        setBucketByHashCode(key.hashCode(), newBucket);

        if (newBucket.size() != bucket.size()) {
            elementAmount--;
>>>>>>> 7109ed8f6d6139fb0fe0fab8de8dbbad3dd2a54d
        }

        return null;
    }

    @Override
    public int size() {
        return elementsNumber;
    }

    @Override
    public boolean isEmpty() {
        return elementsNumber == 0;
    }

<<<<<<< HEAD
    private void resize() {
        if ((double) (elementsNumber / arrSize) > LOAD_FACTOR) {
            arrSize *= 2;
            rehash();
=======
    private Node<K, V> getNodeByKey(@NotNull Object o) {
        List<Node<K, V>> bucket = getBucketByHash(o.hashCode());

        if (bucket == null) {
            return null;
        }

        for (Node<K, V> node : bucket) {
            if (node.getKey().equals(o)) {
                return node;
            }
>>>>>>> 7109ed8f6d6139fb0fe0fab8de8dbbad3dd2a54d
        }
    }

<<<<<<< HEAD
    private void rehash() {
        elementsNumber = 0;
        Node<Key, Value>[] tmp = nodes;
        nodes = new Node[arrSize];

        for (var node : tmp) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    private int findIndex(int hashCode) {
        return (hashCode & 0x7fffffff) % arrSize;
=======
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
>>>>>>> 7109ed8f6d6139fb0fe0fab8de8dbbad3dd2a54d
    }
}
