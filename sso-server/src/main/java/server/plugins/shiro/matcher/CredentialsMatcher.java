package server.plugins.shiro.matcher;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;


//配置自定义的密码比较器
public class CredentialsMatcher extends SimpleCredentialsMatcher{

	@Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        //获得数据库中的密码
        @SuppressWarnings("unchecked")
		Map<String, Object> credentials = (Map<String, Object>) info.getCredentials();
        String salt = credentials.get("salt").toString();
        String dbPassword = credentials.get("pwd").toString();
        
        //密码长度=32位，说明是md5加密过
        if(inPassword.length() == 32)
            return equals(inPassword, dbPassword);
        else{
        	String pwd =EncryptUtil.encrypt32(inPassword, salt);//md5 32位加密 
        	return this.equals(pwd, dbPassword);
        }
    }

	@Override
	protected boolean equals(Object tokenCredentials, Object accountCredentials) {
		return super.equals(tokenCredentials, accountCredentials);
	}
	
	
	
	
}
