package utils;


/**
 *Description: This util class is for validating element availability in a given enum
 *@author Didier Munezero
 */

public class Validity {
    public <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if(e.name().equals(value)) { return true; }
        }
        return false;
    }
}
