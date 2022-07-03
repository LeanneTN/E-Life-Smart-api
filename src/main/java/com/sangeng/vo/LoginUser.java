package com.sangeng.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sangeng.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails{
    private User user;
    private List<String> permissions;
    //该成员变量不存储到redis中
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(User user, List<String> permissions){
        this.user = user;
        this.permissions = permissions;
    }

    //获取权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
        if(authorities == null){
            authorities = permissions.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
