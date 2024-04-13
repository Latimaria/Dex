/*
 * Copyright (c) 1997, 2018, Oracle and/or its affiliates. All rights reserved.
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
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@CFComment("lock/nullness: Subclasses of this interface/class may opt to prohibit null elements")
@AnnotatedFor({ "lock", "nullness", "index" })
public interface Collection<E> extends Iterable<E> {

    @Pure
    @NonNegative
    int size(@GuardSatisfied Collection<E> this);

    @Pure
    boolean isEmpty(@GuardSatisfied Collection<E> this);

    @CFComment({ "lock: not true, because map could contain nulls:  AssertParametersNonNull(\"get(#1)\")", "Nullness: `o` is not @Nullable because this collection might forbid null", "(though I think a nicer specification would be to return false in that case)" })
    @Pure
    boolean contains(@GuardSatisfied Collection<E> this, @GuardSatisfied @UnknownSignedness Object o);

    @SideEffectFree
    Iterator<E> iterator();

    @CFComment({ "lock: The Nullness Checker does NOT use these signatures for either version", "of toArray; rather, the checker has hard-coded rules for those two", "methods, because the most useful type for toArray is not expressible", "in the surface syntax that the nullness annotations support." })
    @SideEffectFree
    @PolyNull
    @PolySigned
    Object[] toArray(Collection<@PolyNull @PolySigned E> this);

    @SideEffectFree
    @Nullable
    <T extends @UnknownSignedness Object> T[] toArray(@PolyNull T[] a);

    default <T> T[] toArray(IntFunction<T[]> generator);

    boolean add(@GuardSatisfied Collection<E> this, E e);

    boolean remove(@GuardSatisfied Collection<E> this, @UnknownSignedness Object o);

    @Pure
    boolean containsAll(@GuardSatisfied Collection<E> this, @GuardSatisfied Collection<? extends @UnknownSignedness Object> c);

    boolean addAll(@GuardSatisfied Collection<E> this, Collection<? extends E> c);

    boolean removeAll(@GuardSatisfied Collection<E> this, Collection<? extends @UnknownSignedness Object> c);

    default boolean removeIf(Predicate<? super E> filter);

    boolean retainAll(@GuardSatisfied Collection<E> this, Collection<? extends @UnknownSignedness Object> c);

    void clear(@GuardSatisfied Collection<E> this);

    @Pure
    boolean equals(@GuardSatisfied Collection<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    @Pure
    int hashCode(@GuardSatisfied Collection<E> this);

    @SideEffectFree
    @Override
    default Spliterator<E> spliterator();

    default Stream<E> stream();

    default Stream<E> parallelStream();
}
