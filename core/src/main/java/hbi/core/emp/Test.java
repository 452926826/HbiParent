package hbi.core.emp;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by dengz on 2017/4/12.
 * zhilong.deng@hand-china.com
 */
public class Test {
    public static void main(String[] args) throws URISyntaxException {
        URI uri = new URI ("http://plm.teg.cn/Windchill/servlet/RPC?CLASS=com.infoengine.soap");
        System.out.println ("Authority = " +uri.getAuthority ());
        System.out.println ("Fragment = " +uri.getFragment ());
        System.out.println ("Host = " +uri.getHost ());
        System.out.println ("Path = " +uri.getPath ());
        System.out.println ("Port = " +uri.getPort ());
        System.out.println ("Query = " +uri.getQuery ());
        System.out.println ("Scheme = " +uri.getScheme ());
        System.out.println ("Scheme-specific part = " + uri.getSchemeSpecificPart ());
        System.out.println ("User Info = " +uri.getUserInfo ());
        System.out.println ("URI is absolute: " +uri.isAbsolute ());
        System.out.println ("URI is opaque: " +uri.isOpaque ());
    }
}
