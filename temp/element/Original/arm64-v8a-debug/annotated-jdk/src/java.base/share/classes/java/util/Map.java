/*
 * Copyright (c) 1997, 2020, Oracle and/or its affiliates. All rights reserved.
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
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
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
import org.checkerframework.framework.qual.Covariant;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.io.Serializable;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
public interface Map<K, V> {

    @Pure
    @NonNegative
    int size(@GuardSatisfied Map<K, V> this);

    @Pure
    boolean isEmpty(@GuardSatisfied Map<K, V> this);

    @CFComment("nullness: key is not @Nullable because this map might not permit null values")
    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    boolean containsKey(@GuardSatisfied Map<K, V> this, @GuardSatisfied @UnknownSignedness Object key);

    @Pure
    boolean containsValue(@GuardSatisfied Map<K, V> this, @GuardSatisfied @UnknownSignedness Object value);

    @Pure
    @Nullable
    V get(@GuardSatisfied Map<K, V> this, @UnknownSignedness @GuardSatisfied Object key);

    @ReleasesNoLocks
    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    V put(@GuardSatisfied Map<K, V> this, K key, V value);

    @CFComment("nullness: key is not @Nullable because this map might not permit null values")
    @Nullable
    V remove(@GuardSatisfied Map<K, V> this, @GuardSatisfied @UnknownSignedness Object key);

    void putAll(@GuardSatisfied Map<K, V> this, Map<? extends K, ? extends V> m);

    void clear(@GuardSatisfied Map<K, V> this);

    @SideEffectFree
    Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied Map<K, V> this);

    @SideEffectFree
    Collection<V> values(@GuardSatisfied Map<K, V> this);

    @SideEffectFree
    Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied Map<K, V> this);

    @Covariant({ 0 })
    interface Entry<K, V> {

        @Pure
        K getKey(Map.@GuardSatisfied Entry<K, V> this);

        @Pure
        V getValue(Map.@GuardSatisfied Entry<K, V> this);

        V setValue(Map.@GuardSatisfied Entry<K, V> this, V value);

        @Pure
        boolean equals(Map.@GuardSatisfied Entry<K, V> this, @GuardSatisfied @Nullable Object o);

        @Pure
        int hashCode(Map.@GuardSatisfied Entry<K, V> this);

        @Pure
        public static <K extends Comparable<? super K>, V> Comparator<Map.Entry<K, V>> comparingByKey();

        @Pure
        public static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K, V>> comparingByValue();

        @Pure
        public static <K, V> Comparator<Map.Entry<K, V>> comparingByKey(Comparator<? super K> cmp);

        @Pure
        public static <K, V> Comparator<Map.Entry<K, V>> comparingByValue(Comparator<? super V> cmp);

        @SuppressWarnings("unchecked")
        public static <K, V> Map.Entry<K, V> copyOf(Map.Entry<? extends K, ? extends V> e);
    }

    boolean equals(@GuardSatisfied Map<K, V> this, @GuardSatisfied @Nullable Object o);

    int hashCode(@GuardSatisfied Map<K, V> this);

    @Pure
    default V getOrDefault(@GuardSatisfied @UnknownSignedness Object key, V defaultValue);

    default void forEach(BiConsumer<? super K, ? super V> action);

    default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    default V putIfAbsent(K key, V value);

    @CFComment("nullness: key and value are not @Nullable because this map might not permit null values")
    default boolean remove(@GuardSatisfied @UnknownSignedness Object key, @GuardSatisfied @UnknownSignedness Object value);

    default boolean replace(K key, V oldValue, V newValue);

    @Nullable
    default V replace(K key, V value);

    @PolyNull
    default V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

    @PolyNull
    default V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

    @PolyNull
    default V compute(K key, BiFunction<? super K, ? super @Nullable V, ? extends @PolyNull V> remappingFunction);

    @CFComment({ "It would be more flexible to make the return type of remappingFunction be `@Nullable V`.  A", "remappingFunction that returns null is is probably rare, and these annotations accommodate", "the majority of uses that don't return null." })
    @PolyNull
    default V merge(K key, @NonNull V value, BiFunction<? super V, ? super V, ? extends @PolyNull V> remappingFunction);

    @SuppressWarnings("unchecked")
    static <K, V> Map<K, V> of();

    static <K extends Object, V extends Object> Map<K, V> of(K k1, V v1);

    static <K extends Object, V extends Object> Map<K, V> of(K k1, V v1, K k2, V v2);

    static <K extends Object, V extends Object> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3);

    static <K extends Object, V extends Object> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4);

    static <K extends Object, V extends Object> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5);

    static <K extends Object, V extends Object> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6);

    static <K extends Object, V extends Object> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7);

    static <K extends Object, V extends Object> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8);

    static <K extends Object, V extends Object> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9);

    static <K extends Object, V extends Object> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10);

    @SafeVarargs
    @SuppressWarnings("varargs")
    static <K extends Object, V extends Object> Map<K, V> ofEntries(Entry<? extends K, ? extends V>... entries);

    static <K, V> Entry<K, V> entry(K k, V v);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    static <K, V> Map<K, V> copyOf(Map<? extends K, ? extends V> map);
}