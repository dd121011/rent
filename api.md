# API 文档
------

## 登陆接口

> /login

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| username | String | 必填 | 用户名 |
| pwd | String | 必填 | 密码 |

RETURN

## 字典

### 所有类型列表

> /api/dic/list

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |

RETURN

### 所有字典项目列表【某一字典类型】

> /api/dic/dicItermList

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |
| dicId | Integer | 非必填 | 字典类型Id |

RETURN

## 房东

### 房子列表

> /api/building/list

GET

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| page | int | 必填 | 页码 |
| rows | int | 必填 | 每页大小 |
| tokenId | String | 必填 | 登录标识 |

RETURN


房东
0，创建房子，删除房子，修改房子，房子详情，房子列表
1，添加房间，删除房间，修改房间，房间详情，房间列表
2，添加租客，删除租客，修改租客，租客详情
3，添加合同，解除合同，合同详情
4，生成账单，账单结算，账单详情