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
package java.util.concurrent;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AnnotatedFor({ "nullness" })
public class ConcurrentLinkedQueue<E extends @NonNull Object> extends AbstractQueue<E> implements Queue<E>, java.io.Serializable {

    static final class Node<E> {

        void appendRelaxed(Node<E> next);

        boolean casItem(E cmp, E val);
    }

    public ConcurrentLinkedQueue() {
    }

    public ConcurrentLinkedQueue(Collection<? extends E> c) {
    }

    public boolean add(E e);

    final void updateHead(Node<E> h, Node<E> p);

    final Node<E> succ(Node<E> p);

    public boolean offer(E e);

    @Nullable
    public E poll();

    @Nullable
    public E peek();

    @Nullable
    Node<E> first();

    @Pure
    public boolean isEmpty();

    @Pure
    public int size();

    @Pure
    public boolean contains(@GuardSatisfied @UnknownSignedness Object o);

    public boolean remove(@GuardSatisfied @UnknownSignedness Object o);

    public boolean addAll(Collection<? extends E> c);

    public String toString();

    @SideEffectFree
    @PolyNull
    @PolySigned
    public Object[] toArray(ConcurrentLinkedQueue<@PolyNull @PolySigned E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    @SideEffectFree
    public Iterator<E> iterator();

    private class Itr implements Iterator<E> {

        public boolean hasNext();

        public E next();

        public void remove();
    }

    final class CLQSpliterator implements Spliterator<E> {

        public Spliterator<E> trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        public boolean tryAdvance(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    @SideEffectFree
    @Override
    public Spliterator<E> spliterator();

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public void clear();

    void forEachFrom(Consumer<? super E> action, Node<E> p);

    public void forEach(Consumer<? super E> action);
}
