package edu.ktu.ds.lab3.demo;

import org.junit.Test;

import edu.ktu.ds.lab3.utils.HashMap;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class HashMapTest {
    Car c0 = new Car("Renault Laguna 2007 50000 1700");
    Car c1 = new Car("Renault Laguna 2007 50000 1700");
    Car c2 = new Car("Renault Megane 2011 20000 3500");
    Car c3 = new Car("Honda   Civic  2017  36400 850.3");
    Car c4 = new Car("Renault Laguna 2011 115900 700");
    Car c5 = new Car("Renault Megane 2001 365100 9500");
    Car c6 = new Car("Honda   Civic  2011  36400 800.3");
    Car c7 = new Car("Renault Laguna 2011 115900 7500");
    Car c8 = new Car("Renault Megane 2002 365100 950");
    Car c9 = new Car("Renault Laguna 2005 50000 700");

    @Test
    public void testRemove_ElementIsNull() {
        HashMap<Integer, Car> map = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> map.remove(null));
    }

    @Test
    public void testRemove_NoSuchKey() {
        HashMap<Integer, Car> map = new HashMap<>();
//        map.put(1, "one");
//        map.put(2, "two");

        map.put(1, c1);
        map.put(2, c2);

        System.out.println(map.toString());

        assertNull(map.remove(3));
        assertEquals(2, map.size());
    }

    @Test
    public void testRemove_RemovesAll() {
        HashMap<Integer, Car> map = new HashMap<>();
//        map.put(1, "one");
//        map.put(2, "two");
//        map.put(3, "three");

        map.put(1, c1);
        map.put(2, c2);
        map.put(3, c3);

        System.out.println(map.toString());

        map.remove(1);
        map.remove(3);

        System.out.println(map.toString());

        map.remove(2);

        assertTrue(map.isEmpty());
    }

    @Test
    public void testRemove() {
        HashMap<Integer, Car> map = new HashMap<>();
//        map.put(1, "one");
//        map.put(2, "two");
//        map.put(3, "three");
//        map.put(5, "four");
//        map.put(4, "five");
//        map.put(1, "six");

        map.put(1, c1);
        map.put(2, c2);
        map.put(3, c3);
        map.put(5, c4);
        map.put(4, c5);
        map.put(1, c6);

        System.out.println(map.toString());

        // Test removing an existing element
        assertEquals(c3, map.remove(3));
        assertFalse(map.contains(3));
        assertEquals(4, map.size());

        System.out.println(map.toString());

        // Test removing the first element
        assertEquals(c6, map.remove(1));
        assertFalse(map.contains(1));

        System.out.println(map.toString());

        assertEquals(c4, map.remove(5));
        assertFalse(map.contains(5));

        System.out.println(map.toString());

        // Test removing a non-existent element
        assertNull(map.remove(6));
    }

    @Test
    public void testReplace_ExistingKeyWithValue() {
        HashMap<Integer, Car> map = new HashMap<>();
//        map.put(1, "old value");

        map.put(1, c0);

        System.out.println(map.toString());

        assertTrue(map.replace(1, c0, c7));
        assertEquals(c7, map.get(1));
    }

    @Test
    public void testReplace_NonExistingKey() {
        HashMap<Integer, Car> map = new HashMap<>();

        assertFalse(map.replace(1, c0, c8));
        assertFalse(map.contains(1));
    }

    @Test
    public void testReplace_WithIncorrectOldValue() {
        HashMap<Integer, Car> map = new HashMap<>();
//        map.put(1, "old value");

        map.put(1, c0);

        System.out.println(map.toString());

        assertFalse(map.replace(1, c7, c8));
        assertEquals(c0, map.get(1));
    }

    @Test
    public void testReplace_MultipleElementsInChain() {
        HashMap<Integer, Car> map = new HashMap<>();
//        map.put(1, "value1");
//        map.put(3, "value3");
//        map.put(11, "value11");

        map.put(1, c0);
        map.put(3, c3);
        map.put(11, c9);

        System.out.println(map.toString());

        assertTrue(map.replace(3, c3, c4));
        assertEquals(c4, map.get(3));
        assertEquals(c9, map.get(11));

        System.out.println(map.toString());
    }

    @Test
    public void testReplace_NullKey() {
        HashMap<Integer, Car> map = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            map.replace(null, c0, c1);
        });
    }

    @Test
    public void testReplace_NullOldValue() {
        HashMap<Integer, Car> map = new HashMap<>();
//        map.put(1, "value");

        map.put(1, c1);

        assertThrows(IllegalArgumentException.class, () -> {
            map.replace(1, null, c0);
        });
    }

    @Test
    public void testReplace_NullNewValue() {
        HashMap<Integer, Car> map = new HashMap<>();
//        map.put(1, "value");

        map.put(1, c1);

        assertThrows(IllegalArgumentException.class, () -> {
            map.replace(1, c1, null);
        });
    }

    @Test
    public void testContainsValue_EmptyMap() {
        HashMap<Integer, Car> map = new HashMap<>();

        assertFalse(map.containsValue(c0));
    }

    @Test
    public void testContainsValue_ValueExists() {
        HashMap<Integer, Car> map = new HashMap<>();
//        map.put(1, "value1");
//        map.put(2, "value2");
//        map.put(3, "value3");
//        map.put(11, "value4");

        map.put(1, c1);
        map.put(2, c2);
        map.put(3, c3);
        map.put(11, c4);

        System.out.println(map.toString());

        assertTrue(map.containsValue(c1));
        assertTrue(map.containsValue(c4));
        assertFalse(map.containsValue(c5));
    }

    @Test
    public void testContainsValue_ValueDoesNotExist() {
        HashMap<Integer, Car> map = new HashMap<>();
//        map.put(1, "value1");
//        map.put(2, "value2");
//        map.put(11, "value4");

        map.put(1, c1);
        map.put(2, c2);
        map.put(11, c4);

        System.out.println(map.toString());

        assertFalse(map.containsValue(c3));
    }

    @Test
    public void testContainsValue_NullValue() {
        HashMap<Integer, Car> map = new HashMap<>();
//        map.put(1, "one");

        map.put(1, c1);

        assertThrows(IllegalArgumentException.class, () -> map.containsValue(null));
    }
}
