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
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Predicate;
import jdk.internal.access.SharedSecrets;
import jdk.internal.util.ArraysSupport;

@AnnotatedFor({ "nullness" })
@SuppressWarnings("unchecked")
public class PriorityBlockingQueue<E extends Object> extends AbstractQueue<E> implements BlockingQueue<E>, java.io.Serializable {

    public PriorityBlockingQueue() {
    }

    public PriorityBlockingQueue(int initialCapacity) {
    }

    public PriorityBlockingQueue(int initialCapacity, Comparator<? super E> comparator) {
    }

    public PriorityBlockingQueue(Collection<? extends E> c) {
    }

    public boolean add(E e);

    public boolean offer(E e);

    public void put(E e);

    public boolean offer(E e, long timeout, TimeUnit unit);

    @Nullable
    public E poll();

    public E take() throws InterruptedException;

    @Nullable
    public E poll(long timeout, TimeUnit unit) throws InterruptedException;

    @Nullable
    public E peek();

    public Comparator<? super E> comparator();

    @Pure
    public int size();

    public int remainingCapacity();

    public boolean remove(@Nullable @UnknownSignedness Object o);

    void removeEq(Object o);

    @Pure
    public boolean contains(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    public String toString();

    public int drainTo(Collection<? super E> c);

    public int drainTo(Collection<? super E> c, int maxElements);

    public void clear();

    @PolyNull
    @PolySigned
    public Object[] toArray(PriorityBlockingQueue<@PolyNull @PolySigned E> this);

    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    public Iterator<E> iterator();

    final class Itr implements Iterator<E> {

        public boolean hasNext();

        public E next();

        public void remove();

        public void forEachRemaining(Consumer<? super E> action);
    }

    final class PBQSpliterator implements Spliterator<E> {

        public PBQSpliterator trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        public boolean tryAdvance(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    public Spliterator<E> spliterator();

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public void forEach(Consumer<? super E> action);
}
