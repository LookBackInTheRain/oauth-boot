package club.yuit.oauth.boot.mapper;

import club.yuit.oauth.boot.entity.Client;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author yuit
 * @create time 2018/10/16  10:02
 * @description
 * @modify by
 * @modify time
 **/
@Repository
public interface ClientMapper extends BaseMapper<Client> {


    @Select("select * from clients where clientId=#{id}")
    Client findClientByClientId(@Param("id") String clientId);

}
