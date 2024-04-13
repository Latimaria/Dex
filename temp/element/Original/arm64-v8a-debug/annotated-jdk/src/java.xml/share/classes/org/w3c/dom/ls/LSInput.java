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
package org.w3c.dom.ls;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public interface LSInput {

    @Pure
    public java.io.@Nullable Reader getCharacterStream();

    public void setCharacterStream(java.io.@Nullable Reader characterStream);

    @Pure
    public java.io.@Nullable InputStream getByteStream();

    public void setByteStream(java.io.@Nullable InputStream byteStream);

    @Pure
    @Nullable
    public String getStringData();

    public void setStringData(@Nullable String stringData);

    @Pure
    @Nullable
    public String getSystemId();

    public void setSystemId(@Nullable String systemId);

    @Pure
    @Nullable
    public String getPublicId();

    public void setPublicId(@Nullable String publicId);

    @Pure
    @Nullable
    public String getBaseURI();

    public void setBaseURI(@Nullable String baseURI);

    @Pure
    @Nullable
    public String getEncoding();

    public void setEncoding(@Nullable String encoding);

    @Pure
    public boolean getCertifiedText();

    public void setCertifiedText(boolean certifiedText);
}