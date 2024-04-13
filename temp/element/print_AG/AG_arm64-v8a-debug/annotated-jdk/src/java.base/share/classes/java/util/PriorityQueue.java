/*
 * Copyright (c) 2003, 2019, Oracle and/or its affiliates. All rights reserved.
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
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.Consumer;
import java.util.function.Predicate;
import jdk.internal.access.SharedSecrets;
import jdk.internal.util.ArraysSupport;

@CFComment({ "lock/nullness: This class doesn't permits null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
@SuppressWarnings("unchecked")
public class PriorityQueue<E extends @NonNull Object> extends AbstractQueue<E> implements java.io.Serializable {

    public PriorityQueue() {
    }

    public PriorityQueue(@Positive int initialCapacity) {
    }

    public PriorityQueue(Comparator<? super E> comparator) {
    }

    public PriorityQueue(@Positive int initialCapacity, Comparator<? super E> comparator) {
    }

    public PriorityQueue(Collection<? extends E> c) {
    }

    public PriorityQueue(PriorityQueue<? extends E> c) {
    }

    public PriorityQueue(SortedSet<? extends E> c) {
    }

    public boolean add(@GuardSatisfied PriorityQueue<E> this, E e);

    public boolean offer(E e);

    @Nullable
    public E peek(@GuardSatisfied PriorityQueue<E> this);

    public boolean remove(@GuardSatisfied PriorityQueue<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    void removeEq(Object o);

    @Pure
    public boolean contains(@GuardSatisfied PriorityQueue<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    @SideEffectFree
    @PolyNull
    @PolySigned
    public Object[] toArray(PriorityQueue<@PolyNull @PolySigned E> this);

    @SideEffectFree
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    @SideEffectFree
    public Iterator<E> iterator();

    private final class Itr implements Iterator<E> {

        public boolean hasNext();

        public E next();

        public void remove();
    }

    @Pure
    @NonNegative
    public int size(@GuardSatisfied PriorityQueue<E> this);

    public void clear(@GuardSatisfied PriorityQueue<E> this);

    @Nullable
    public E poll(@GuardSatisfied PriorityQueue<E> this);

    E removeAt(int i);

    @Pure
    @Nullable
    public Comparator<? super E> comparator(@GuardSatisfied PriorityQueue<E> this);

    public final Spliterator<E> spliterator();

    final class PriorityQueueSpliterator implements Spliterator<E> {

        public PriorityQueueSpliterator trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        public boolean tryAdvance(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<? extends @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @UnknownSignedness Object> c);

    public void forEach(Consumer<? super E> action);
}
