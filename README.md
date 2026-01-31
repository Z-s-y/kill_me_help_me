# 微信小程序后端项目

基于 Java 8 + Spring Boot 2.7.18 开发的微信小程序后端项目，提供简单的 GET 和 POST 接口。

## 技术栈

- Java 8
- Spring Boot 2.7.18
- Maven
- Lombok

## 项目结构

```
wechat-miniprogram-backend/
├── pom.xml                                    # Maven 配置文件
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/wechat/
│       │       ├── Application.java           # 应用启动类
│       │       ├── controller/
│       │       │   └── ApiController.java     # API 控制器
│       │       └── model/
│       │           ├── ApiResponse.java       # 统一响应格式
│       │           └── UserRequest.java       # 请求参数模型
│       └── resources/
│           └── application.properties         # 应用配置文件
└── README.md
```

## 接口说明

### GET 接口

#### 1. 获取用户信息
- **路径**: `GET /api/user`
- **参数**:
  - `userId` (可选): 用户ID，默认为 "10001"
- **示例**:
  ```bash
  curl http://localhost:5000/api/user?userId=123
  ```
- **返回**:
  ```json
  {
    "code": 0,
    "message": "获取用户信息成功",
    "data": {
      "userId": "123",
      "username": "test_user",
      "nickname": "测试用户",
      "avatar": "https://example.com/avatar.png",
      "createTime": 1234567890000
    }
  }
  ```

#### 2. 健康检查
- **路径**: `GET /api/health`
- **示例**:
  ```bash
  curl http://localhost:5000/api/health
  ```
- **返回**:
  ```json
  {
    "code": 0,
    "message": "success",
    "data": "OK"
  }
  ```

#### 3. 获取系统信息
- **路径**: `GET /api/info`
- **示例**:
  ```bash
  curl http://localhost:5000/api/info
  ```
- **返回**:
  ```json
  {
    "code": 0,
    "message": "success",
    "data": {
      "appName": "微信小程序后端",
      "version": "1.0.0",
      "author": "Vibe Coding",
      "javaVersion": "1.8.0_xxx",
      "osName": "Linux"
    }
  }
  ```

### POST 接口

#### 1. 用户登录
- **路径**: `POST /api/login`
- **请求体**:
  ```json
  {
    "username": "admin",
    "password": "123456"
  }
  ```
- **示例**:
  ```bash
  curl -X POST -H "Content-Type: application/json" \
    -d '{"username":"admin","password":"123456"}' \
    http://localhost:5000/api/login
  ```
- **返回**:
  ```json
  {
    "code": 0,
    "message": "登录成功",
    "data": {
      "token": "mock_token_1234567890",
      "userId": "10001",
      "username": "admin",
      "expireTime": 1234567890000
    }
  }
  ```

#### 2. 用户注册
- **路径**: `POST /api/register`
- **请求体**:
  ```json
  {
    "username": "newuser",
    "password": "123456",
    "nickname": "新用户"
  }
  ```
- **示例**:
  ```bash
  curl -X POST -H "Content-Type: application/json" \
    -d '{"username":"newuser","password":"123456","nickname":"新用户"}' \
    http://localhost:5000/api/register
  ```

#### 3. 更新用户信息
- **路径**: `POST /api/user/update`
- **请求体**:
  ```json
  {
    "nickname": "新昵称",
    "age": 25
  }
  ```

#### 4. 数据提交
- **路径**: `POST /api/data`
- **请求体**: 任意 JSON 数据
- **示例**:
  ```bash
  curl -X POST -H "Content-Type: application/json" \
    -d '{"key1":"value1","key2":"value2"}' \
    http://localhost:5000/api/data
  ```

## 快速开始

### 开发环境

1. **安装依赖**:
   ```bash
   mvn clean install
   ```

2. **启动服务**:
   ```bash
   mvn spring-boot:run
   ```

3. **访问服务**:
   - 服务地址: `http://localhost:5000`
   - 健康检查: `http://localhost:5000/api/health`

### 生产环境

1. **打包**:
   ```bash
   mvn clean package
   ```

2. **运行**:
   ```bash
   java -jar target/wechat-miniprogram-backend-1.0.0.jar --server.port=5000
   ```

## 统一响应格式

所有接口返回统一的 JSON 格式：

```json
{
  "code": 0,           // 0-成功，其他-失败
  "message": "消息",   // 响应消息
  "data": {}           // 响应数据
}
```

## 测试建议

在微信小程序中使用这些接口时，建议：

1. 在 `app.json` 中配置服务器域名（需要在小程序后台配置）
2. 使用 `wx.request()` 发起请求
3. 在开发阶段可以勾选"不校验合法域名"进行测试

### 微信小程序调用示例

```javascript
// GET 请求
wx.request({
  url: 'http://localhost:5000/api/user',
  method: 'GET',
  data: { userId: '123' },
  success(res) {
    console.log(res.data);
  }
});

// POST 请求
wx.request({
  url: 'http://localhost:5000/api/login',
  method: 'POST',
  data: {
    username: 'admin',
    password: '123456'
  },
  success(res) {
    console.log(res.data);
  }
});
```

## 注意事项

1. 生产环境需要配置 HTTPS 和合法域名
2. 实际项目中需要添加数据库连接（如 MySQL）
3. 需要实现真实的用户认证和权限管理
4. 建议添加日志记录和异常处理
5. 需要配置跨域（已在代码中添加 `@CrossOrigin` 注解）

## 扩展建议

- 添加数据库支持（Spring Data JPA + MySQL）
- 添加 Redis 缓存
- 添加 JWT Token 认证
- 添加 MyBatis Plus 简化数据库操作
- 添加 Swagger 接口文档
- 添加单元测试
