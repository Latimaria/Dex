/*
 * Copyright (c) 1997, 2019, Oracle and/or its affiliates. All rights reserved.
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
package jdk.javadoc.internal.tool;

import org.checkerframework.dataflow.qual.Pure;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject.Kind;
import com.sun.source.util.DocTrees;
import com.sun.tools.javac.code.Source;
import jdk.javadoc.doclet.DocletEnvironment;

public class DocEnvImpl implements DocletEnvironment {

    public final ElementsTable etable;

    public final ToolEnvironment toolEnv;

    public DocEnvImpl(ToolEnvironment toolEnv, ElementsTable etable) {
    }

    @Override
    public Set<? extends Element> getSpecifiedElements();

    @Override
    public Set<? extends Element> getIncludedElements();

    @Override
    @Pure
    public boolean isIncluded(Element e);

    @Override
    public DocTrees getDocTrees();

    @Override
    public Elements getElementUtils();

    @Override
    public Types getTypeUtils();

    @Override
    public JavaFileManager getJavaFileManager();

    @Override
    public SourceVersion getSourceVersion();

    @Override
    public ModuleMode getModuleMode();

    @Override
    public Kind getFileKind(TypeElement type);

    @Override
    @Pure
    public boolean isSelected(Element e);
}