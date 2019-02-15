package club.yuit.oauth.boot.service;

import club.yuit.oauth.boot.entity.Client;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * @author yuit
 * @date 2018/10/16  10:03
 *
 **/

public interface IClientService extends IService<Client> {
    Client findClientByClientId(@Param("id") String clientId);
}
