package org.example;

import java.lang.reflect.Method;
import java.util.*;

public class TestUtil {
    private static final Map<Class<?>, Class<?>> wrapperToPrimitiveMap = new HashMap<>();
    private static final Map<Class<?>, Class<?>> primitiveToWrapperMap = new HashMap<>();

    static {
        wrapperToPrimitiveMap.put(Integer.class, int.class);
        wrapperToPrimitiveMap.put(Long.class, long.class);
        wrapperToPrimitiveMap.put(Double.class, double.class);
        wrapperToPrimitiveMap.put(Float.class, float.class);
        wrapperToPrimitiveMap.put(Boolean.class, boolean.class);
        wrapperToPrimitiveMap.put(Character.class, char.class);
        wrapperToPrimitiveMap.put(Byte.class, byte.class);
        wrapperToPrimitiveMap.put(Short.class, short.class);

        primitiveToWrapperMap.put(int.class, Integer.class);
        primitiveToWrapperMap.put(long.class, Long.class);
        primitiveToWrapperMap.put(double.class, Double.class);
        primitiveToWrapperMap.put(float.class, Float.class);
        primitiveToWrapperMap.put(boolean.class, Boolean.class);
        primitiveToWrapperMap.put(char.class, Character.class);
        primitiveToWrapperMap.put(byte.class, Byte.class);
        primitiveToWrapperMap.put(short.class, Short.class);
    }

    public <T> T run(Object target, String methodName, Object... params) throws Exception {
        Class<?>[] paramTypes = Arrays.stream(params)
                .map(Object::getClass)
                .toArray(Class[]::new);

        List<Class<?>[]> possibleParamTypes = generatePossibleParamTypes(paramTypes);

        for (Class<?>[] possibleParamType : possibleParamTypes) {
            Optional<Method> methodOptional = findMethod(target.getClass(), methodName, possibleParamType);
            if (methodOptional.isPresent()) {
                Method method = methodOptional.get();
                method.setAccessible(true);
                return (T) method.invoke(target, params);
            }
        }

        throw new NoSuchMethodException(
                "No such method found: " + methodName + " with parameter types " + Arrays.toString(paramTypes));
    }

    private Optional<Method> findMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes) {
        try {
            return Optional.of(clazz.getDeclaredMethod(methodName, paramTypes));
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        }
    }

    private List<Class<?>[]> generatePossibleParamTypes(Class<?>[] paramTypes) {
        List<Class<?>[]> possibleParamTypesList = new ArrayList<>();
        generatePossibleParamTypes(paramTypes, 0, possibleParamTypesList);
        return possibleParamTypesList;
    }

    private void generatePossibleParamTypes(Class<?>[] paramTypes, int index, List<Class<?>[]> possibleParamTypesList) {
        if (index == paramTypes.length) {
            possibleParamTypesList.add(paramTypes.clone());
            return;
        }
        Class<?> originalType = paramTypes[index];
        Class<?> primitiveType = wrapperToPrimitiveMap.get(originalType);
        Class<?> wrapperType = primitiveToWrapperMap.get(originalType);

        // 일반 오브젝트임
        if (primitiveType == null && wrapperType == null) {
            generatePossibleParamTypes(paramTypes, index + 1, possibleParamTypesList);
        }
        // 래퍼 혹은 기본 클래스임
        else{
            if (primitiveType != null) { // 래퍼클래스임
                paramTypes[index] = primitiveType;
            } else  { // 기본클래스임
                paramTypes[index] = wrapperType;
            }
            generatePossibleParamTypes(paramTypes, index + 1, possibleParamTypesList);
            paramTypes[index] = originalType;
            generatePossibleParamTypes(paramTypes, index + 1, possibleParamTypesList);
        }
    }


}
