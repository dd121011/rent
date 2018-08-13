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
curl -X POST -H 'Accept: application/json' -H 'Content-type: application/json' -d '{"username":"admin", "pwd":"111111"}' 'https://scrats.cn/rent/login'
```
#### Response

```
{
    "code": 1,
    "count": 0,
    "data": {
        "tokenId": "0451e77616bc4f188b2003b2dc656855",
        "userId": "3",
    }
}
```

## 字典

### 所有字典类型列表

> [GET] `/api/dic/dicListAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  -X GET -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' 'https://scrats.cn/rent/api/dic/dicListAll'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": [
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicCode": "0001",
        "name": "配套设施"
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicCode": "0002",
        "name": "装修情况"
        }
],
"count": 0
}
```

### 某一字典类型的所有字典项目列表

> [GET] `/api/dic/dicItermListAll/{dicCode}` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |
| dicCode | String | true | 字典类型码 |

#### Sample
```
curl  -X GET -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' 'https://scrats.cn/rent/api/dic/dicItermListAll/0001'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": [
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "1001",
        "dicCode": "0001",
        "value": "床",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "1002",
        "dicCode": "0001",
        "value": "WIFI",
        "unit": ""
        }
],
"count": 0
}
```

### 所有额外收费项

> [GET] `/api/dic/extrasAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  -X GET -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' 'https://scrats.cn/rent/api/dic/extrasAll'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": [
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "4001",
        "dicCode": "0004",
        "value": "水费",
        "unit": "吨"
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "4002",
        "dicCode": "0004",
        "value": "电费",
        "unit": "度"
        }
],
"count": 0
}
```

### 所有配套设施

> [GET] `/api/dic/facilitiestAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  -X GET -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' 'https://scrats.cn/rent/api/dic/facilitiestAll'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": [
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "1001",
        "dicCode": "0001",
        "value": "床",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "1002",
        "dicCode": "0001",
        "value": "WIFI",
        "unit": ""
        }
],
"count": 0
}
```

### 所有装修情况

> [GET] `/api/dic/decorationAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  -X GET -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' 'https://scrats.cn/rent/api/dic/decorationAll'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": [
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "2001",
        "dicCode": "0002",
        "value": "毛胚",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "2002",
        "dicCode": "0002",
        "value": "简装",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "2003",
        "dicCode": "0002",
        "value": "精装",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "2004",
        "dicCode": "0002",
        "value": "豪装",
        "unit": ""
        }
],
"count": 0
}
```

### 所有房间朝向

> [GET] `/api/dic/orientationAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  -X GET -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' 'https://scrats.cn/rent/api/dic/orientationAll'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": [
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "3001",
        "dicCode": "0003",
        "value": "东",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "3002",
        "dicCode": "0003",
        "value": "南",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "3003",
        "dicCode": "0003",
        "value": "西",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "3004",
        "dicCode": "0003",
        "value": "北",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "3005",
        "dicCode": "0003",
        "value": "东南",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "3006",
        "dicCode": "0003",
        "value": "东北",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "3007",
        "dicCode": "0003",
        "value": "西南",
        "unit": ""
        },
        {
        "createTs": 1529661151,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "3008",
        "dicCode": "0003",
        "value": "西北",
        "unit": ""
        }
],
"count": 0
}
```

### 所有押金项

> [GET] `/api/dic/depositAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  -X GET -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' 'https://scrats.cn/rent/api/dic/depositAll'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": [
        {
        "createTs": 1530079051,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "5001",
        "dicCode": "0005",
        "value": "租金",
        "unit": "月"
        },
        {
        "createTs": 1530079051,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "5002",
        "dicCode": "0005",
        "value": "房间钥匙",
        "unit": "把"
        },
        {
        "createTs": 1530079051,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "dicItermCode": "5003",
        "dicCode": "0005",
        "value": "门禁钥匙",
        "unit": "个"
        }
],
"count": 0
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
curl -X POST -H 'Accept: application/json' -H 'Content-type: application/json' -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' -d '{"page": 1, "rows": 10, "buildingId": 1, "name": "宝山新村二区28栋"}' 'https://scrats.cn/rent/api/building/list'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": [
        {
        "createTs": 123,
        "updateTs": 1530079150343,
        "remark": "",
        "deleteTs": 0,
        "buildingId": 1,
        "name": "宝山新村二区28栋",
        "rooms": 2,
        "roomAble": 0,
        "facilities": "1001,1009,1011,1016",
        "extraFee": "4001,4002,4009,4011",
        "deposits": "5001,5002",
        "description": "87654",
        "address": "这个是地址",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null
        },
        {
        "createTs": 1234,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 1,
        "buildingId": 2,
        "name": "宝山新村二区29栋",
        "rooms": 10,
        "roomAble": 0,
        "facilities": "",
        "extraFee": "",
        "deposits": "",
        "description": "",
        "address": "这个是地址",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null
        },
        {
        "createTs": 1528905213908,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "buildingId": 16,
        "name": "444",
        "rooms": 0,
        "roomAble": 0,
        "facilities": "",
        "extraFee": "",
        "deposits": "",
        "description": "",
        "address": "这个是地址",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null
        },
        {
        "createTs": 1528907252826,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "buildingId": 17,
        "name": "666",
        "rooms": 1,
        "roomAble": 0,
        "facilities": "",
        "extraFee": "",
        "deposits": "",
        "description": "",
        "address": "这个是地址",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null
        }
],
"count": 4
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
| facilityIds | String | false | 配套设施id数组 |
| extraIds | String | false | 额外收费项id数组 |
| depositIds | String | false | 额外收费项id数组 |
| description | String | false | 描述 |
| address | String | false | 地址 |

#### Sample
```
curl -X POST -H 'Accept: application/json' -H 'Content-type: application/json' -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' -d '{"body": {"buildingId": 1, "name": "宝山新村二区28栋"}}' 'https://scrats.cn/rent/api/building/edit'
```
#### Response
```
{
    "code": 1,
    "msg": "成功",
    "data": null,
    "count": 0
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
curl -X POST -H 'Accept: application/json' -H 'Content-type: application/json' -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' -d '{"body": {"ids": [1,2]}}' 'https://scrats.cn/rent/api/building/delete'

```
#### Response
```
{
    "code": 1,
    "msg": "成功",
    "data": null,
    "count": 0
}
```

### 获取所有房子

> [GET] `/api/building/buildingAll` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |

#### Sample
```
curl  -X GET -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' 'https://scrats.cn/rent/api/building/buildingAll'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": [
        {
        "createTs": 123,
        "updateTs": 1530079150343,
        "remark": "",
        "deleteTs": 0,
        "buildingId": 1,
        "name": "宝山新村二区28栋",
        "rooms": 2,
        "roomAble": 0,
        "facilities": "1001,1009,1011,1016",
        "extraFee": "4001,4002,4009,4011",
        "deposits": "5001,5002",
        "description": "87654",
        "address": "这个是地址",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null
        },
        {
        "createTs": 1234,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 1,
        "buildingId": 2,
        "name": "宝山新村二区29栋",
        "rooms": 10,
        "roomAble": 0,
        "facilities": "",
        "extraFee": "",
        "deposits": "",
        "description": "",
        "address": "这个是地址",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null
        },
        {
        "createTs": 1528905213908,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "buildingId": 16,
        "name": "444",
        "rooms": 0,
        "roomAble": 0,
        "facilities": "",
        "extraFee": "",
        "deposits": "",
        "description": "",
        "address": "这个是地址",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null
        },
        {
        "createTs": 1528907252826,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "buildingId": 17,
        "name": "666",
        "rooms": 0,
        "roomAble": 0,
        "facilities": "",
        "extraFee": "",
        "deposits": "",
        "description": "",
        "address": "这个是地址",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null
        },
        {
        "createTs": 1530621587378,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "buildingId": 18,
        "name": "6543",
        "rooms": 1,
        "roomAble": 1,
        "facilities": "1001,1002",
        "extraFee": "4001,4002,4003",
        "deposits": "5001,5002,5003",
        "description": "哈哈哈返回的撒风",
        "address": "这个是地址",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null
        },
        {
        "createTs": 1530623585211,
        "updateTs": 1530624351312,
        "remark": "",
        "deleteTs": 0,
        "buildingId": 19,
        "name": "fdsf",
        "rooms": 1,
        "roomAble": 0,
        "facilities": "1001",
        "extraFee": "4002",
        "deposits": "5001,5002",
        "description": "fffffff",
        "address": "sdfa",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null
        }
],
"count": 0
}
```

### 房子详情

> [GET] `/api/room/detail/{buildingId}` 

| param | type | require | description |
| --- | --- | --- | --- |

#### Sample
```
curl  -X GET -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' 'https://scrats.cn/rent/api/building/detail/1'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": {
        "createTs": 123,
        "updateTs": 1530798379181,
        "remark": "",
        "deleteTs": 0,
        "buildingId": 1,
        "name": "宝山新村二区28栋",
        "rooms": 2,
        "roomAble": 0,
        "facilities": "1001,1009,1011,1016",
        "extraFee": "4001,4002,4009,4011",
        "deposits": "5001,5002",
        "description": "87654",
        "address": "这个是地址",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": [
            {
            "createTs": 1529661151,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "1001",
            "dicCode": "0001",
            "value": "床",
            "unit": ""
            },
            {
            "createTs": 1529661151,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "1009",
            "dicCode": "0001",
            "value": "冰箱",
            "unit": ""
            },
            {
            "createTs": 1529661151,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "1011",
            "dicCode": "0001",
            "value": "电视机",
            "unit": ""
            },
            {
            "createTs": 1529661151,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "1016",
            "dicCode": "0001",
            "value": "油烟机",
            "unit": ""
            }
        ],
        "extraFeeIterm": [
            {
            "createTs": 1529661151,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "4001",
            "dicCode": "0004",
            "value": "水费",
            "unit": "吨"
            },
            {
            "createTs": 1529661151,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "4002",
            "dicCode": "0004",
            "value": "电费",
            "unit": "度"
            },
            {
            "createTs": 1529661152,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "4009",
            "dicCode": "0004",
            "value": "空调费",
            "unit": "月"
            },
            {
            "createTs": 1529661152,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "4011",
            "dicCode": "0004",
            "value": "物业费",
            "unit": "月"
            }
        ],
        "depositIterm": [
            {
            "createTs": 1530079051,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "5001",
            "dicCode": "0005",
            "value": "租金",
            "unit": "月"
            },
            {
            "createTs": 1530079051,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "5002",
            "dicCode": "0005",
            "value": "房间钥匙",
            "unit": "把"
            },
            {
            "createTs": 1530079051,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "5003",
            "dicCode": "0005",
            "value": "门禁钥匙",
            "unit": "个"
            }
        ]   
},
"count": 0
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
curl -X POST -H 'Accept: application/json' -H 'Content-type: application/json' -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' -d '{"page": 1, "rows": 10, "roomNo": "201"}' 'https://scrats.cn/rent/api/room/list/1'
curl  X POST  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 3' 'https://scrats.cn/rent/api/room/list/1'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": [
        {
        "createTs": 1529683903320,
        "updateTs": 1530079540093,
        "remark": "",
        "deleteTs": 0,
        "roomId": 3,
        "roomNo": "201",
        "living": 0,
        "bedroom": 1,
        "toilet": 1,
        "orientation": "3002",
        "decoration": "2002",
        "guaranty": 1,
        "pay": 1,
        "rentFee": 49876,
        "area": 231200,
        "description": "remark",
        "facilities": "1001,1002,1014",
        "extraFee": "4001,4002,4010,4011",
        "deposits": "5001,5002,5003",
        "rentTs": 1530431170048,
        "buildingId": 1,
        "attachmentList": null,
        "orientationName": "南",
        "decorationName": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "building": null,
        "barginList": null,
        "rentList": null
        },
        {
        "createTs": 1529684090731,
        "updateTs": 1530240736330,
        "remark": "",
        "deleteTs": 0,
        "roomId": 4,
        "roomNo": "301",
        "living": 0,
        "bedroom": 1,
        "toilet": 1,
        "orientation": "3001",
        "decoration": "2001",
        "guaranty": 1,
        "pay": 1,
        "rentFee": 45600,
        "area": 210000,
        "description": "",
        "facilities": "1001,1002,1003",
        "extraFee": "4001,4002,4003",
        "deposits": "5001,5002",
        "rentTs": 1530431170048,
        "buildingId": 1,
        "attachmentList": null,
        "orientationName": "东",
        "decorationName": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "building": null,
        "barginList": null,
        "rentList": null
        }
],
"count": 2
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
curl -X POST -H 'Accept: application/json' -H 'Content-type: application/json' -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' -d '{"body": {"roomId": 3, "roomNo": "201"}}' 'https://scrats.cn/rent/api/room/edit'
```
#### Response
```
{
    "code": 1,
    "msg": "成功",
    "data": null,
    "count": 0
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
curl -X POST -H 'Accept: application/json' -H 'Content-type: application/json' -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' -d '{"body": {"ids": [1,2]}}' 'https://scrats.cn/rent/api/room/delete'
```
#### Response
```
{
    "code": 1,
    "msg": "成功",
    "data": null,
    "count": 0
}
```

### 房间详情

> [GET] `/api/room/detail/{roomId}` 

| param | type | require | description |
| --- | --- | --- | --- |

#### Sample
```
curl  -X GET -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' 'https://scrats.cn/rent/api/room/detail/3'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": {
        "createTs": 1529683903320,
        "updateTs": 1530799534221,
        "remark": "",
        "deleteTs": 0,
        "roomId": 3,
        "roomNo": "201",
        "living": 0,
        "bedroom": 1,
        "toilet": 1,
        "orientation": "3002",
        "decoration": "2002",
        "guaranty": 1,
        "pay": 1,
        "rentFee": 49876,
        "area": 231200,
        "description": "remark",
        "facilities": "1001,1002,1014",
        "extraFee": "4001,4002,4010,4011",
        "deposits": "5001,5002,5003",
        "rentTs": 1530431170048,
        "buildingId": 1,
        "attachmentList": null,
        "orientationName": "南",
        "decorationName": "简装",
        "facilitiesIterm": [
            {
            "createTs": 1529661151,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "1001",
            "dicCode": "0001",
            "value": "床",
            "unit": ""
            },
            {
            "createTs": 1529661151,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "1002",
            "dicCode": "0001",
            "value": "WIFI",
            "unit": ""
            },
            {
            "createTs": 1529661151,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "1014",
            "dicCode": "0001",
            "value": "餐桌",
            "unit": ""
            }
        ],
        "extraFeeIterm": [
            {
            "createTs": 1529661151,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "4001",
            "dicCode": "0004",
            "value": "水费",
            "unit": "吨"
            },
            {
            "createTs": 1529661151,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "4002",
            "dicCode": "0004",
            "value": "电费",
            "unit": "度"
            },
            {
            "createTs": 1529661152,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "4010",
            "dicCode": "0004",
            "value": "卫生费",
            "unit": "月"
            },
            {
            "createTs": 1529661152,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "4011",
            "dicCode": "0004",
            "value": "物业费",
            "unit": "月"
            }
        ],
        "depositIterm": [
            {
            "createTs": 1530079051,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "5001",
            "dicCode": "0005",
            "value": "租金",
            "unit": "月"
            },
            {
            "createTs": 1530079051,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "5002",
            "dicCode": "0005",
            "value": "房间钥匙",
            "unit": "把"
            },
            {
            "createTs": 1530079051,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "dicItermCode": "5003",
            "dicCode": "0005",
            "value": "门禁钥匙",
            "unit": "个"
            }
        ],
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "building": {
        "createTs": 123,
        "updateTs": 1530798379181,
        "remark": "",
        "deleteTs": 0,
        "buildingId": 1,
        "name": "宝山新村二区28栋",
        "rooms": 2,
        "roomAble": 0,
        "facilities": "1001,1009,1011,1016",
        "extraFee": "4001,4002,4009,4011",
        "deposits": "5001,5002",
        "description": "87654",
        "address": "这个是地址",
        "attachmentList": null,
        "facilityIds": null,
        "extraIds": null,
        "depositIds": null,
        "facilitiesIterm": null,
        "extraFeeIterm": null,
        "depositIterm": null
        },
        "barginList": null,
        "rentList": null
        },
"count": 0
}
```

### 房间所有租客

> [GET] `/api/room/renterAll/{roomId}` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | token |
| userId | Integer | true | userId |
| roomId | Integer | true | 房间ID |

#### Sample
```
curl  -X GET -H 'tokenId: 0451e77616bc4f188b2003b2dc656855' -H 'userId: 3' 'https://scrats.cn/rent/api/room/renterAll/3'
```
#### Response
```
{
"code": 1,
"msg": null,
"data": [
        {
        "roomRenterId": 1,
        "phone": "13332942354",
        "idCard": "410526199002265837",
        "user": {
            "createTs": 1529684135196,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "userId": 9,
            "type": "0",
            "name": "入住1",
            "sex": "0",
            "age": 0,
            "avatar": "",
            "qq": "",
            "wechat": "",
            "email": "",
            "profession": "",
            "hometown": "",
            "address": "",
            "accountId": 9,
            "sexName": "保密",
            "typeName": "租客"
            }
        }
],
"count": 0
}
```

房东
2，，修改租客，租客详情
3，合同详情
4，账单结算，账单详情

小程序

## 小程序

公共Header

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | 登录获取的tokenId |
| userId | Integer | true | userId放在header中 |


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

### 小程序注册租户

> [POST] **application/json** `/api/renter/snsRegist` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | tokenId |
| name | String | true | 姓名 |
| phone | String | true | 手机号 |
| idCard | String | true | 身份证号 |

#### Sample

```
curl -X POST -H 'Accept: application/json' -H 'Content-type: application/json' -d '{"tokenId": "399c6d05741f4ce2a7cff52fbb4dc6ff", "body": {"name": "test", "phone": "13332965432", "idCard": "410526198902265817"}}' 'https://scrats.cn/rent/api/renter/snsRegist'
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

### 小程序入住

> [GET] `/api/renter/bindRoom/{roomId}` 

| param | type | require | description |
| --- | --- | --- | --- |
| tokenId | String | true | 登录获取的tokenId，不要放在header中 |
| openid | String | true | 登录时获取的openid |
| roomId | Integer | true | 待绑定的房间Id |

#### Sample
```
curl  X GET  H 'Content type: application/json'  H 'tokenId: 399c6d05741f4ce2a7cff52fbb4dc6ff'  H 'userId: 9' 'https://scrats.cn/rent/api/renter/bindRoom/5'
```

#### Response

```
{
    "code": 1,
    "msg": "成功",
    "data": null,
    "count": 0
}
```

### 获取房间列表

> [GET] `/api/renter/roomList` 

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

> [GET] `/api/renter/bargin/{roomId}` 

| param | type | require | description |
| --- | --- | --- | --- |
| roomId | Integer | true | 房间ID |

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

> [GET] `/api/renter/deposit/{roomId}` 

| param | type | require | description |
| --- | --- | --- | --- |
| roomId | Integer | true | 房间ID |

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

> [GET] `/api/renter/unpay/{roomId}` 

| param | type | require | description |
| --- | --- | --- | --- |
| roomId | Integer | true | 房间ID |

#### Sample
```
curl 'https://scrats.cn/rent/api/renter/unpay/3' -H 'Pragma: no-cache' -H 'Accept-Encoding: gzip, deflate, br' -H 'User-Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1 wechatdevtools/1.02.1804120 MicroMessenger/6.5.7 Language/zh_CN webview/' -H 'content-type: application/json' -H 'Accept: */*' -H 'Cache-Control: no-cache' -H 'userId: 9' -H 'Referer: https://servicewechat.com/wxba42a8644a5548fd/devtools/page-frame.html' -H 'Connection: keep-alive' -H 'tokenId: 58115c918cd444bcae3679578e64e91c' --compressed | jq
```
#### Response
```
{
  "code": 1,
  "msg": null,
  "data": [
    {
      "rentIterms": [
        {
          "createTs": 543,
          "updateTs": 0,
          "remark": "",
          "deleteTs": 0,
          "rentItermId": 1,
          "rentId": 1,
          "barginExtraId": 0,
          "value": "房租",
          "price": 40000,
          "unit": "月",
          "number": 1,
          "money": 40000,
          "description": ""
        },
        {
          "createTs": 6543,
          "updateTs": 0,
          "remark": "",
          "deleteTs": 0,
          "rentItermId": 2,
          "rentId": 1,
          "barginExtraId": 1,
          "value": "水费",
          "price": 700,
          "unit": "方",
          "number": 5,
          "money": 3500,
          "description": ""
        },
        {
          "createTs": 87654,
          "updateTs": 0,
          "remark": "",
          "deleteTs": 0,
          "rentItermId": 3,
          "rentId": 1,
          "barginExtraId": 2,
          "value": "电费",
          "price": 120,
          "unit": "度",
          "number": 100,
          "money": 12000,
          "description": ""
        },
        {
          "createTs": 5443,
          "updateTs": 0,
          "remark": "",
          "deleteTs": 0,
          "rentItermId": 4,
          "rentId": 1,
          "barginExtraId": 3,
          "value": "卫生费",
          "price": 1000,
          "unit": "月",
          "number": 1,
          "money": 1000,
          "description": ""
        }
      ],
      "rent": {
        "createTs": 876543,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "rentId": 1,
        "rentNo": "999999999",
        "rentMonth": "201805",
        "fee": 56500,
        "count": 0,
        "realFee": 56500,
        "payTs": 0,
        "payNo": "",
        "channel": "99",
        "roomId": 3
      }
    },
    {
      "rentIterms": [
        {
          "createTs": 1530550719935,
          "updateTs": 0,
          "remark": "",
          "deleteTs": 0,
          "rentItermId": 20,
          "rentId": 7,
          "barginExtraId": 1,
          "value": "水费",
          "price": 700,
          "unit": "吨",
          "number": 22,
          "money": 15400,
          "description": ""
        },
        {
          "createTs": 1530550719935,
          "updateTs": 0,
          "remark": "",
          "deleteTs": 0,
          "rentItermId": 21,
          "rentId": 7,
          "barginExtraId": 2,
          "value": "电费",
          "price": 120,
          "unit": "度",
          "number": 13,
          "money": 1560,
          "description": ""
        },
        {
          "createTs": 1530550719935,
          "updateTs": 0,
          "remark": "",
          "deleteTs": 0,
          "rentItermId": 22,
          "rentId": 7,
          "barginExtraId": 3,
          "value": "卫生费",
          "price": 1000,
          "unit": "月",
          "number": 1,
          "money": 1000,
          "description": ""
        }
      ],
      "rent": {
        "createTs": 1530550719935,
        "updateTs": 0,
        "remark": "",
        "deleteTs": 0,
        "rentId": 7,
        "rentNo": "haozu-rent-201807p-1530550719935",
        "rentMonth": "201807",
        "fee": 17960,
        "count": 0,
        "realFee": 17960,
        "payTs": 0,
        "payNo": "",
        "channel": "99",
        "roomId": 3
      }
    }
  ],
  "count": 0
}
```

### 获取某一个房间额外收费项的明细数据

> [GET] `/api/renter/extraHistory/{barginExtraId}` 

| param | type | require | description |
| --- | --- | --- | --- |
| barginExtraId | Integer | true | 未缴费ID |

#### Sample
```
curl 'https://scrats.cn/rent/api/renter/extraHistory/1' -H 'Pragma: no-cache' -H 'Accept-Encoding: gzip, deflate, br' -H 'User-Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1 wechatdevtools/1.02.1804120 MicroMessenger/6.5.7 Language/zh_CN webview/' -H 'content-type: application/json' -H 'Accept: */*' -H 'Cache-Control: no-cache' -H 'userId: 9' -H 'Referer: https://servicewechat.com/wxba42a8644a5548fd/devtools/page-frame.html' -H 'Connection: keep-alive' -H 'tokenId: 58115c918cd444bcae3679578e64e91c' --compressed | jq
```
#### Response
```
{
  "code": 1,
  "msg": null,
  "data": [
    {
      "createTs": 76543,
      "updateTs": 0,
      "remark": "",
      "deleteTs": 0,
      "extraHistoryId": 1,
      "roomId": 9,
      "count": 87,
      "month": "201806",
      "dicItermCode": "4001",
      "barginExtraId": 1
    },
    {
      "createTs": 1530550719935,
      "updateTs": 0,
      "remark": "",
      "deleteTs": 0,
      "extraHistoryId": 17,
      "roomId": 3,
      "count": 109,
      "month": "201807",
      "dicItermCode": "4001",
      "barginExtraId": 1
    }
  ],
  "count": 0
}
```

### 获取某一个房间的所有房租

> [GET] `/api/renter/rent/{roomId}?page=1&rows=10` 

| param | type | require | description |
| --- | --- | --- | --- |
| roomId | Integer | true | 未缴费ID |
| page | Integer | false | 分页显示页码 |
| rows | Integer | false | 每页显示数量 |

#### Sample
```
curl  -X GET -H 'tokenId: 47beb23ed45744ae94abf92e3efeb95d' -H 'userId: 9' 'https://scrats.cn/rent/api/renter/rent/3'
```
#### Response
```
{
    "code": 1,
    "msg": null,
    "data": {
        "total": 2,
        "list": [
            {
                "createTs": 1531631108000,
                "updateTs": 0,
                "remark": "",
                "deleteTs": 0,
                "rentId": 1,
                "rentNo": "haozu-rent-201807e-1531631108000",
                "rentMonth": "201807",
                "fee": 73836,
                "count": 0,
                "realFee": 73836,
                "payTs": 0,
                "payNo": "",
                "channel": "99",
                "roomId": 3,
                "buildingId": 1,
                "barginId": 8
            },
            {
                "createTs": 1531631154187,
                "updateTs": 0,
                "remark": "",
                "deleteTs": 0,
                "rentId": 2,
                "rentNo": "haozu-rent-201806j-1531631154187",
                "rentMonth": "201806",
                "fee": 760296,
                "count": 0,
                "realFee": 760296,
                "payTs": 0,
                "payNo": "",
                "channel": "99",
                "roomId": 3,
                "buildingId": 1,
                "barginId": 8
            }
        ],
        "pageNum": 1,
        "pageSize": 10,
        "pages": 1,
        "size": 2
    },
    "count": 0
}
```

### 获取某一个房租的明细

> [GET] `/api/renter/rentDetail/{rentId}?` 

| param | type | require | description |
| --- | --- | --- | --- |
| rentId | Integer | true | 房租ID |

#### Sample
```
curl  -X GET -H 'tokenId: 47beb23ed45744ae94abf92e3efeb95d' -H 'userId: 9' 'https://scrats.cn/rent/api/renter/rentDetail/1'
```
#### Response
```
{
    "code": 1,
    "msg": null,
    "data": [
        {
            "createTs": 1531631154187,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "rentItermId": 24,
            "rentId": 2,
            "barginExtraId": 15,
            "value": "水费",
            "price": 800,
            "unit": "吨",
            "number": 845,
            "money": 676000,
            "description": ""
        },
        {
            "createTs": 1531631154187,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "rentItermId": 25,
            "rentId": 2,
            "barginExtraId": 16,
            "value": "电费",
            "price": 130,
            "unit": "度",
            "number": 234,
            "money": 30420,
            "description": ""
        },
        {
            "createTs": 1531631154187,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "rentItermId": 26,
            "rentId": 2,
            "barginExtraId": 17,
            "value": "卫生费",
            "price": 1000,
            "unit": "月",
            "number": 1,
            "money": 1000,
            "description": ""
        },
        {
            "createTs": 1531631154187,
            "updateTs": 0,
            "remark": "",
            "deleteTs": 0,
            "rentItermId": 27,
            "rentId": 2,
            "barginExtraId": 18,
            "value": "物业费",
            "price": 3000,
            "unit": "月",
            "number": 1,
            "money": 3000,
            "description": ""
        }
    ],
    "count": 0
}
```
上传身份信息

查看水电
