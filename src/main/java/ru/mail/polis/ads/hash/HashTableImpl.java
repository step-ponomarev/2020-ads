package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HashTableImpl implements HashTable {
    private static final float MAX_LOAD_FACTOR = 0.75f;

    private int arraySize;
    private int elementAmount;
    private List<LinkedList<Node>> buckets;

    private static class Node {
        private Object key;
        private Object value;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
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
        this.buckets = new ArrayList<LinkedList<Node>>(arraySize);
        createBuckets();
    }

    @Nullable
    @Override
    public Object get(@NotNull Object o) {
        Node node = getNodeByKey(o);

        return node == null ? null : node.getValue();
    }

    @Override
    public void put(@NotNull Object o, @NotNull Object o2) {
        List<Node> bucket = getBucketByHash(o.hashCode());

        Node node = new Node(o, o2);

        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).getKey().equals(o)) {
                bucket.add(i, node);
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
    public Object remove(@NotNull Object o) {
        List<Node> bucket = getBucketByHash(o.hashCode());

        if (bucket == null || isEmpty()) {
            return null;
        }

        Node removedNode = getNodeByKey(o);

        bucket.remove(removedNode);

        if (removedNode != null) {
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

    private Node getNodeByKey(@NotNull Object o) {
        List<Node> bucket = getBucketByHash(o.hashCode());

        if (bucket == null) {
            return null;
        }

        for (Node node : bucket) {
            if (node.getKey().equals(o)) {
                return node;
            }
        }

        return null;
    }

    private List<Node> getBucketByHash(int hash) {
        int i = hash % arraySize;

        return i < arraySize ? buckets.get(i) : null;
    }

    private float getLoadFactor() {
        return (float) elementAmount / (float) arraySize;
    }

    private void grow() {
        List<LinkedList<Node>> tmp = this.buckets;

        arraySize *= 2;

        this.buckets = new ArrayList<LinkedList<Node>>(arraySize);
        createBuckets();

        elementAmount = 0;

        moveElements(tmp);
    }

    private void createBuckets() {
        for (int i = 0; i < arraySize; i++) {
            this.buckets.add(i, new LinkedList<Node>());
        }
    }

    private void moveElements(List<? extends List<Node>> list) {
        list.forEach(l -> {
            l.forEach(n -> {
                this.put(n.getKey(), n.getValue());
            });
        });
    }
}
