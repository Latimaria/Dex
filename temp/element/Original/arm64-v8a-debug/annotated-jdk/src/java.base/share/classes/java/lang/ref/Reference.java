/*
 * Copyright (c) 1997, 2021, Oracle and/or its affiliates. All rights reserved.
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
package java.lang.ref;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.IntrinsicCandidate;
import jdk.internal.access.JavaLangRefAccess;
import jdk.internal.access.SharedSecrets;
import jdk.internal.ref.Cleaner;

@AnnotatedFor({ "lock", "nullness" })
@SuppressWarnings({ "rawtypes" })
public abstract class Reference<T> {

    private static class ReferenceHandler extends Thread {

        @SuppressWarnings({ "unchecked" })
        public void run();
    }

    @SideEffectFree
    @IntrinsicCandidate
    @Nullable
    public T get(@GuardSatisfied Reference<T> this);

    @Pure
    public final boolean refersTo(T obj);

    @IntrinsicCandidate
    native boolean refersTo0(Object o);

    public void clear();

    T getFromInactiveFinalReference();

    void clearInactiveFinalReference();

    @Deprecated()
    public boolean isEnqueued();

    public boolean enqueue();

    @Override
    protected Object clone() throws CloneNotSupportedException;

    @ForceInline
    @CFComment("nullness: Docs say the parameter can be null, but in practice, calls pass `this`")
    public static void reachabilityFence(Object ref);
}
