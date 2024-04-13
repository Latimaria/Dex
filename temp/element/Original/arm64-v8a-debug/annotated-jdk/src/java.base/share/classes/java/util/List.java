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

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.UnaryOperator;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
public interface List<E> extends Collection<E> {

    @Pure
    @NonNegative
    int size(@GuardSatisfied List<E> this);

    @Pure
    boolean isEmpty(@GuardSatisfied List<E> this);

    @Pure
    boolean contains(@GuardSatisfied List<E> this, @UnknownSignedness Object o);

    @SideEffectFree
    Iterator<E> iterator();

    @SideEffectFree
    @PolyNull
    Object[] toArray(List<@PolyNull E> this);

    @SideEffectFree
    @Nullable
    <T> T[] toArray(@PolyNull T[] a);

    @ReleasesNoLocks
    boolean add(@GuardSatisfied List<E> this, E e);

    boolean remove(@GuardSatisfied List<E> this, @UnknownSignedness Object o);

    @Pure
    boolean containsAll(@GuardSatisfied List<E> this, Collection<? extends @UnknownSignedness Object> c);

    boolean addAll(@GuardSatisfied List<E> this, Collection<? extends E> c);

    boolean addAll(@GuardSatisfied List<E> this, @IndexOrHigh({ "this" }) int index, Collection<? extends E> c);

    boolean removeAll(@GuardSatisfied List<E> this, Collection<? extends @UnknownSignedness Object> c);

    boolean retainAll(@GuardSatisfied List<E> this, Collection<? extends @UnknownSignedness Object> c);

    default void replaceAll(UnaryOperator<E> operator);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    default void sort(Comparator<? super E> c);

    void clear(@GuardSatisfied List<E> this);

    @Pure
    boolean equals(@GuardSatisfied List<E> this, @Nullable Object o);

    @Pure
    int hashCode(@GuardSatisfied List<E> this);

    @Pure
    E get(@GuardSatisfied List<E> this, @IndexFor({ "this" }) int index);

    E set(@GuardSatisfied List<E> this, @IndexFor({ "this" }) int index, E element);

    @ReleasesNoLocks
    void add(@GuardSatisfied List<E> this, @IndexOrHigh({ "this" }) int index, E element);

    @ReleasesNoLocks
    E remove(@GuardSatisfied List<E> this, @IndexFor({ "this" }) int index);

    @Pure
    @GTENegativeOne
    int indexOf(@GuardSatisfied List<E> this, @GuardSatisfied @UnknownSignedness Object o);

    @Pure
    @GTENegativeOne
    int lastIndexOf(@GuardSatisfied List<E> this, @GuardSatisfied @UnknownSignedness Object o);

    ListIterator<E> listIterator();

    ListIterator<E> listIterator(@IndexOrHigh({ "this" }) int index);

    @SideEffectFree
    List<E> subList(@GuardSatisfied List<E> this, @IndexOrHigh({ "this" }) int fromIndex, @IndexOrHigh({ "this" }) int toIndex);

    @SideEffectFree
    @Override
    default Spliterator<E> spliterator();

    @SuppressWarnings("unchecked")
    static <E> List<E> of();

    static <E extends Object> List<E> of(E e1);

    static <E extends Object> List<E> of(E e1, E e2);

    static <E extends Object> List<E> of(E e1, E e2, E e3);

    static <E extends Object> List<E> of(E e1, E e2, E e3, E e4);

    static <E extends Object> List<E> of(E e1, E e2, E e3, E e4, E e5);

    static <E extends Object> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6);

    static <E extends Object> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7);

    static <E extends Object> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8);

    static <E extends Object> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9);

    static <E extends Object> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10);

    @SafeVarargs
    @SuppressWarnings("varargs")
    static <E extends Object> List<E> of(E... elements);

    static <E extends Object> List<E> copyOf(Collection<? extends E> coll);
}
