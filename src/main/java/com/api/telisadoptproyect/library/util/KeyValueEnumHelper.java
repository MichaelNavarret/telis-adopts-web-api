package com.api.telisadoptproyect.library.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class holds common Map/List structures based on a key-value based Enum class.
 *
 * The caller can use these structures to support various operations among key, value and the actual Enum.
 *
 * @param <E>
 * @param <T>
 * @param <V>
 */
public final class KeyValueEnumHelper<E extends Enum<E>, T, V> implements Serializable {

    private static final long serialVersionUID = -2825087043076231880L;

    private String simpleEnumClassName;

    private Map<T, E> enumByKeyMap = new LinkedHashMap<>();

    private Map<T, E> enumByCasedKeyMap = new LinkedHashMap<>();

    private boolean usingCasedKey = false;

    private Map<V, E> enumByValueMap = new LinkedHashMap<>();

    private Map<V, E> enumByCasedValueMap = new LinkedHashMap<>();

    private boolean usingCasedValue = false;

    private Map<T, V> keyValueMap = new LinkedHashMap<>();

    private Map<V, T> valueKeyMap = new LinkedHashMap<>();

    private List<T> keyList = new ArrayList<>();

    private Set<T> keySet = new LinkedHashSet<>();

    private List<V> valueList = new ArrayList<>();

    private Set<V> valueSet = new LinkedHashSet<>();

    /**
     * Check if an enum exists for a key.
     *
     * @param key
     * @return
     */
    public boolean containsKey(T key) {

        return enumByKeyMap.containsKey(key);
    }

    /**
     * Check if an enum exists for a value.
     *
     * @param value
     * @return
     */
    public boolean containsValue(V value) {

        return enumByValueMap.containsKey(value);
    }

    /**
     * Look up enum by a key.
     *
     * @param key
     * @return
     */
    public E getEnumByKey(T key) {

        return enumByKeyMap.get(key);
    }

    /**
     * Look up enum by a key.
     *
     * Throw exception if null found.
     *
     * @param key
     * @return
     */
    public E getEnumByKeyWithException(T key) {

        E entry = enumByKeyMap.get(key);
        if (entry == null)
            throw new IllegalArgumentException("No enum found in " + simpleEnumClassName + " for key: " + key);
        return entry;
    }

    /**
     * Look up enum by a value.
     *
     * @param value
     * @return
     */
    public E getEnumByValue(V value) {

        return enumByValueMap.get(value);
    }

    /**
     * Look up enum by a value.
     *
     * Throw exception if null found.
     *
     * @param value
     * @return
     */
    public E getEnumByValueWithException(V value) {

        E entry = enumByValueMap.get(value);
        if (entry == null)
            throw new IllegalArgumentException("No enum found in " + simpleEnumClassName + " for value: " + value);
        return entry;
    }

    /**
     * Look up enum by a key.
     *
     * The key will be set to upper case first.
     *
     * @param key
     * @return
     */
    public E getEnumByNonCasedKey(T key) {

        if (!usingCasedKey || key == null)
            return getEnumByKey(key);
        return enumByCasedKeyMap.get(toUpperCase(key));
    }

    /**
     * Look up enum by a key.
     *
     * The key will be set to upper case first.
     *
     * Throw exception if null found.
     *
     * @param key
     * @return
     */
    public E getEnumByNonCasedKeyWithException(T key) {

        E entry = (!usingCasedKey || key == null) ? getEnumByKey(key)
                : enumByCasedKeyMap.get(toUpperCase(key));
        if (entry == null)
            throw new IllegalArgumentException("No enum found in " + simpleEnumClassName + " for key: " + key);
        return entry;
    }

    /**
     * Look up enum by a value.
     *
     * The value will be set to upper case first.
     *
     * @param value
     * @return
     */
    public E getEnumByNonCasedValue(V value) {

        if (!usingCasedValue || value == null)
            return getEnumByValue(value);
        return enumByCasedValueMap.get(toUpperCase(value));
    }

    /**
     * Look up enum by a value.
     *
     * The value will be set to upper case first.
     *
     * Throw exception if null found.
     *
     * @param value
     * @return
     */
    public E getEnumByNonCasedValueWithException(V value) {

        E entry = (!usingCasedValue || value == null) ? getEnumByValue(value)
                : enumByCasedValueMap.get(toUpperCase(value));
        if (entry == null)
            throw new IllegalArgumentException("No enum found in " + simpleEnumClassName + " for value: " + value);
        return entry;
    }

    /**
     * Return the key by value.
     *
     * @param value
     * @return
     */
    public T getKeyByValue(V value) {

        return valueKeyMap.get(value);
    }

    /**
     * Return the key by value.
     *
     * @param value
     * @return
     */
    public T getKeyByValueWithException(V value) {

        T key = valueKeyMap.get(value);
        if (key == null)
            throw new IllegalArgumentException("No key found in " + simpleEnumClassName + " for value: " + value);
        return key;
    }

    /**
     * Return the value by key.
     *
     * @param key
     * @return
     */
    public V getValueByKey(T key) {

        return keyValueMap.get(key);
    }

    /**
     * Return the enum by key map.
     *
     * @return
     */
    public Map<T, E> getEnumByKeyMap() {

        return enumByKeyMap;
    }

    /**
     * Return the enum by value map.
     *
     * @return
     */
    public Map<V, E> getEnumByValueMap() {

        return enumByValueMap;
    }

    /**
     * Return the key/value map.
     *
     * @return
     */
    public Map<T, V> getKeyValueMap() {

        return keyValueMap;
    }

    /**
     * Return the value/key map.
     *
     * @return
     */
    public Map<V, T> getValueKeyMap() {

        return valueKeyMap;
    }

    /**
     * Return the simple enum class name.
     *
     * @return
     */
    public String getSimpleEnumClassName() {

        return simpleEnumClassName;
    }

    /**
     * Return the enum by key map and the keys are uppercased.
     *
     * @return
     */
    public Map<T, E> getEnumByCasedKeyMap() {

        return enumByCasedKeyMap;
    }

    /**
     * Return the enum by value map and the values are uppercased.
     *
     * @return
     */
    public Map<V, E> getEnumByCasedValueMap() {

        return enumByCasedValueMap;
    }

    /**
     * Return the key list
     *
     * @return
     */
    public List<T> getKeyList() {

        return keyList;
    }

    /**
     * Return the value list.
     *
     * @return
     */
    public List<V> getValueList() {

        return valueList;
    }

    /**
     * Return the key set.
     *
     * @return
     */
    public Set<T> getKeySet() {

        return keySet;
    }

    /**
     * Return the value set.
     *
     * @return
     */
    public Set<V> getValueSet() {

        return valueSet;
    }

    static String toUpperCase(Object key) {

        return ((String) key).toUpperCase();
    }

    /**
     * The purpose of this method is to populate often-used Map/List structures for a key-value based enum class.
     *
     * @param <E>
     * @param <T>
     * @param <V>
     * @param keyMapper
     * @param valueMapper
     * @param enums
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>, T, V> KeyValueEnumHelper<E, T, V>
    adapt(Function<? super E, ? extends T> keyMapper, Function<? super E, ? extends V> valueMapper, E[] enums) {

        KeyValueEnumHelper<E, T, V> adaptor = new KeyValueEnumHelper<>();
        for (E element : enums) {
            T key = keyMapper.apply(element);
            if (adaptor.simpleEnumClassName == null) {
                adaptor.simpleEnumClassName = element.getClass().getSimpleName();
            }
            E existingEnum = adaptor.enumByKeyMap.get(key);
            if (existingEnum != null)
                throw new IllegalStateException("Enums " + existingEnum + ", " + element + " have the same key");
            adaptor.enumByKeyMap.put(key, element);
            V value = valueMapper.apply(element);
            existingEnum = adaptor.enumByValueMap.get(value);
            if (existingEnum != null)
                throw new IllegalStateException("Enums " + existingEnum + ", " + element + " have the same value");
            adaptor.enumByValueMap.put(value, element);
            if (adaptor.keyValueMap.containsKey(key))
                throw new IllegalStateException("Enum " + element + "'s key already exists in keyValueMap");
            adaptor.keyValueMap.put(key, value);
            if (adaptor.valueKeyMap.containsKey(value))
                throw new IllegalStateException("Enum " + element + "'s value already exists in valueKeyMap");
            adaptor.valueKeyMap.put(value, key);
        }
        adaptor.keyList = Collections
                .unmodifiableList(Stream.of(enums).map(keyMapper).collect(Collectors.toList()));
        adaptor.keySet = Collections.unmodifiableSet(new LinkedHashSet<>(adaptor.keyList));
        adaptor.valueList = Collections
                .unmodifiableList(Stream.of(enums).map(valueMapper).collect(Collectors.toList()));
        adaptor.valueSet = Collections.unmodifiableSet(new LinkedHashSet<>(adaptor.valueList));
        //special handling for enumByKeyMap if the map uses String as key
        if (adaptor.enumByKeyMap.keySet().stream().anyMatch(key -> key != null && key instanceof String)) {
            Map<T, E> newMap = new LinkedHashMap<>();
            for (Map.Entry<T, E> entry : adaptor.enumByKeyMap.entrySet()) {
                if (entry.getKey() == null)
                    newMap.put(null, entry.getValue());
                else
                    newMap.put((T) toUpperCase(entry.getKey()), entry.getValue());
            }
            adaptor.usingCasedKey = true;
            adaptor.enumByCasedKeyMap = Collections.unmodifiableMap(newMap);
        }
        //special handling for enumByValueMap if the map uses String as key
        if (adaptor.enumByValueMap.keySet().stream().anyMatch(key -> key != null && key instanceof String)) {
            Map<V, E> newMap = new LinkedHashMap<>();
            for (Map.Entry<V, E> entry : adaptor.enumByValueMap.entrySet()) {
                if (entry.getKey() == null)
                    newMap.put(null, entry.getValue());
                else
                    newMap.put((V) toUpperCase(entry.getKey()), entry.getValue());
            }
            adaptor.usingCasedValue = true;
            adaptor.enumByCasedValueMap = Collections.unmodifiableMap(newMap);
        }
        return adaptor;
    }
}