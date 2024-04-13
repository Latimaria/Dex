/*
 * Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.
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
package sun.security.krb5.internal;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import sun.security.krb5.Config;
import sun.security.krb5.PrincipalName;
import sun.security.krb5.KrbException;
import sun.security.krb5.Asn1Exception;
import sun.security.util.*;
import java.net.*;
import java.util.*;
import java.io.IOException;
import sun.security.krb5.internal.ccache.CCacheOutputStream;

public class HostAddresses implements Cloneable {

    public HostAddresses(HostAddress[] new_addresses) throws IOException {
    }

    public HostAddresses() throws UnknownHostException {
    }

    public HostAddresses(PrincipalName serverPrincipal) throws UnknownHostException, KrbException {
    }

    public Object clone();

    public boolean inList(HostAddress addr);

    public int hashCode();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object obj);

    public HostAddresses(DerValue encoding) throws Asn1Exception, IOException {
    }

    public byte[] asn1Encode() throws Asn1Exception, IOException;

    public static HostAddresses parse(DerInputStream data, byte explicitTag, boolean optional) throws Asn1Exception, IOException;

    public void writeAddrs(CCacheOutputStream cos) throws IOException;

    public InetAddress[] getInetAddresses();

    public static HostAddresses getLocalAddresses() throws IOException;

    public HostAddresses(InetAddress[] inetAddresses) {
    }

    @Override
    public String toString();
}
