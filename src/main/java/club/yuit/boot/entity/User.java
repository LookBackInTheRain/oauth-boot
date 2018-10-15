package club.yuit.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author yuit
 * @create time 2018/10/9  15:43
 * @description
 * @modify by
 * @modify time
 **/
@Data
@TableName("user")
public class User implements UserDetails {
    @TableId
    private String id;
    private String username;
    private String email;
    @TableField("isEnable")
    private Boolean isEnable;
    @TableField("isExpired")
    private Boolean isExpired;
    @TableField("isLocked")
    private Boolean isLocked;
    private String password;
    private String gender;

    @TableField(exist = false)
    private List<GrantedAuthority> authorities;

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }
}
