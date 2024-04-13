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

import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

@AnnotatedFor({ "nullness" })
public interface BlockingDeque<E extends @NonNull Object> extends BlockingQueue<E>, Deque<E> {

    void addFirst(E e);

    void addLast(E e);

    boolean offerFirst(E e);

    boolean offerLast(E e);

    void putFirst(E e) throws InterruptedException;

    void putLast(E e) throws InterruptedException;

    boolean offerFirst(E e, long timeout, TimeUnit unit) throws InterruptedException;

    boolean offerLast(E e, long timeout, TimeUnit unit) throws InterruptedException;

    E takeFirst() throws InterruptedException;

    E takeLast() throws InterruptedException;

    @Nullable
    E pollFirst(long timeout, TimeUnit unit) throws InterruptedException;

    @Nullable
    E pollLast(long timeout, TimeUnit unit) throws InterruptedException;

    boolean removeFirstOccurrence(Object o);

    boolean removeLastOccurrence(Object o);

    boolean add(E e);

    boolean offer(E e);

    void put(E e) throws InterruptedException;

    boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;

    E remove();

    @Nullable
    E poll();

    E take() throws InterruptedException;

    @Nullable
    E poll(long timeout, TimeUnit unit) throws InterruptedException;

    E element();

    @Nullable
    E peek();

    boolean remove(@UnknownSignedness Object o);

    @Pure
    boolean contains(@UnknownSignedness Object o);

    @Pure
    int size();

    @SideEffectFree
    Iterator<E> iterator();

    void push(E e);
}
