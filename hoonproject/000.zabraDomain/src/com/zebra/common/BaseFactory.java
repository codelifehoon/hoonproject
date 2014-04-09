package com.zebra.common;

import java.lang.reflect.Constructor;

import java.lang.reflect.Modifier;
import org.apache.log4j.Logger;
import com.sun.beans.finder.ClassFinder;


public abstract class BaseFactory {

    public static Logger logger = Logger.getLogger(BaseFactory.class);
    
    
    protected BaseFactory() {
    }

    /**
     * class instance return
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T>T create( Class clazz )  {
  
        return (T)create(clazz.getName());
    }

    /**
     * class instance return
     * 
     * @param
     * @return
     */
    public static <T>T  create( String className ) {
        // ClassLoader cl = getClassLoader();

        Object serviceObject = null;

        // Class serviceClass = cl.loadClass( SERVICE_PREFIX + serviceName +
        // SERVICE_SUFFIX );
        // serviceObject = serviceClass.newInstance();

        try {
            serviceObject = Class.forName(className).newInstance();
        }
        catch (Exception e) {
        	e.printStackTrace();
             }

        return (T)serviceObject;
    }

    /**
     * 클래스의 instance 를 생성한다. 
     * 
     * @param className
     * @param classArgValues
     * @return
     */
    public static final Object create( String className, Object[] classArgValues ) throws Exception {
        Object instance = null;

        try {
            Class clazz = Class.forName( className );
            Constructor constructor = getConstructor(clazz, classArgValues);
            if (!Modifier.isPublic(constructor.getModifiers())
                    || !Modifier.isPublic(constructor.getDeclaringClass().getModifiers())) {
                constructor.setAccessible(true);
            }
            instance = constructor.newInstance(classArgValues);
        }
        catch (Exception e) {
            throw new Exception("Factory 객체["+className+"]의 클래스를 찾을 수 없습니다.", e);
        }

        return instance;

    }

    /**
     * TODO 메소드 설명을 입력해주십시오. <P/> 상세한 메소드 설명.
     * 
     * @return
     */
    protected static ClassLoader getClassLoader(Class clazz) {
        ClassLoader cl = null;

        try {
            cl = clazz.getClassLoader();
        }
        catch (SecurityException ex) {
//            logger.debug("현재 Thread 의 ClassLoader 를 얻지 못했기 때문에 기본 시스템 Class Loader 를 사용합니다.", ex);
            if( logger.isDebugEnabled() ) {
                logger.debug("'"+clazz+"' 클래스의 보안 접근 제한(security restriction) 때문에 classloader 를 얻을 수 없습니다.", ex);
            }
        }

        if (cl == null) {
            cl = BaseFactory.class.getClassLoader();
        }

        return cl;
    }

    /**
     * Return a constructor on the class with the arguments.
     * 
     * @throws exception
     *             if the method is ambiguios.
     */
    protected final static Constructor getConstructor( final Class clazz, final Object[] classArgValues ) {
        Constructor constructor = null;

        // PENDING: Implement the resolutuion of ambiguities properly.
        Constructor[] constructors = clazz.getConstructors();
        // 생성자의
        Class[] classType = getClassTypes(classArgValues);

        for (int i = 0; i < constructors.length; i++) {
            if (matchArguments(classType, constructors[i].getParameterTypes())) {
                constructor = constructors[i];
            }
        }
        return constructor;
    }

    /**
     * TODO 메소드 설명을 입력해주십시오.
     * <P/>
     * 상세한 메소드 설명.
     *
     * @param objs
     * @return
     */
    protected final static Class[] getClassTypes( final Object[] objs ) {
        Class[] classType = null;

        if (objs != null && objs.length > 0) {
            classType = new Class[objs.length];
            for (int idx = 0; idx < objs.length; idx++) {
                classType[idx] = objs[idx].getClass();
            }
        }

        return classType;
    }

    /**
     * Tests each element on the class arrays for assignability.
     * 
     * @param argClasses
     *            arguments to be tested
     * @param argTypes
     *            arguments from Method
     * @return true if each class in argTypes is assignable from the
     *         corresponding class in argClasses.
     */
    protected final static boolean matchArguments( final Class[] argClasses, final Class[] argTypes ) {
        return matchArguments(argClasses, argTypes, false);
    }

    /**
     * TODO 메소드 설명을 입력해주십시오.
     * <P/>
     * 상세한 메소드 설명.
     *
     * @param argClasses
     * @param argTypes
     * @param explicit
     * @return
     */
    protected final static boolean matchArguments( final Class[] argClasses, final Class[] argTypes, boolean explicit ) {
        boolean match = (argClasses.length == argTypes.length);
        for (int idx = 0; idx < argClasses.length && match; idx++) {
            Class argType = argTypes[idx];
            if (argType.isPrimitive()) {
                argType = typeToClass(argType);
            }
            if (explicit) {
                // Test each element for equality
                if (argClasses[idx] != argType) {
                    match = false;
                }
            }
            else {
                // Consider null an instance of all classes.
                if (argClasses[idx] != null && !(argType.isAssignableFrom(argClasses[idx]))) {
                    match = false;
                }
            }
        }
        return match;
    }

    protected static Class typeToClass( final Class type ) {
    	  
    	try 
    	{
    		//Access restriction 오류발생시 compile error/warnings 설정 변경필요
			return ClassFinder.resolveClass(type.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    
      
        
    }

}
