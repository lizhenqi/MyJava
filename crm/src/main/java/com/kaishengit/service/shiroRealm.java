package com.kaishengit.service;

import com.kaishengit.exception.NotFoundException;
import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import sun.dc.pr.PRError;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Administrator on 2016/7/8.
 */
@Named
public class ShiroRealm extends AuthorizingRealm {

    @Inject
    private UserService userService;

    /**
     * 验证用户是否具有某项权限
     *
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        下面的principalCollection实际就是下面账号验证return new SimpleAuthenticationInfo(user,user.getPassword(),getName())传过来的user;
        User user = (User) principalCollection.getPrimaryPrincipal();
        if (user != null) {
//            根据user获取Role对象
            Integer roleid = user.getRoleid();
            Role role=userService.findRoleById(roleid);

//           根据Role将角色的名字赋值给info（给拥有特定角色才能查看特定内容的账户：例如管理员才能查看的：系统管理）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole(role.getRolename());
            return info;
//            意思即<shiro:hasRole name="经理,员工"></shiro:hasRole>里面的name只有和info利用才能查看里面的内容，多个权限之间用逗号分开。
        }
        return null;
    }


    /**
     * 用来验证用户的账户和密码是否正确
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        authenticationToken是UsernamePasswordToken的父类故要强制类型转换。
        String username = token.getUsername();
        User user = userService.findUserByUsername(username);
        if (user != null) {
            if (!user.getEnable()) {
                throw new LockedAccountException("该账户已被禁用");
//                如果账户被禁用则抛出。
            }

            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
//            注意上面括号内填写的内容：user、密码和getName()而不是user.getName().此处的getName()是token的
        } else {
            throw new UnknownAccountException("账号或是密码错误！");
        }
    }
}
