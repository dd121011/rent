# API 文档
---------
> 接口地址: https://scrats.cn/rent


## 登陆接口
> [POST] **application/json** `/login` 

| param | type | require | description |
| --- | --- | --- | --- |
| username | String | true | 用户名 |
| pwd | String | true | 密码 |

#### Sample
```
curl  X POST  H 'Content type: application/json'  --data-binary '{"username":"123123", "pwd":"111111"}' 'https://scrats.cn/rent/login'
```
#### Response

```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

## 字典

### 所有字典类型列表

> [GET] **application/json** `/api/dic/dicListAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/dic/dicListAll'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 某一字典类型的所有字典项目列表

> [GET] **application/json** `/api/dic/dicItermListAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/dic/dicItermListAll'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 所有额外收费项

> [GET] **application/json** `/api/dic/extrasAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/dic/extrasAll'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 所有配套设施

> [GET] **application/json** `/api/dic/facilitiestAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/dic/facilitiestAll'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 所有装修情况

> [GET] **application/json** `/api/dic/decorationAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/dic/decorationAll'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 所有房间朝向

> [GET] **application/json** `/api/dic/orientationAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/dic/orientationAll'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 所有房间朝向

> [GET] **application/json** `/api/dic/depositAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/dic/depositAll'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

## 房东

### 房子列表

> [POST] **application/json** `/api/building/list` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |
| page | int | true | 页码 |
| rows | int | true | 每页大小 |
| buildingId | Integer | false | 房子Id |
| name | String | false | 房子名称，精确匹配 |
| deleteTs | Long | false | 删除标识，deleteTs>0代表已删除 |

#### Sample
```
curl  X POST  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 3'   data binary $'{"page": 1, "rows": 10, "buildingId": 1, "name": "宝山新村二区28栋"}' https://scrats.cn/rent/api/building/list'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 编辑房子

> [POST] **application/json** `/api/building/edit` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |
| buildingId | Integer | false | 有值代表修改，否则新增 |
| name | String | true | 房子名称 |
| facilities | String | false | 配套设施id字符串，','隔开 |
| extraFee | String | false | 额外收费项id字符串，','隔开 |
| description | String | false | 描述 |
| address | String | false | 地址 |

#### Sample
```
curl  X POST  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 3'   data binary $'{"page": 1, "rows": 10, "buildingId": 1, "name": "宝山新村二区28栋"}' https://scrats.cn/rent/api/building/edit'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 删除房子

> [POST] **application/json** `/api/building/delete` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |
| ids | Integer数组 | true | buildingId |

#### Sample
```
curl  X POST  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 3'   data binary $'{"ids": [1,2,3,4],}' https://scrats.cn/rent/api/building/delete'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 获取所有房子

> [GET] **application/json** `/api/building/buildingAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 3' 'https://scrats.cn/rent/api/dic/dicListAll'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 房子详情

> [GET] **application/json** `/api/room/detail/{buildingId}` 

| param | type | require | description |
| --- | --- | --- | --- |

#### Sample
```
curl  X GET  H 'Content type: application/json' 'https://scrats.cn/rent/api/room/detail/1'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 房间列表

> [POST] **application/json** `/api/room/list/{buildingId}` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |
| page | int | true | 页码 |
| rows | int | true | 每页大小 |
| buildingId | Integer | false | 房子Id |
| roomNo | String | false | 房间号,精确匹配 |
| rentTs | Long | false | 出租标识，rentTs>0代表已出租，rentTs=0代表未出租，rentTs<0代表全部 |
| deleteTs | Long | false | 删除标识，deleteTs>0代表已删除 |

#### Sample
```
curl  X POST  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 3' 'https://scrats.cn/rent/api/room/list/1'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 编辑房间

> [POST] **application/json** `/api/room/edit` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |
| buildingId | Integer | false | 房子id,一个房间对应一个房子id |
| roomId | Integer | true | 房子Id |
| roomNo | String | true | 房间号 |
| bedroom | Integer | true | 房间数量 |
| living | Integer | true | 客厅数量 |
| toilet | Integer | false | 卫生间数量 |
| orientation | String | false | 房间朝向id |
| decoration | String | false | 额外收费项id |
| guaranty | Integer | true | 装修情况 |
| pay | Integer | true | 租金月份 |
| rentFee | Integer | true | 租金[元/月] |
| area | Integer | true | 使用面积[平方米] |
| facilities | String | false | 配套设施id字符串，','隔开 |
| extraFee | String | false | 额外收费项id字符串，','隔开 |
| description | String | false | 描述 |

#### Sample
```
curl  X POST  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 3' 'https://scrats.cn/rent/api/room/edit'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 删除房间

> [POST] **application/json** `/api/room/delete` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |
| ids | Integer数组 | true | roomId |

#### Sample
```
curl  X POST  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 3' 'https://scrats.cn/rent/api/room/delete'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 房间详情

> [GET] **application/json** `/api/room/detail/{roomId}` 

| param | type | require | description |
| --- | --- | --- | --- |

#### Sample
```
curl  X GET  H 'Content type: application/json' 'https://scrats.cn/rent/api/room/detail/1'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 房间所有租客

> [GET] **application/json** `/api/room/renterAll/{roomId}` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |
| roomId | Integer | true | 房间ID |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 3' 'https://scrats.cn/rent/api/room/renterAll/3'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

房东
2，添加租客，删除租客，修改租客，租客详情
3，添加合同，解除合同，合同详情
4，生成账单，账单结算，账单详情

小程序

## 小程序

### 小程序登录

> [POST] **application/json** `/api/renter/snsLogin` 

| param | type | require | description |
| --- | --- | --- | --- |
| code | String | true | 登录时获取的 code |
| signature | String | true | 待校验签名 |
| rawData | String | true | 被校验rawData |

#### Sample
```
curl  X POST  H 'Content type: application/json' --data-binary '{"code":"011a4IcW1Iu8OV0mWt9W12c4dW1a4IcQ", "signature":"fe9ddbd1db6beb82c022c127f34f45e4e4cbb1a5", "rawData":"{\\"nickName\\":\\"\u590f\u5929\\",\\"gender\\":1,\\"language\\":\\"zh_CN\\",\\"city\\":\\"Zhuhai\\",\\"province\\":\\"Guangdong\\",\\"country\\":\\"China\\",\\"avatarUrl\\":\\"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKbVPzJ2iab2mnxY5nX9icHwud9ueQrDm2O2icJAv69RCHT0NvIUscjC9AcooQSm3KotuQicyr2kuCic6w/132\\"}",}' 'https://scrats.cn/rent/api/renter/snsLogin'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 绑定用户

> [POST] **application/json** `/api/renter/bindUser` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | 登录获取的tokenId，不要放在header中 |
| openid | String | true | 登录时获取的openid |
| roomId | Integer | true | 带绑定的房间Id |

#### Sample
```
curl  X POST  H 'Content type: application/json' --data-binary '{"tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff", "openid": "xxxxxx", roomId":3}' 'https://scrats.cn/rent/api/renter/bindUser'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 获取房间列表

> [GET] **application/json** `/api/renter/roomList` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | 登录获取的tokenId |
| userId | Integer | true | userId放在header中 |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/enter/roomList'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 获取某一个房间的合同

> [GET] **application/json** `/api/renter/bargin/{roomId}` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | 登录获取的tokenId |
| userId | Integer | true | userId放在header中 |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/renter/bargin/3'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 获取某一个房间的押金

> [GET] **application/json** `/api/renter/deposit/{roomId}` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | 登录获取的tokenId |
| userId | Integer | true | userId放在header中 |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/renter/deposit/3'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```

### 获取某一个房间的未缴费房租

> [GET] **application/json** `/api/renter/unpay/{roomId}` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | 登录获取的tokenId |
| userId | Integer | true | userId放在header中 |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/renter/unpay/3'
```
#### Response
```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff",
        "userId": "9",
    }
}
```



上传身份信息

查看水电
