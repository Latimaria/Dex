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
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AnnotatedFor({ "nullness" })
public class LinkedBlockingQueue<E extends Object> extends AbstractQueue<E> implements BlockingQueue<E>, java.io.Serializable {

    static class Node<E> {
    }

    void fullyLock();

    void fullyUnlock();

    public LinkedBlockingQueue() {
    }

    public LinkedBlockingQueue(int capacity) {
    }

    public LinkedBlockingQueue(Collection<? extends E> c) {
    }

    @Pure
    public int size();

    public int remainingCapacity();

    public void put(E e) throws InterruptedException;

    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;

    public boolean offer(E e);

    public E take() throws InterruptedException;

    @Nullable
    public E poll(long timeout, TimeUnit unit) throws InterruptedException;

    @Nullable
    public E poll();

    @Nullable
    public E peek();

    void unlink(Node<E> p, Node<E> pred);

    public boolean remove(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    @Pure
    public boolean contains(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    @PolyNull
    @PolySigned
    public Object[] toArray(LinkedBlockingQueue<@PolyNull @PolySigned E> this);

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    public String toString();

    public void clear();

    public int drainTo(Collection<? super E> c);

    public int drainTo(Collection<? super E> c, int maxElements);

    Node<E> succ(Node<E> p);

    public Iterator<E> iterator();

    private class Itr implements Iterator<E> {

        public boolean hasNext();

        public E next();

        public void forEachRemaining(Consumer<? super E> action);

        public void remove();
    }

    private final class LBQSpliterator implements Spliterator<E> {

        public long estimateSize();

        public Spliterator<E> trySplit();

        public boolean tryAdvance(Consumer<? super E> action);

        public void forEachRemaining(Consumer<? super E> action);

        public int characteristics();
    }

    public Spliterator<E> spliterator();

    public void forEach(Consumer<? super E> action);

    void forEachFrom(Consumer<? super E> action, Node<E> p);

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    Node<E> findPred(Node<E> p, Node<E> ancestor);
}
