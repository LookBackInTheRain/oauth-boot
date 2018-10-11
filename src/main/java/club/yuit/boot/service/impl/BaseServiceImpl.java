package club.yuit.boot.service.impl;

import club.yuit.boot.service.IBaseService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author yuit
 * @create time 2018/10/11  9:30
 * @description
 * @modify by
 * @modify time
 **/
@Service
public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    @Qualifier("bootBaseMapper")
    private BaseMapper<T> mapper;


    @Override
    public int insert(T t) {
        return this.mapper.insert(t);
    }

    @Override
    public int deleteById(Serializable id) {
        return this.mapper.deleteById(id);
    }

    @Override
    public int deleteByMap(Map<String, Object> map) {
        return this.mapper.deleteByMap(map);
    }

    @Override
    public int delete(Wrapper<T> wt) {
        return this.mapper.delete(wt);
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> ids) {
        return this.mapper.deleteBatchIds(ids);
    }

    @Override
    public int updateById(T t) {
        return this.mapper.updateById(t);
    }

    @Override
    public int update(T t, Wrapper<T> wt) {
        return this.mapper.update(t,wt);
    }

    @Override
    public T selectById(Serializable id) {
        return this.mapper.selectById(id);
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> ids) {
        return this.mapper.selectBatchIds(ids);
    }

    @Override
    public List<T> selectByMap(Map<String, Object> map) {
        return this.mapper.selectByMap(map);
    }

    @Override
    public T selectOne(Wrapper<T> wt) {
        return this.mapper.selectOne(wt);
    }

    @Override
    public Integer selectCount(Wrapper<T> wt) {
        return this.mapper.selectCount(wt);
    }

    @Override
    public List<T> selectList(Wrapper<T> wt) {
        return this.mapper.selectList(wt);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<T> wt) {
        return this.mapper.selectMaps(wt);
    }

    @Override
    public List<Object> selectObjs(Wrapper<T> wt) {
        return this.mapper.selectObjs(wt);
    }

    @Override
    public IPage<T> selectPage(Page<T> page, Wrapper<T> wt) {
        return this.mapper.selectPage(page,wt);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(Page<T> page, Wrapper<T> wt) {
        return this.mapper.selectMapsPage(page,wt);
    }
}
