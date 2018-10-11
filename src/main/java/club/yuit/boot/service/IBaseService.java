package club.yuit.boot.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author yuit
 * @create time 2018/10/11  9:28
 * @description
 * @modify by
 * @modify time
 **/
public interface IBaseService<T> {

    int insert(T t);

    int deleteById(Serializable id);

    int deleteByMap(Map<String, Object> map);

    int delete(Wrapper<T> wt);

    int deleteBatchIds(Collection<? extends Serializable> ids);

    int updateById( T t);

    int update( T t,  Wrapper<T> wt);

    T selectById(Serializable id);

    List<T> selectBatchIds( Collection<? extends Serializable> ids);

    List<T> selectByMap( Map<String, Object> map);

    T selectOne( Wrapper<T> wt);

    Integer selectCount( Wrapper<T> wt);

    List<T> selectList( Wrapper<T> wt);

    List<Map<String, Object>> selectMaps( Wrapper<T> wt);

    List<Object> selectObjs(Wrapper<T> wt);

    IPage<T> selectPage(Page<T> page,  Wrapper<T> wt);

    IPage<Map<String, Object>> selectMapsPage(Page<T> page,  Wrapper<T> wt);

}
