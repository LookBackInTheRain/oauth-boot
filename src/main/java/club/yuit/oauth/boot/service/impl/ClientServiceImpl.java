package club.yuit.oauth.boot.service.impl;

import club.yuit.oauth.boot.entity.Client;
import club.yuit.oauth.boot.mapper.ClientMapper;
import club.yuit.oauth.boot.service.IClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuit
 * @date 2018/10/16  10:06
 *
 **/
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper,Client> implements IClientService {


    @Override
    public Client findClientByClientId(String clientId) {
        return this.baseMapper.findClientByClientId(clientId);
    }
}
