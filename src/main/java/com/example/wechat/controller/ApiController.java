package com.example.wechat.controller;

import com.example.wechat.model.ApiResponse;
import com.example.wechat.model.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * API 控制器
 * 实现简单的 GET 和 POST 接口
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {

    /**
     * GET 接口示例：获取用户信息
     * 请求路径：GET /api/user?userId=123
     */
    @GetMapping("/user")
    public ApiResponse<Map<String, Object>> getUser(@RequestParam(required = false) String userId) {
        Map<String, Object> userInfo = new HashMap<>();

        // 如果没有传 userId，使用默认值
        if (userId == null || userId.isEmpty()) {
            userId = "10001";
        }

        userInfo.put("userId", userId);
        userInfo.put("username", "test_user");
        userInfo.put("nickname", "测试用户");
        userInfo.put("avatar", "https://example.com/avatar.png");
        userInfo.put("createTime", System.currentTimeMillis());

        return ApiResponse.success("获取用户信息成功", userInfo);
    }

    /**
     * GET 接口示例：健康检查
     * 请求路径：GET /api/health
     */
    @GetMapping("/health")
    public ApiResponse<String> health() {
        return ApiResponse.success("服务运行正常", "OK");
    }

    /**
     * GET 接口示例：获取系统信息
     * 请求路径：GET /api/info
     */
    @GetMapping("/info")
    public ApiResponse<Map<String, Object>> getInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("appName", "微信小程序后端");
        info.put("version", "1.0.0");
        info.put("author", "Vibe Coding");
        info.put("javaVersion", System.getProperty("java.version"));
        info.put("osName", System.getProperty("os.name"));

        return ApiResponse.success(info);
    }

    /**
     * POST 接口示例：用户登录
     * 请求路径：POST /api/login
     * 请求体：{"username": "admin", "password": "123456"}
     */
    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody UserRequest request) {
        Map<String, Object> result = new HashMap<>();

        // 简单的模拟登录逻辑
        if ("admin".equals(request.getUsername()) && "123456".equals(request.getPassword())) {
            result.put("token", "mock_token_" + System.currentTimeMillis());
            result.put("userId", "10001");
            result.put("username", request.getUsername());
            result.put("expireTime", System.currentTimeMillis() + 7200000); // 2小时后过期

            return ApiResponse.success("登录成功", result);
        } else {
            return ApiResponse.error("用户名或密码错误");
        }
    }

    /**
     * POST 接口示例：用户注册
     * 请求路径：POST /api/register
     * 请求体：{"username": "newuser", "password": "123456", "nickname": "新用户"}
     */
    @PostMapping("/register")
    public ApiResponse<Map<String, Object>> register(@RequestBody UserRequest request) {
        Map<String, Object> result = new HashMap<>();

        // 简单的参数校验
        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            return ApiResponse.error("用户名不能为空");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return ApiResponse.error("密码不能为空");
        }

        // 模拟注册逻辑
        result.put("userId", String.valueOf(System.currentTimeMillis()));
        result.put("username", request.getUsername());
        result.put("nickname", request.getNickname() != null ? request.getNickname() : request.getUsername());
        result.put("createTime", System.currentTimeMillis());

        return ApiResponse.success("注册成功", result);
    }

    /**
     * POST 接口示例：更新用户信息
     * 请求路径：POST /api/user/update
     * 请求体：{"nickname": "新昵称", "age": 25}
     */
    @PostMapping("/user/update")
    public ApiResponse<Map<String, Object>> updateUser(@RequestBody UserRequest request) {
        Map<String, Object> result = new HashMap<>();

        result.put("userId", "10001");
        result.put("nickname", request.getNickname() != null ? request.getNickname() : "未设置");
        result.put("age", request.getAge() != null ? request.getAge() : 0);
        result.put("updateTime", System.currentTimeMillis());

        return ApiResponse.success("更新成功", result);
    }

    /**
     * POST 接口示例：数据提交
     * 请求路径：POST /api/data
     * 请求体：任意 JSON 数据
     */
    @PostMapping("/data")
    public ApiResponse<String> submitData(@RequestBody Map<String, Object> data) {
        // 模拟数据处理
        System.out.println("收到数据: " + data);
        return ApiResponse.success("数据提交成功，共收到 " + data.size() + " 个字段");
    }
}
