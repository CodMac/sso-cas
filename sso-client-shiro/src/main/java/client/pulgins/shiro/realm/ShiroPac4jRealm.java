package client.pulgins.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

import io.buji.pac4j.realm.Pac4jRealm;

public class ShiroPac4jRealm extends Pac4jRealm {
 
    @SuppressWarnings("unused")
	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
        Object user = super.getAvailablePrincipal(principals);
        
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<String> permissions = new ArrayList<String>();
        
        simpleAuthorizationInfo.addStringPermissions(permissions);
 
        return simpleAuthorizationInfo;
    }
 
}