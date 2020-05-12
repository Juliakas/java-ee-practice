package com.github.juliakas.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import java.util.Arrays;

@Interceptor
@Logger
public class LoggerInterceptor {

    @AroundInvoke
    public Object trace(InvocationContext ctx) throws Exception {
        String methodName = ctx.getMethod().getDeclaringClass().getName()
                + " -> " + ctx.getMethod().getName();
        Object[] params = ctx.getParameters();
        String fullMethodPathWithParams = methodName + "(" + String.join(", ",
                Arrays.stream(params).map(Object::toString).toArray(String[]::new))+ ")";

        System.out.println("BEGIN: " + fullMethodPathWithParams);
        try {
            Object res = ctx.proceed();
            System.out.println("END: " + fullMethodPathWithParams);
            return res;
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + fullMethodPathWithParams
                    + ": \"" + e.getMessage() + "\" at line " + e.getStackTrace()[0].getLineNumber());
            throw e;
        }
    }
}
