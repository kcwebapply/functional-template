package func.spring.rabbit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodObject{


    Method method;

    Object instance;

    List<Object> args = new ArrayList<Object>();

    public MethodObject(Object instance, Method method, Object ... args){
        this.instance = instance;
        this.method = method;
        this.args = Arrays.asList(args.clone());
    }

    public void invoke()  {
        try {
            method.invoke(instance, args.toArray());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}