基本依赖：
jdk 17 
spring boot 3.2.5

postgresql 需要启用插件 pgagent 来实现定时任务
1. docker-postgresql容器需要安装 pgagent，参考：https://www.pgadmin.org/docs/pgadmin4/8.5/pgagent_install.html
2. 执行pgagent ：
```shell
pgagent hostaddr=127.0.0.1 dbname=home user=root
```
3. 数据库执行
```sql
CREATE EXTENSION pgagent;
```
4. 在pgadmin中刷新，可以看到新增的 pgagent schema，具体使用参考：https://www.pgadmin.org/docs/pgadmin4/8.5/pgagent_jobs.html

