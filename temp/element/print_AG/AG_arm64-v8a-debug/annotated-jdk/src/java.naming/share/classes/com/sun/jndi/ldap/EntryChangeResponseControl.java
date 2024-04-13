/*
 * Copyright (c) 1999, 2002, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.jndi.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import java.io.IOException;
import javax.naming.*;
import javax.naming.directory.*;

public final class EntryChangeResponseControl extends BasicControl {

    @Interned
    public static final String OID;

    public static final int ADD;

    public static final int DELETE;

    public static final int MODIFY;

    public static final int RENAME;

    public EntryChangeResponseControl(String id, boolean criticality, byte[] value) throws IOException {
    }

    public int getChangeType();

    public String getPreviousDN();

    public long getChangeNumber();
}
