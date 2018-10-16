package club.yuit.boot.mapper;

import club.yuit.boot.entity.Client;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author yuit
 * @create time 2018/10/16  10:02
 * @description
 * @modify by
 * @modify time
 **/
@Repository
public interface ClientMapper extends BootBaseMapper<Client> {


    @Select("select * from clients where clientId=#{id}")
    Client findClientByClientId(@Param("id") String clientId);

}
