package club.yuit.boot.service;

import club.yuit.boot.entity.Client;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author yuit
 * @create time 2018/10/16  10:03
 * @description
 * @modify by
 * @modify time
 **/

public interface IClientService extends IBaseService<Client> {
    Client findClientByClientId(@Param("id") String clientId);
}
