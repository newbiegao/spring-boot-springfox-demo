## Paths
### å¢å ç¨æ·å°REDISä¸­
```
POST /users/addUser
```

#### Description

èªå¨åå»ºç¨æ·å°REDISä¸­ï¼å¹¶è¿åç¨æ·ä¿¡æ¯

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity|
|201|Created|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### countUser
```
OPTIONS /users/count/{name}/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|name|name|true|string||
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|integer (int32)|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### countUser
```
DELETE /users/count/{name}/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|name|name|true|string||
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|integer (int32)|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### countUser
```
HEAD /users/count/{name}/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|name|name|true|string||
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|integer (int32)|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### countUser
```
GET /users/count/{name}/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|name|name|true|string||
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|integer (int32)|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### countUser
```
PATCH /users/count/{name}/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|name|name|true|string||
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|integer (int32)|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### countUser
```
POST /users/count/{name}/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|name|name|true|string||
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|integer (int32)|
|201|Created|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### countUser
```
PUT /users/count/{name}/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|name|name|true|string||
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|integer (int32)|
|201|Created|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### å é¤ç¨æ·
```
POST /users/deluser/{userId}
```

#### Description

ä»REDISä¸­å é¤ç¨æ·å¹¶è¿åæå

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|userId|ç¨æ·ID|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|string|
|201|Created|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### addUsers
```
OPTIONS /users/user/addusers
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### addUsers
```
DELETE /users/user/addusers
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### addUsers
```
HEAD /users/user/addusers
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### addUsers
```
GET /users/user/addusers
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### addUsers
```
PATCH /users/user/addusers
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### addUsers
```
POST /users/user/addusers
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|201|Created|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### addUsers
```
PUT /users/user/addusers
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|201|Created|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### findUserByAge
```
OPTIONS /users/user/age/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### findUserByAge
```
DELETE /users/user/age/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### findUserByAge
```
HEAD /users/user/age/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### findUserByAge
```
GET /users/user/age/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### findUserByAge
```
PATCH /users/user/age/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### findUserByAge
```
POST /users/user/age/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|201|Created|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### findUserByAge
```
PUT /users/user/age/{age}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|age|age|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|UserEntity array|
|201|Created|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### loadUserPageList
```
OPTIONS /users/user/page/{page}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|page|page|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|SimplePagedList|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### loadUserPageList
```
DELETE /users/user/page/{page}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|page|page|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|SimplePagedList|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### loadUserPageList
```
HEAD /users/user/page/{page}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|page|page|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|SimplePagedList|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### loadUserPageList
```
GET /users/user/page/{page}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|page|page|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|SimplePagedList|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### loadUserPageList
```
PATCH /users/user/page/{page}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|page|page|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|SimplePagedList|
|204|No Content|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### loadUserPageList
```
POST /users/user/page/{page}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|page|page|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|SimplePagedList|
|201|Created|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

### loadUserPageList
```
PUT /users/user/page/{page}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|page|page|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|SimplePagedList|
|201|Created|No Content|
|401|Unauthorized|No Content|
|403|Forbidden|No Content|
|404|Not Found|No Content|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* ç¨æ·APIæ¥å£

