/*
 * Copyright (c) 2012, 2020, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.optional.qual.Present;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import org.checkerframework.framework.qual.Covariant;
import org.checkerframework.framework.qual.EnsuresQualifier;
import org.checkerframework.framework.qual.EnsuresQualifierIf;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

@CFComment({ "nullness :", "The @NonNull annotation on the class makes the type \"@Nullable Optional<T>\" illegal and enforces", "\"Rule #1: Never, ever, use null for an Optional variable or return value.\" from", "https://stuartmarks.files.wordpress.com/2016/09/optionalmotherofallbikesheds3.pdf, which is", "generally accepted practice.  If you wish to permit the type \"@Nullable Optional\", you may do so", "by writing a stub file that overrides this class in the annotated JDK.", "The type argument to Optional is meaningless.", "Optional<@NonNull String> and Optional<@Nullable String> have the same", "meaning, but are unrelated by the Java type hierarchy.", "@Covariant makes Optional<@NonNull String> a subtype of Optional<@Nullable String>." })
@AnnotatedFor({ "lock", "nullness", "optional" })
@Covariant(0)
@jdk.internal.ValueBased
@NonNull
public final class Optional<T> {

    @SideEffectFree
    public static <T> Optional<T> empty();

    @SideEffectFree
    @Present
    public static <T> Optional<T> of(@NonNull T value);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> ofNullable(@Nullable T value);

    @Pure
    @NonNull
    public T get(@Present Optional<T> this);

    @Pure
    @EnsuresQualifierIf(result = true, expression = "this", qualifier = Present.class)
    public boolean isPresent();

    @Pure
    @EnsuresQualifierIf(result = false, expression = "this", qualifier = Present.class)
    public boolean isEmpty();

    public void ifPresent(Consumer<? super T> action);

    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction);

    public Optional<T> filter(Predicate<? super T> predicate);

    public <U> Optional<U> map(Function<? super T, ? extends @Nullable U> mapper);

    public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper);

    public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier);

    @SideEffectFree
    public Stream<T> stream();

    @Pure
    @PolyNull
    public T orElse(@PolyNull T other);

    @PolyNull
    public T orElseGet(Supplier<? extends @PolyNull T> supplier);

    @Pure
    @EnsuresQualifier(expression = "this", qualifier = Present.class)
    public T orElseThrow(@Present Optional<T> this);

    @EnsuresQualifier(expression = "this", qualifier = Present.class)
    public <X extends Throwable> T orElseThrow(@Present Optional<T> this, Supplier<? extends X> exceptionSupplier) throws X;

    @Override
    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object obj);

    @Pure
    @Override
    public int hashCode();

    @SideEffectFree
    @Override
    public String toString();
}
