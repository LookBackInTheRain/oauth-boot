package club.yuit.boot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yuit
 * @create time 2018/10/9  15:43
 * @description
 * @modify by
 * @modify time
 **/
@Data
@TableName("user")
public class User {
    @TableId
    private String id;
    private String username;
    private String password;
    private String gender;

}
