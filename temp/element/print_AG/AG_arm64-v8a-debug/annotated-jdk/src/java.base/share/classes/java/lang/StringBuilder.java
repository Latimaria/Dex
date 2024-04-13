/*
 * Copyright (c) 2003, 2020, Oracle and/or its affiliates. All rights reserved.
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
package java.lang;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.regex.qual.PolyRegex;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.vm.annotation.IntrinsicCandidate;
import java.io.IOException;

@AnnotatedFor({ "lock", "nullness", "index", "regex" })
public final class StringBuilder extends AbstractStringBuilder implements java.io.Serializable, Comparable<StringBuilder>, CharSequence {

    @IntrinsicCandidate
    public StringBuilder() {
    }

    @IntrinsicCandidate
    public StringBuilder(@NonNegative int capacity) {
    }

    @IntrinsicCandidate
    public StringBuilder(String str) {
    }

    public StringBuilder(CharSequence seq) {
    }

    @Override
    public int compareTo(StringBuilder another);

    @Override
    public StringBuilder append(@GuardSatisfied @Nullable Object obj);

    @Override
    @IntrinsicCandidate
    public StringBuilder append(@Nullable String str);

    public StringBuilder append(@Nullable StringBuffer sb);

    @Override
    public StringBuilder append(@Nullable CharSequence s);

    @Override
    public StringBuilder append(@Nullable CharSequence s, @IndexOrHigh({ "#1" }) int start, @IndexOrHigh({ "#1" }) int end);

    @Override
    public StringBuilder append(char[] str);

    @Override
    public StringBuilder append(char[] str, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len);

    @Override
    public StringBuilder append(boolean b);

    @Override
    @IntrinsicCandidate
    public StringBuilder append(char c);

    @Override
    @IntrinsicCandidate
    public StringBuilder append(int i);

    @Override
    public StringBuilder append(long lng);

    @Override
    public StringBuilder append(float f);

    @Override
    public StringBuilder append(double d);

    @Override
    public StringBuilder appendCodePoint(int codePoint);

    @Override
    public StringBuilder delete(@NonNegative int start, @NonNegative int end);

    @Override
    public StringBuilder deleteCharAt(@NonNegative int index);

    @Override
    public StringBuilder replace(@NonNegative int start, @NonNegative int end, String str);

    @Override
    public StringBuilder insert(@NonNegative int index, char[] str, @IndexOrHigh({ "#2" }) int offset, @IndexOrHigh({ "#2" }) int len);

    @Override
    public StringBuilder insert(@NonNegative int offset, @GuardSatisfied @Nullable Object obj);

    @Override
    public StringBuilder insert(@NonNegative int offset, @Nullable String str);

    @Override
    public StringBuilder insert(@NonNegative int offset, char[] str);

    @Override
    public StringBuilder insert(@NonNegative int dstOffset, @Nullable CharSequence s);

    @Override
    public StringBuilder insert(@NonNegative int dstOffset, @Nullable CharSequence s, @NonNegative int start, @NonNegative int end);

    @Override
    public StringBuilder insert(@NonNegative int offset, boolean b);

    @Override
    public StringBuilder insert(@NonNegative int offset, char c);

    @Override
    public StringBuilder insert(@NonNegative int offset, int i);

    @Override
    public StringBuilder insert(@NonNegative int offset, long l);

    @Override
    public StringBuilder insert(@NonNegative int offset, float f);

    @Override
    public StringBuilder insert(@NonNegative int offset, double d);

    @Pure
    @Override
    @GTENegativeOne
    public int indexOf(@GuardSatisfied StringBuilder this, String str);

    @Pure
    @Override
    @GTENegativeOne
    public int indexOf(@GuardSatisfied StringBuilder this, String str, int fromIndex);

    @Pure
    @Override
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied StringBuilder this, String str);

    @Pure
    @Override
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied StringBuilder this, String str, int fromIndex);

    @Override
    public StringBuilder reverse();

    @SideEffectFree
    @Override
    @IntrinsicCandidate
    @PolyRegex
    public String toString(@GuardSatisfied @PolyRegex StringBuilder this);
}
