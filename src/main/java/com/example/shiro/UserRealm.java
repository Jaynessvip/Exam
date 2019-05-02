package com.example.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.RoleMapper;
import com.example.dao.UserMapper;
import com.example.pojo.User;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserMapper umapper;
	@Autowired
	private RoleMapper roleMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		Set<String> role = roleMapper.getRole(username);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(role);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		String username = t.getUsername();
		String password = new SimpleHash("MD5", new String(t.getPassword()), username, 1024).toString();// 1.加密类型2.加密源3.盐值4.加密次数
		System.out.println(password);
		User u = umapper.findByNameAndPSW(username, password);
		if (u != null && 1 != u.getStatu()) {
			AuthenticationInfo info = new SimpleAuthenticationInfo(t.getUsername(), t.getPassword(), getName());
			return info;
		} else {
			throw new AuthenticationException();
		}

	}

}
