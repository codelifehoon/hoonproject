package socialUp.common.util;

public class JarCheck {

    public static void main( String[] args ) {
        String _resource = args[0];
        String _type = args[1];
        
        String path = null;
        
        if (_resource != null) {
            ResourceLocation rl = new ResourceLocation();

            if ("class".equals(_type)) {
                path = rl.getResourceURL(_resource, true);
            } else {
                path = rl.getResourceURL(_resource, false);
            }
            
            System.out.println( "* 요청 리소스 : " + _resource );
            System.out.println( "* 요청 타입 : " + _type );
            System.out.println( "* 실제 리소스 경로 : " + path );
        }
    }

    /**
     * <p>
     * Java Class 혹은 리소스(클래스패스 내에 있는 파일)의 위치를 정확히 알려준다.
     * </p>
     * 
     * @author Son KwonNam(kwon37xi@yahoo.co.kr)
     */
    private static class ResourceLocation {

        /**
         * 패키지명을 포함한 클래스 풀 네임을 리소스 경로(/package/classname.class)로 바꿔준다.
         * 
         * @param classFullName
         *            클래스 풀 네임(package.classname)
         * @return 리소스 경로 (/package/classname.class)
         */
        public String classFullNameToResourcePath(String classFullName) {
            String resourcePath = classFullName.replace('.', '/').trim();
            resourcePath = "/" + resourcePath + ".class";
            return resourcePath;
        }

        /**
         * 클래스 풀 네임을 받아서 실제 파일이 위치한 URL을 리턴한다.
         * 
         * @param classFullName
         *            클래스의 풀 네임
         * @return 클래스 파일의 URL
         */
        public String getResourceURL(String classFullName) {
            return getResourceURL(classFullName, true);
        }

        /**
         * 클래스 혹은 리소스의 이름을 받아서 실제 파일이 위치한 URL을 리턴한다.
         * 
         * @param resource
         *            리소스 이름
         * @param isClass
         *            클래스인지 여부
         * @return 리소스의 URL
         */
        public String getResourceURL(String resource, boolean isClass) {
            String refinedResource = null;

            // "/dir1/dir2/resource.ext" 형태로 바꾼다.
            if (isClass) {
                refinedResource = classFullNameToResourcePath(resource);
            } else if (!resource.startsWith("/")) {
                refinedResource = "/" + resource.trim();
            } else {
                refinedResource = resource.trim();
            }

            System.out.println("검색할 리소스 : " + refinedResource);

            java.net.URL resourceUrl = ResourceLocation.class
                    .getResource(refinedResource);

            if (resourceUrl == null) {
                return null;
            }

            return resourceUrl.getFile();
        }
    }
}

