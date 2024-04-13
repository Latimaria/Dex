/*
 * Copyright (c) 2009, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
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
package jdk.vm.ci.meta;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.nio.ByteBuffer;

public class PrimitiveConstant implements JavaConstant, SerializableConstant {

    protected PrimitiveConstant(JavaKind kind, long primitive) {
    }

    static PrimitiveConstant forTypeChar(char kind, long i);

    @Override
    public JavaKind getJavaKind();

    @Override
    public boolean isNull();

    @Override
    public boolean isDefaultForKind();

    @Override
    public boolean asBoolean();

    @Override
    public int asInt();

    @Override
    public long asLong();

    @Override
    public float asFloat();

    @Override
    public double asDouble();

    @Override
    public Object asBoxedPrimitive();

    @Override
    public int getSerializedSize();

    @Override
    public void serialize(ByteBuffer buffer);

    @Override
    public int hashCode();

    @Override
    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object o);

    @Override
    public String toString();
}