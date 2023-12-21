package com.api.telisadoptproyect.library.validation;

public class EnumValidation {
    public static boolean validateEnum(Class<? extends Enum<?>> e, String value){
        for (Enum<?> enumValue : e.getEnumConstants()){
            if (enumValue.name().equals(value)){
                return true;
            }
        }
        return false;
    }

    public static <E extends Enum<E>> E toEnum (Class<E> e, String value){
        for (E enumValue : e.getEnumConstants()){
            if (enumValue.name().equals(value)){
                return enumValue;
            }
        }
        return null;
    }

    public static <E extends Enum<E>> String safeGetName (E e){
        if (e == null) return null;
        return e.name();
    }

    public static <E extends Enum<E>> boolean equals (E e1, E e2){
        if (e1 == null && e2 == null) return true;
        if (e1 == null || e2 == null) return false;
        return e1.equals(e2);
    }
}
