package club.yuit.oauth.boot.support.common;

/**
 * @author yuit
 * @date 2018/10/19 10:57
 */
public enum TokenStoreType {
    /*
        内存
     */
    memory,
    /*
        redis
     */
    redis,
    /*
        json web token
     */
    jwt,
    /*
        数据库
     */
    jdbc
}
