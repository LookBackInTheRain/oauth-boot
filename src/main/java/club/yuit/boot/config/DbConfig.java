package club.yuit.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author yuit
 * @create time 2018/10/9  15:14
 * @description
 * @modify by
 * @modify time
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DbConfig {

    private String username;
    private String url;
    private String  password;
    private String driverClassName;

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
    * <!-- 配置初始化大小、最小、最大 -->
        <property name="initialSize" value="10" />
        <property name="minIdle" value="30" />
        <property name="maxActive" value="300" />
        <!-- 配置获取连接等待超时的时间 -->
        <property name="maxWait" value="3600000" />
        <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
        <property name="timeBetweenEvictionRunsMillis" value="60000" />
        <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
        <property name="minEvictableIdleTimeMillis" value="30000" />
        <property name="validationQuery" value="SELECT 'x' FROM dual" />
        <property name="testWhileIdle" value="true" />
        <property name="testOnBorrow" value="false" />
        <property name="testOnReturn" value="false" />
        <!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
        <property name="poolPreparedStatements" value="true" />
        <property name="maxPoolPreparedStatementPerConnectionSize"
        value="20" />
    *
    * */
    @Bean
    public DataSource dataSource() {
        DruidDataSource dr = new DruidDataSource();
        dr.setUsername(this.username);
        dr.setPassword(this.password);
        dr.setDriverClassName(this.driverClassName);
        dr.setUrl(this.url);

        this.logger.info(this.url+":"+this.username+":"+this.password);

        dr.setInitialSize(10);
        dr.setMinIdle(30);
        dr.setMaxActive(300);
        dr.setMaxWait(3600000);
        dr.setTimeBetweenEvictionRunsMillis(60000);
        dr.setMinEvictableIdleTimeMillis(30000);
        dr.setValidationQuery("select 'X' from dual");
        dr.setTestWhileIdle(true);
        dr.setTestOnBorrow(false);
        dr.setTestOnReturn(false);
        dr.setPoolPreparedStatements(true);
        dr.setMaxPoolPreparedStatementPerConnectionSize(20);

        return dr;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

}
