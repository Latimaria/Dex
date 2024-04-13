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
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LinkedTransferQueue<E> extends AbstractQueue<E> implements TransferQueue<E>, java.io.Serializable {

    static final class Node implements ForkJoinPool.ManagedBlocker {

        final boolean casNext(Node cmp, Node val);

        final boolean casItem(Object cmp, Object val);

        final void selfLink();

        final void appendRelaxed(Node next);

        final boolean isMatched();

        final boolean tryMatch(Object cmp, Object val);

        final boolean cannotPrecede(boolean haveData);

        public final boolean isReleasable();

        public final boolean block();
    }

    final Node firstDataNode();

    public String toString();

    @PolyNull
    @PolySigned
    public Object[] toArray(LinkedTransferQueue<@PolyNull @PolySigned E> this);

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    final class Itr implements Iterator<E> {

        public final boolean hasNext();

        public final E next();

        public void forEachRemaining(Consumer<? super E> action);

        public final void remove();
    }

    final class LTQSpliterator implements Spliterator<E> {

        public Spliterator<E> trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        @SuppressWarnings("unchecked")
        public boolean tryAdvance(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    public Spliterator<E> spliterator();

    final void unsplice(Node pred, Node s);

    public LinkedTransferQueue() {
    }

    public LinkedTransferQueue(Collection<? extends E> c) {
    }

    public void put(E e);

    public boolean offer(E e, long timeout, TimeUnit unit);

    public boolean offer(E e);

    public boolean add(E e);

    public boolean tryTransfer(E e);

    public void transfer(E e) throws InterruptedException;

    public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException;

    public E take() throws InterruptedException;

    public E poll(long timeout, TimeUnit unit) throws InterruptedException;

    public E poll();

    public int drainTo(Collection<? super E> c);

    public int drainTo(Collection<? super E> c, int maxElements);

    public Iterator<E> iterator();

    public E peek();

    @Pure
    public boolean isEmpty();

    public boolean hasWaitingConsumer();

    @Pure
    public int size();

    public int getWaitingConsumerCount();

    public boolean remove(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    @Pure
    public boolean contains(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    public int remainingCapacity();

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public void clear();

    @SuppressWarnings("unchecked")
    void forEachFrom(Consumer<? super E> action, Node p);

    public void forEach(Consumer<? super E> action);
}
