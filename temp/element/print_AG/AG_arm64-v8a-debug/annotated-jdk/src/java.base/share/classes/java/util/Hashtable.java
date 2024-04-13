/*
 * Copyright (c) 1994, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.BiFunction;
import jdk.internal.access.SharedSecrets;

@CFComment({ "lock: This collection can only contain nonnull values" })
@AnnotatedFor({ "lock", "nullness", "index" })
public class Hashtable<K extends @NonNull Object, V extends @NonNull Object> extends Dictionary<K, V> implements Map<K, V>, Cloneable, java.io.Serializable {

    public Hashtable(@NonNegative int initialCapacity, float loadFactor) {
    }

    public Hashtable(@NonNegative int initialCapacity) {
    }

    public Hashtable() {
    }

    public Hashtable(Map<? extends K, ? extends V> t) {
    }

    @Pure
    @NonNegative
    public synchronized int size(@GuardSatisfied Hashtable<K, V> this);

    @Pure
    public synchronized boolean isEmpty(@GuardSatisfied Hashtable<K, V> this);

    public synchronized Enumeration<@KeyFor({ "this" }) K> keys();

    public synchronized Enumeration<V> elements();

    @Pure
    public synchronized boolean contains(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied @UnknownSignedness Object value);

    @Pure
    public boolean containsValue(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied @UnknownSignedness Object value);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public synchronized boolean containsKey(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied @UnknownSignedness Object key);

    @Pure
    @SuppressWarnings("unchecked")
    @Nullable
    public synchronized V get(@GuardSatisfied Hashtable<K, V> this, @UnknownSignedness @GuardSatisfied Object key);

    @SuppressWarnings("unchecked")
    protected void rehash();

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public synchronized V put(@GuardSatisfied Hashtable<K, V> this, K key, V value);

    @Nullable
    public synchronized V remove(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied @UnknownSignedness Object key);

    public synchronized void putAll(@GuardSatisfied Hashtable<K, V> this, Map<? extends K, ? extends V> t);

    public synchronized void clear(@GuardSatisfied Hashtable<K, V> this);

    @SideEffectFree
    public synchronized Object clone(@GuardSatisfied Hashtable<K, V> this);

    final Hashtable<?, ?> cloneHashtable();

    public synchronized String toString(@GuardSatisfied Hashtable<K, V> this);

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied Hashtable<K, V> this);

    private class KeySet extends AbstractSet<K> {

        @SideEffectFree
        public Iterator<K> iterator();

        @Pure
        @NonNegative
        public int size();

        @Pure
        public boolean contains(@UnknownSignedness Object o);

        public boolean remove(@UnknownSignedness Object o);

        public void clear();
    }

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied Hashtable<K, V> this);

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @SideEffectFree
        public Iterator<Map.Entry<K, V>> iterator();

        public boolean add(Map.Entry<K, V> o);

        @Pure
        public boolean contains(@UnknownSignedness Object o);

        public boolean remove(@UnknownSignedness Object o);

        @Pure
        @NonNegative
        public int size();

        public void clear();
    }

    @SideEffectFree
    public Collection<V> values(@GuardSatisfied Hashtable<K, V> this);

    private class ValueCollection extends AbstractCollection<V> {

        @SideEffectFree
        public Iterator<V> iterator();

        @Pure
        @NonNegative
        public int size();

        @Pure
        public boolean contains(@UnknownSignedness Object o);

        public void clear();
    }

    @Pure
    public synchronized boolean equals(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied @Nullable Object o);

    @Pure
    public synchronized int hashCode(@GuardSatisfied Hashtable<K, V> this);

    @Override
    @Pure
    public synchronized V getOrDefault(@GuardSatisfied @UnknownSignedness Object key, V defaultValue);

    @SuppressWarnings("unchecked")
    @Override
    public synchronized void forEach(BiConsumer<? super K, ? super V> action);

    @SuppressWarnings("unchecked")
    @Override
    public synchronized void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Override
    public synchronized V putIfAbsent(K key, V value);

    @Override
    public synchronized boolean remove(@GuardSatisfied @UnknownSignedness Object key, @GuardSatisfied @UnknownSignedness Object value);

    @Override
    public synchronized boolean replace(K key, V oldValue, V newValue);

    @Override
    public synchronized V replace(K key, V value);

    @Override
    @PolyNull
    public synchronized V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

    @Override
    @PolyNull
    public synchronized V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

    @Override
    @PolyNull
    public synchronized V compute(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

    @Override
    @PolyNull
    public synchronized V merge(K key, @NonNull V value, BiFunction<? super V, ? super V, ? extends @PolyNull V> remappingFunction);

    void writeHashtable(java.io.ObjectOutputStream s) throws IOException;

    final void defaultWriteHashtable(java.io.ObjectOutputStream s, int length, float loadFactor) throws IOException;

    void readHashtable(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException;

    private static class Entry<K, V> implements Map.Entry<K, V> {

        protected Entry(int hash, K key, V value, Entry<K, V> next) {
        }

        @SideEffectFree
        @SuppressWarnings("unchecked")
        protected Object clone();

        public K getKey();

        public V getValue();

        public V setValue(V value);

        public boolean equals(Object o);

        public int hashCode();

        public String toString();
    }

    private class Enumerator<T> implements Enumeration<T>, Iterator<T> {

        protected int expectedModCount;

        public boolean hasMoreElements();

        @SuppressWarnings("unchecked")
        public T nextElement();

        public boolean hasNext();

        public T next();

        public void remove();
    }
}