package edu.montana.esof322.homework.homework2;

import edu.montana.esof322.demo.patterns.creation.FactoryDemo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Homework2 {

    int invocationCount = 0;
    static StringBuilder output = new StringBuilder();

    public interface IDoAThing {
        void doIt();
    }

    class ThingDoer implements IDoAThing {
        public void doIt() {
            output.append("Did it!\n");
        }
    }

    public class DoerFactory {
        public IDoAThing getDoer() {
            ThingDoer thing = new ThingDoer();
            return (IDoAThing) Proxy.newProxyInstance(DoerFactory.class.getClassLoader(), new Class[]{IDoAThing.class},
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                                System.out.println("Proxied method call on thingDoer!");
                                invocationCount++;
                                return method.invoke(thing, objects);
                            }
            });
        }
    }

    //=======================================================================
    // Your boss wants to know how many times a method on a given class is
    // being invoked.  Your job is to take the the code above and refactor it
    // using some patterns to capture the needed information.
    //=======================================================================
    @Test
    void theAssignment() {
        // Step 1: extract an interface for ThingDoer, IDoAThing and
        //         replace the variable below with

        // Step 2: replace this new expression with a factory to produce
        //         IDoAThings
        DoerFactory factory = new DoerFactory();
        IDoAThing thingDoer = factory.getDoer();

        // Step 3: use the factory to insert a proxy object that wraps
        //         a ThingDoer and increments the invocationCount
        assertFalse(thingDoer instanceof ThingDoer);

        // do the thing a few times...
        thingDoer.doIt();
        thingDoer.doIt();
        thingDoer.doIt();

        // the proxy should have incremented the invocation count
        assertEquals(3, invocationCount);

        // output should still be the same since the proxy passed through
        // to the underlying ThingDoer
        assertEquals(output.toString(),
                "Did it!\nDid it!\nDid it!\n");
    }


}
