package socialUp.common;


import socialUp.common.BaseFactory;


public class ServiceFactory extends BaseFactory {

    public static Object createService( String serivceName ) throws Exception {
        return create(serivceName);
    }

    public static Object createService( Class serviceClass ) throws Exception {
        return create(serviceClass);
    }

}
