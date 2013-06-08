package com.captcha.web.controllers;

import net.paoding.rose.web.ErrorHandlerAdapter;
import net.paoding.rose.web.Invocation;

public class ErrorHandler extends ErrorHandlerAdapter
{
    public Object onError(Invocation inv, Exception ex) throws Throwable
    {
        System.out.println("---------Exception----------");
        inv.getResponse().getWriter().write("<pre>Exception<br>");
        ex.printStackTrace(inv.getResponse().getWriter());
        inv.getResponse().getWriter().write("</pre>");
        return "";
    }

}
