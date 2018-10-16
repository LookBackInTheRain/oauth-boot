package club.yuit.boot.service.impl;

import club.yuit.boot.entity.Client;
import club.yuit.boot.mapper.ClientMapper;
import club.yuit.boot.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuit
 * @create time 2018/10/16  10:06
 * @description
 * @modify by
 * @modify time
 **/
@Service
public class ClientServiceImpl extends BaseServiceImpl<Client> implements IClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Client findClientByClientId(String clientId) {
        return this.clientMapper.findClientByClientId(clientId);
    }
}
