package cn.tiiis.restfulapi;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value="/user")
public class UserController {

    static Map<Long,User> users = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value="/",method = RequestMethod.GET)
    public List<User> getUsers(){
        // 处理"/user/"的GET请求，用来获取用户列表
        List<User> res = new ArrayList<>(users.values());
        return res;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="/",method = RequestMethod.POST)
    public String saveUser(@RequestBody User user){
        // 处理"/user/"的POST请求，用来创建User
        users.put(user.getId(),user);
        return "ok";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long" ,paramType="path")
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        // 处理"/user/{id}"的GET请求，用来获取url中id值的User信息
        return users.get(id);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long" ,paramType="path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public String updateUser(@PathVariable Long id,@RequestBody User user){
        // 处理"/user/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "ok";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long" ,paramType="path")
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public String delUser(@PathVariable Long id){
        // 处理"/user/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "ok";
    }

}
