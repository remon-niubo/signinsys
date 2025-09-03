# 返回值封装
使用ajax封装返回值，典型返回值如
```json
{
    "msg": "操作成功",
    "code": 200,
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImxvZ2luX3VzZXJfa2V5IjoiZTcyODBjY2EtYTM0OC00MzQ5LThjNGQtNTA3ZDUwMWYyZGI1In0.r_vRwHtrGN_ejz--O-Mq1k0xHt20QGs_VzmnxA54i9ATa7YEbnVr0SNqssRtYT3u_90-fWxkfIQL9XvGO3tyTg"
}
```
```json
{
    "msg": "操作成功",
    "code": 200,
    "data": [
        {
            "createBy": "admin",
            "createTime": "2025-08-27 00:49:58",
            "updateBy": null,
            "updateTime": null,
            "remark": null,
            "deptId": 100,
            "parentId": 0,
            "ancestors": "0",
            "deptName": "若依科技",
            "orderNum": 0,
            "leader": "若依",
            "phone": "15888888888",
            "email": "ry@qq.com",
            "status": "0",
            "delFlag": "0",
            "parentName": null,
            "children": []
        },
        {
            "createBy": "admin",
            "createTime": "2025-08-27 00:49:58",
            "updateBy": null,
            "updateTime": null,
            "remark": null,
            "deptId": 101,
            "parentId": 100,
            "ancestors": "0,100",
            "deptName": "深圳总公司",
            "orderNum": 1,
            "leader": "若依",
            "phone": "15888888888",
            "email": "ry@qq.com",
            "status": "0",
            "delFlag": "0",
            "parentName": null,
            "children": []
        }
    ]
}

```

# 登陆令牌
- API接口为：POST http://localhost:8080/login
- Context-Type: application/json
    ```json
    {
      "username": "admin",
      "password": "admin123",
      "code": "",
      "uuid": ""
    }
    ```
- 访问其他任何接口需要确保在Headers体中有字段：```Authorization: Bearer <TOKEN>```

# 登录页面

用户模块，本系统中只有admin和用户两个角色。另外用户使用第三方的sso登录，因此本系统设计上不需要系统内的注册，但是需要将第三方的sso登录数据保存在本系统内。

关于第三方sso相关我还没有咨询甲方那边，因此用户的数据设计仅作参考。

```java
```

# 主管理界面, 班级模块

班级管理模块，班级的增删改查。ruoyi中有部门管理模块，可以直接使用。

```java
/**
 * 部门表 sys_dept
 * 
 * @author ruoyi
 */
public class SysDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private Integer orderNum;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 父部门名称 */
    private String parentName;
    
    /** 子部门 */
    private List<SysDept> children = new ArrayList<SysDept>();
}
```
1. 以某个部门为根节点, 获取部门列表*
    ```
    URL:        GET ~/system/dept/list
    参数:       SysDept
    参数方式:   路径参数自动绑定到类对象
    返回值:     List<SysDept> 该部门及其子子部门的所有信息
    权限:       system:dept:list
    ```

2. 获取部门列表, 但是排除某个部门节点*
    ```
    URL:        GET ~/system/dept/list/exclude/{deptId}
    参数:       Long deptId
    参数方式:   路径参数
    返回值:     List<SysDept> 该部门及其子子部门的所有信息
    权限:       system:dept:list
    ```

3. 查询某个部门的详细信息*
    ```
    URL:        GET ~/system/dept/{deptId}
    参数:       Long deptId
    参数方式:   路径参数
    返回值:     SysDept 该部门的信息
    权限:       system:dept:query
    ```

4. 新增部门*
    - 部门名字唯一, 否则返回error信息, data为空
    ```
    URL:        POST ~/system/dept
    参数:       SysDept
    参数方式:   路径参数自动绑定到类对象
    返回值:     int 本次插入影响的数据库表中的行数
    权限:       system:dept:add
    ```

5. 修改部门*
    - 部门名字唯一， 否则返回error信息, data为空
    - 该部门下的所有子部门必须都停用
    ```
    URL:        PUT ~/system/dept
    参数:       SysDept
    参数方式:   路径参数自动绑定到类对象
    返回值:     int 本次插入影响的数据库表中的行数
    权限:       system:dept:edit
    ```

6. 删除部门*
    - 部门必须不存在下级部门, 否则返回error, data为空
    - 部门必须不存在用户, 否则返回error, data为空
    ```
    URL:        DELETE ~/system/dept/{deptId}
    参数:       Long deptId
    参数方式:   路径参数
    返回值:     int 本次插入影响的数据库表中的行数
    权限:       system:dept:remove
    ```

7. 获取数据库表中所有部门, 测试用
    ```
    URL:        GET ~/system/dept/treeselectAll
    参数:       无
    返回值:     List<TreeSelect> 下拉树结构的部门列表
    权限:       无
    ```
