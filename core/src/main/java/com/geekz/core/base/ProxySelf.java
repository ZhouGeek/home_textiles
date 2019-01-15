package com.geekz.core.base;

import org.springframework.aop.framework.AopContext;

/**
 * Service的事务管理是AOP实现的，AOP的实现用的是JDK动态代理或CGLIB动态代理。
 * 所以，如果你想在你的代理方法中以 this 调用当前接口的另一个方法，另一个方法的事务是不会起作用的。
 * 因为事务的方法是代理对象的，而 this 是当前类对象，不是一个代理对象，自然事务就不会起作用了。
 * 这是我在不久前的开发中遇到的实际问题，我自定义了一个注解，加在方法上，使用AspectJ来拦截该注解，却没拦截到，
 * 原因就是这个方法是被另一个方法以 this 的方式调用的，所以AOP不能起作用。
 * 获取代理对象本身.
 */
public interface ProxySelf<T> {
    /**
     * 取得当前对象的代理.
     *
     * @return 代理对象,如果未被代理,则抛出 IllegalStateException
     */
    @SuppressWarnings("unchecked")
    default T self() {
        return (T) AopContext.currentProxy();
    }
}