/*******************************************************************************
 * Copyright (c) 2011 Infineon Technologies Austria AG
 *
 * Contributors:
 *     Christian Pontesegger - initial version
 *     
 * Version Control:
 *     Last edited by: $Author: pontesegger $
 *     Date:           $Date: 2011-02-18 14:27:56 +0100 (Fr, 18 Feb 2011) $
 *     Revision:       $Revision: 121 $
 *     Head URL:       $URL: https://grzw2b4ph2j.eu.infineon.com/svn/Eclipse_RCP/trunk/bundles/com.infineon.javascript/src/com/infineon/javascript/modules/IJavaScriptFunctionModifier.java $
 *******************************************************************************/

package com.codeandme.scripting.javascript.modules;

import java.lang.reflect.Method;

/**
 * Interface for modules that want to globally modify JavaScript code used to
 * wrap Java method calls. An implementing module will affect only code
 * generated by the autoWrap mechanism of {@link AbstractJavaScriptModule}.
 * Modules loaded before will not be affected. Modules loaded afterwards will be
 * affected by code insertions.
 */
public interface IJavaScriptFunctionModifier {

	/** intermediate name of original method call return value. */
	String RESULT_NAME = "__result";

	/**
	 * Get code that shall be executed before actual method gets called. As
	 * multiple modules might want to inject code make sure to avoid
	 * interactions by using commonly used variable names.
	 * 
	 * @param method
	 *            method that will be called afterwards.
	 * 
	 * @return JavaScript code to be inserted before method call
	 */
	String getPreExecutionCode(Method method);

	/**
	 * Get code that shall be executed after actual method gets called. As
	 * multiple modules might want to inject code make sure to avoid
	 * interactions by using commonly used variable names.
	 * 
	 * @param method
	 *            method that will be called before.
	 * 
	 * @return JavaScript code to be inserted after method call
	 */
	String getPostExecutionCode(Method method);
}
