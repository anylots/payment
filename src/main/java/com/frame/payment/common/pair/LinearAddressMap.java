package com.frame.payment.common.pair;

import java.io.Serializable;

/**
 * linear address map
 *
 * @author anylots
 * @version $Id: LinearAddressMap.java, v 0.1 2020年10月31日 11:14 anylots Exp $
 */

public class LinearAddressMap<K, V> implements Cloneable, Serializable {

    private static final long serialVersionUID = -540665719961325636L;

    /*** 初始容量 -- must be a power of two.*/
    private static final int INITIAL_CAPACITY = 16;

    /**
     * 一维数组
     */
    private Entry[] array;

    /**
     * 数组扩容阈值
     */
    private int threshold;

    /**
     * 已使用空间
     */
    private int usedSize = 0;

    /**
     * 初始化构造方法
     *
     * @param capacity
     */
    public LinearAddressMap(int capacity) {
        if (capacity == 0) {
            capacity = INITIAL_CAPACITY;
        }
        array = new Entry[capacity];
        setThreshold(capacity);
    }

    /**
     * put operation
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("LinearAddressMap does not support null key");
        }
        Entry[] internalArray = array;
        int len = internalArray.length;
        int i = key.hashCode() & (len - 1);
        Entry e = internalArray[i];

        if (e == null) {
            //位置为空或key相等，替换旧值
            internalArray[i] = new Entry((String) key, value);
            updateUsedSpace();
            return;
        }

        if (e.getKey().equals(key)) {
            //位置为空或key相等，替换旧值
            internalArray[i] = new Entry((String) key, value);
            return;
        }


        //出现hash冲突，从冲突位置开始，查找下一个空位置
        for (int index = i; i < len; index = nextIndex(index, len)) {
            if (internalArray[index] == null) {
                internalArray[index] = new Entry((String) key, value);
                break;
            }
        }
        updateUsedSpace();
    }

    /**
     * update used space
     */
    private void updateUsedSpace() {
        //已使用空间+1
        int currentSize = ++usedSize;// Use lower threshold for doubling to avoid hysteresis

        if (currentSize >= threshold) {
            //达到扩容阈值，容量扩大x2
            arrayResize();
        }
    }

    /**
     * get operation
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("LinearAddressMap does not support null key");
        }
        int i = key.hashCode() & (array.length - 1);
        Entry e = array[i];
        if (e != null && e.getKey().equals(key)) {
            return e.getValue();
        } else {
            return getEntryAfterMiss(key, i, e);
        }
    }

    /**
     * get entry after miss
     *
     * @param key
     * @param i
     * @param e
     * @return
     */
    private Object getEntryAfterMiss(String key, int i, Entry e) {
        Entry[] tab = array;
        int len = tab.length;
        while (e != null) {
            String k = e.getKey();
            if (k.equals(key)) return e.getValue();
            else
                i = nextIndex(i, len);
            e = tab[i];
        }
        return null;
    }

    /*** Increment i modulo len.*/
    private static int nextIndex(int i, int len) {
        return ((i + 1 < len) ? i + 1 : 0);
    }

    /*** Set the resize threshold to maintain at worst a 2/3 load factor.*/
    private void setThreshold(int len) {
        threshold = len * 1 / 2;
    }


    /**
     * array resize,Double the capacity of the table
     */
    private void arrayResize() {
        Entry[] oldArray = array;
        int oldLen = oldArray.length;
        int newLen = oldLen * 2;
        //定义新的数组
        Entry[] newArray = new Entry[newLen];
        int count = 0;
        //遍历旧数组
        for (int j = 0; j < oldLen; ++j) {
            Entry e = oldArray[j];
            if (e != null) {
                String k = e.getKey();
                int h = k.hashCode() & (newLen - 1);
                //放入新数组空位置
                while (newArray[h] != null) {
                    h = nextIndex(h, newLen);
                }
                newArray[h] = e;
                count++;
            }
        }
        //更新数组使用空间、扩容阈值
        setThreshold(newLen);
        usedSize = count;
        array = newArray;
    }
}
