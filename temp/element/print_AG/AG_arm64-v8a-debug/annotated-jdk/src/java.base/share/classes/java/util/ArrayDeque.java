/*
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
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import jdk.internal.access.SharedSecrets;

@AnnotatedFor({ "lock", "nullness", "index" })
public class ArrayDeque<E extends @NonNull Object> extends AbstractCollection<E> implements Deque<E>, Cloneable, Serializable {

    public ArrayDeque() {
    }

    public ArrayDeque(@NonNegative int numElements) {
    }

    public ArrayDeque(Collection<? extends E> c) {
    }

    static final int inc(int i, int modulus);

    static final int dec(int i, int modulus);

    static final int inc(int i, int distance, int modulus);

    static final int sub(int i, int j, int modulus);

    @SuppressWarnings("unchecked")
    @Pure
    static final <E> E elementAt(@PolyNull @PolySigned Object[] es, int i);

    static final <E> E nonNullElementAt(@PolyNull @PolySigned Object[] es, int i);

    public void addFirst(@GuardSatisfied ArrayDeque<E> this, E e);

    public void addLast(@GuardSatisfied ArrayDeque<E> this, E e);

    public boolean addAll(Collection<? extends E> c);

    public boolean offerFirst(E e);

    public boolean offerLast(E e);

    public E removeFirst(@GuardSatisfied ArrayDeque<E> this);

    public E removeLast(@GuardSatisfied ArrayDeque<E> this);

    @Nullable
    public E pollFirst(@GuardSatisfied ArrayDeque<E> this);

    @Nullable
    public E pollLast(@GuardSatisfied ArrayDeque<E> this);

    public E getFirst(@GuardSatisfied ArrayDeque<E> this);

    public E getLast(@GuardSatisfied ArrayDeque<E> this);

    @Pure
    @Nullable
    public E peekFirst();

    @Nullable
    public E peekLast();

    public boolean removeFirstOccurrence(@GuardSatisfied ArrayDeque<E> this, @Nullable Object o);

    public boolean removeLastOccurrence(@GuardSatisfied ArrayDeque<E> this, @Nullable Object o);

    public boolean add(@GuardSatisfied ArrayDeque<E> this, E e);

    public boolean offer(@GuardSatisfied ArrayDeque<E> this, E e);

    public E remove(@GuardSatisfied ArrayDeque<E> this);

    @Nullable
    public E poll(@GuardSatisfied ArrayDeque<E> this);

    public E element();

    @Nullable
    public E peek();

    public void push(@GuardSatisfied ArrayDeque<E> this, E e);

    public E pop(@GuardSatisfied ArrayDeque<E> this);

    boolean delete(int i);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied ArrayDeque<E> this);

    @EnsuresNonNullIf(expression = { "peek()", "peekFirst()", "peekLast()", "poll()", "pollFirst()", "pollLast()" }, result = false)
    @Pure
    public boolean isEmpty(@GuardSatisfied ArrayDeque<E> this);

    @SideEffectFree
    public Iterator<E> iterator();

    public Iterator<E> descendingIterator();

    private class DeqIterator implements Iterator<E> {

        public final boolean hasNext();

        public E next();

        void postDelete(boolean leftShifted);

        public final void remove();

        public void forEachRemaining(Consumer<? super E> action);
    }

    private class DescendingIterator extends DeqIterator {

        public final E next();

        void postDelete(boolean leftShifted);

        public final void forEachRemaining(Consumer<? super E> action);
    }

    public Spliterator<E> spliterator();

    final class DeqSpliterator implements Spliterator<E> {

        public DeqSpliterator trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        public boolean tryAdvance(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    public void forEach(Consumer<? super E> action);

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<? extends @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @UnknownSignedness Object> c);

    @Pure
    public boolean contains(@GuardSatisfied ArrayDeque<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    public boolean remove(@GuardSatisfied ArrayDeque<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    public void clear(@GuardSatisfied ArrayDeque<E> this);

    @SideEffectFree
    @PolyNull
    @PolySigned
    public Object[] toArray(ArrayDeque<@PolyNull @PolySigned E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    @SideEffectFree
    public ArrayDeque<E> clone(@GuardSatisfied ArrayDeque<E> this);

    void checkInvariants();
}
