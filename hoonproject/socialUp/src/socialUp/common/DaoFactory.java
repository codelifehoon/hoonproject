
package socialUp.common;


/**
 * DAO을 생성하기위한 CLASS	
 * 
 * @author 장재훈
 *
 */
public class DaoFactory extends BaseFactory {
    
    public static Object createDAO( String daoName ) throws Exception {
        return create(daoName);
    }
    

    public static Object createDAO( Class daoClass ) throws Exception {
        return create(daoClass);
    }
}
