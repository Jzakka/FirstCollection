package org.example;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestUtil {
    private static final Map<Class<?>, Class<?>> wrapperToPrimitiveMap = new HashMap<>();

    static {
        wrapperToPrimitiveMap.put(Integer.class, int.class);
        wrapperToPrimitiveMap.put(Long.class, long.class);
        wrapperToPrimitiveMap.put(Double.class, double.class);
        wrapperToPrimitiveMap.put(Float.class, float.class);
        wrapperToPrimitiveMap.put(Boolean.class, boolean.class);
        wrapperToPrimitiveMap.put(Character.class, char.class);
        wrapperToPrimitiveMap.put(Byte.class, byte.class);
        wrapperToPrimitiveMap.put(Short.class, short.class);
    }

    public <T> T run(Object target, String methodName, Object... params) throws Exception {
        Method method;
        try {
            Class<?>[] paramTypes = Arrays.stream(params)
                    .map(Object::getClass)
                    .toArray(Class[]::new);
            method = target.getClass().getDeclaredMethod(methodName, paramTypes);

        } catch (NoSuchMethodException e) {
            Class<?>[] boxedParamTypes = Arrays.stream(params)
                    .map(p -> {
                        Class<?> pClass = p.getClass();
                        return wrapperToPrimitiveMap.getOrDefault(pClass, pClass);
                    })
                    .toArray(Class[]::new);
            method = target.getClass().getDeclaredMethod(methodName, boxedParamTypes);
        }
        method.setAccessible(true);
        return (T) method.invoke(target, params);
    }
}
