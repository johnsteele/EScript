/*******************************************************************************
 * Copyright (c) 2013 Christian Pontesegger and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Christian Pontesegger - initial API and implementation
 *******************************************************************************/
package com.codeandme.scripting.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.codeandme.scripting.IScriptEngine;
import com.codeandme.scripting.IScriptEngineProvider;

/**
 * Run macro command. Invoke a given macro.
 */
public class Run extends AbstractHandler implements IHandler {

    public static final String COMMAND_ID = "com.codeandme.commands.macro.run";
    public static final String PARAMETER_NAME = "com.codeandme.commands.macro.run.name";

    @Override
    public final Object execute(final ExecutionEvent event) throws ExecutionException {

        final IWorkbenchPart part = HandlerUtil.getActivePart(event);
        if (part instanceof IScriptEngineProvider) {
            final IScriptEngine scriptEngine = ((IScriptEngineProvider) part).getScriptEngine();
            if (scriptEngine != null) {
                final String[] macros = event.getParameter(PARAMETER_NAME).split(";");

                for (final String macroID : macros)
                    scriptEngine.executeAsync("include(\"macro://" + macroID + "\");");
            }
        }

        return null;
    }
}
