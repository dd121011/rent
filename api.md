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

### 所有字典类型列表

> /api/dic/dicListAll

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |

RETURN

### 所有字典项目列表【某一字典类型】

> /api/dic/dicItermListAll

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |
| dicId | Integer | 非必填 | 字典类型Id |

RETURN

### 所有额外收费项

> /api/dic/extrasAll

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |

RETURN

### 所有配套设施

> /api/dic/facilitiestAll

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |

RETURN

### 所有装修情况

> /api/dic/decorationAll

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |

RETURN

### 所有房间朝向

> /api/dic/orientationAll

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |

RETURN

## 房东

### 房子列表

> /api/building/list

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| page | int | 必填 | 页码 |
| rows | int | 必填 | 每页大小 |
| tokenId | String | 必填 | 登录标识 |
| buildingId | Integer | 非必填 | 房子Id |
| name | String | 非必填 | 房子名称，精确匹配 |
| deleteTs | Long | 非必填 | 删除标识，deleteTs>0代表已删除 |

RETURN

### 编辑房子

> /api/building/edit

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |
| buildingId | Integer | 非必填 | 有值代表修改，否则新增 |
| name | String | 必填 | 房子名称 |
| facilities | String | 非必填 | 配套设施id字符串，','隔开 |
| extraFee | String | 非必填 | 额外收费项id字符串，','隔开 |
| description | String | 非必填 | 描述 |
| address | String | 非必填 | 地址 |

RETURN

### 删除房子

> /api/building/delete

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |
| ids | Integer数组 | 必填 | buildingId |

RETURN

### 获取所有房子

> /api/building/buildingAll

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |

RETURN

### 房子详情

> /api/room/detail/{buildingId}

GET

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| buildingId | Integer | 必填 | 房子ID |

RETURN

### 房间列表

> /api/room/list/{buildingId}

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| page | int | 必填 | 页码 |
| rows | int | 必填 | 每页大小 |
| tokenId | String | 必填 | 登录标识 |
| buildingId | Integer | 非必填 | 房子Id |
| roomNo | String | 非必填 | 房间号,精确匹配 |
| rentTs | Long | 非必填 | 删除标识，rentTs>0代表已出租，rentTs=0代表未出租，rentTs<0代表全部 |
| deleteTs | Long | 非必填 | 删除标识，deleteTs>0代表已删除 |

RETURN

### 编辑房间

> /api/room/edit

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |
| buildingId | Integer | 非必填 | 房子id,一个房间对应一个房子id |
| roomId | Integer | 必填 | 房子Id |
| roomNo | String | 必填 | 房间号 |
| bedroom | Integer | 必填 | 房间数量 |
| living | Integer | 必填 | 客厅数量 |
| toilet | Integer | 非必填 | 卫生间数量 |
| orientation | String | 非必填 | 房间朝向id |
| decoration | String | 非必填 | 额外收费项id |
| guaranty | Integer | 必填 | 装修情况 |
| pay | Integer | 必填 | 租金月份 |
| rentFee | Integer | 必填 | 租金[元/月] |
| area | Integer | 必填 | 使用面积[平方米] |
| facilities | String | 非必填 | 配套设施id字符串，','隔开 |
| extraFee | String | 非必填 | 额外收费项id字符串，','隔开 |
| description | String | 非必填 | 描述 |

RETURN

### 删除房间

> /api/room/delete

POST

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |
| ids | Integer数组 | 必填 | roomId |

RETURN

### 房间详情

> /api/room/detail/{roomId}

GET

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| roomId | Integer | 必填 | 房间ID |

RETURN

### 房间所有租客

> /api/room/renterAll/{roomId}

GET

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| tokenId | String | 必填 | 登录标识 |
| roomId | Integer | 必填 | 房间ID |

RETURN

房东
2，添加租客，删除租客，修改租客，租客详情
3，添加合同，解除合同，合同详情
4，生成账单，账单结算，账单详情

小程序

## 小程序

### 绑定用户

> /api/user/bindUser

Get

| 参数 | 类型 | 是否必填 | 描述 |
| --- | --- | --- | --- |
| userId | Integer | 必填 | 用户Id |
| code | String | 必填 | 登录时获取的 code |

RETURN

绑定租客、上传身份信息
查看合同、押金单
查看水电、缴费记录
