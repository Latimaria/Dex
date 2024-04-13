/*
 * Copyright (c) 1997, 2015, Oracle and/or its affiliates. All rights reserved.
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
package javax.swing.text;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.io.Serializable;

@SuppressWarnings("serial")
public class TabStop implements Serializable {

    public static final int ALIGN_LEFT;

    public static final int ALIGN_RIGHT;

    public static final int ALIGN_CENTER;

    public static final int ALIGN_DECIMAL;

    public static final int ALIGN_BAR;

    public static final int LEAD_NONE;

    public static final int LEAD_DOTS;

    public static final int LEAD_HYPHENS;

    public static final int LEAD_UNDERLINE;

    public static final int LEAD_THICKLINE;

    public static final int LEAD_EQUALS;

    public TabStop(float pos) {
    }

    public TabStop(float pos, int align, int leader) {
    }

    public float getPosition();

    public int getAlignment();

    public int getLeader();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object other);

    public int hashCode();

    public String toString();
}