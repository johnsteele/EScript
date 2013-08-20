package com.codeandme.scripting.ui.console.actions;

import java.util.ArrayList;

import org.eclipse.debug.internal.ui.DebugPluginImages;
import org.eclipse.debug.internal.ui.IDebugHelpContextIds;
import org.eclipse.debug.internal.ui.IInternalDebugUIConstants;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;

import com.codeandme.scripting.ui.console.ScriptConsole;

/**
 * ConsoleRemoveAllTerminatedAction
 */
public class ConsoleRemoveAllTerminatedAction extends Action {

    @Override
    public void run() {
        IConsole[] consoles = ConsolePlugin.getDefault().getConsoleManager().getConsoles();
        ArrayList<IConsole> candidates = new ArrayList<IConsole>();
        for (IConsole console : consoles) {
            if (console instanceof ScriptConsole) {
                if (((ScriptConsole) console).getScriptEngine() == null)
                    candidates.add(console);
            }
        }

        ConsolePlugin.getDefault().getConsoleManager().removeConsoles(candidates.toArray(new IConsole[candidates.size()]));
    }

    public ConsoleRemoveAllTerminatedAction() {
        super(ConsoleMessages.ConsoleRemoveAllTerminatedAction_0);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IDebugHelpContextIds.CONSOLE_REMOVE_ALL_TERMINATED);
        setToolTipText(ConsoleMessages.ConsoleRemoveAllTerminatedAction_1);
        setImageDescriptor(DebugPluginImages.getImageDescriptor(IDebugUIConstants.IMG_LCL_REMOVE_ALL));
        setDisabledImageDescriptor(DebugPluginImages.getImageDescriptor(IInternalDebugUIConstants.IMG_DLCL_REMOVE_ALL));
        setHoverImageDescriptor(DebugPluginImages.getImageDescriptor(IDebugUIConstants.IMG_LCL_REMOVE_ALL));

        // TODO add disablement when no more terminated consoles are open, best using the new eventBroker
        setEnabled(true);
    }
}