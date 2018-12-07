# dev
## 电商项目-需求分析
### 核心-购买
#### 一、用户模块
##### 登录
##### 注册
##### 忘记密码
##### 获取用户信息
##### 修改密码
##### 登出
#### 二、商品模块
##### 后台
###### 添加商品
###### 修改商品
###### 删除商品
###### 商品上下架
###### 查看商品
##### 前台（门户）
###### 搜索商品
###### 查看商品详情
#### 三、类别模块
##### 添加类别
##### 修改类别
##### 删除类别
##### 查看类别
###### 查看子类
###### 查看所有类别
#### 四、购物车模块
##### 添加购物车
##### 修改购物车中的某个商品的数量
##### 删除购物车商品
##### 全选/取消全选
##### 单选/取消单选
##### 查看购物车中的商品数量
#### 五、地址模块
##### 添加地址
##### 修改地址
##### 删除地址
##### 查看地址
#### 六、订单模块
##### 前台
###### 下订单
###### 查看订单
###### 取消订单
###### 订单详情
##### 后台
###### 订单列表
###### 订单详情
###### 发货
#### 七、支付模块
##### 支付宝支付
###### 支付
###### 支付回调
###### 查看支付状态
#### 八、线上部署
##### 阿里云部署
------------------------------------
## 数据库设计
### 创建数据库
```
 create database ilearnshopping;
 use ilearnshopping;
```
### 用户表
```
create table neuedu_user(
 `id` int(11) nt null auto_increment comment '用户id',
 `username` varchar(50) not null comment '用户名',
 `password` varchar(50) not null commit '密码',
 `email` varchar(50) not null commit '邮箱',
 `phone` varchar(11) not null commit '联系电话',
 `question` varchar(100) not null commit '密保问题',
 `answer` varchar(100) not null commit '答案',
 `role` int(4) not null default 0 commit '用户角色',
 `create_time` datetime commit '创建时间',
 `update_time` datetime commit '修改时间',
  PRIMARY KEY(`id`),
  UNIQUE KEY `user_name_index`(`username`) USING BTREE 
 )ENGINE=InnoDB DEFAULT CHARSET=UTF8
```
### 类别表
```
create table neuedu_category(
 `id` int(11) not null auto_increment comment '类别id',
 `parent_id` int(11) not null default 0 comment '父类id',
 `name` varchar(50) not null comment '类别名称',
 `status` int(4) default 1 comment '类别状态: 1正常 0废弃',
 `create_time` datetime comment '创建时间',
 `update_time` datetime comment '修改时间',
   PRIMARY KEY(`id`)
   )ENGINE=InnoDB DEFAULT CHARSET=UTF8
```
### 商品表
```
create table neuedu_product(
 `id` int(11) not null auto_increment comment '商品id',
 `category_id` int(11) not null comment '商品所属的类别id，值引用的是类别表的商品id',
 `name` varchar(100) not null comment '商品名称',
 `detail` text comment '商品详情',
 `subtitle` varchar(200) comment '商品副标题',
 `main_image` varchar(100) comment '商品主图',
 `sub_image` varchar(200) comment '商品子图',
 `price` decimal(20,2) not null comment '商品价格，总共20位，小数2位，整数18位',
 `stock` int(11) comment '商品库存',
 `status` int(6) default 1 comment '商品状态:1在售 2下架 3删除',
 `create_time` datetime comment '创建时间',
 `update_time` datetime comment '修改时间',
  PRIMARY KEY(`id`)
 )ENGINE=InnoDB DEFAULT CHARSET=UTF8
```
### 购物车表
```
create table neuedu_cart(
 `id` int(11) not null auto_increment comment '购物车id',
 `user_id` int(11) not null comment '用户id',
 `product_id` int(11) not null comment '商品id',
 `quantity` int(11) not null comment '购买数量',
 `checked` int(4) default 1 comment '1选中 0未选中',
 `create_time` datetime comment '创建时间',
 `update_time` datetime comment '修改时间',
  PRIMARY KEY(`id`),
  key `user_id_index`(`user_id`) USING BTREE
  )ENGINE=InnoDB DEFAULT CHARSET=UTF8
```
### 订单表
```
create table neuedu_order(
 `id` int(11) not null auto_increment comment '订单id,主键', 
 `order_no` bigint(20) not null comment '订单编号',
 `user_id` int (11) not null comment '用户id',
 `payment` decimal(20,2) not null comment '付款总金额,单位元，保留两位小数',
 `payment_type` int(4) not null default 1 comment '支付方式 1线上支付',
 `status` int(10) not null comment '订单状态 0已取消 10未付款 20已付款 30已发货 40已完成 50已关闭',
 `shipping_id` int(11) not null comment '收货地址id',
 `postage` int(10) not null default 0 comment '运费',
 `payment_time` datetime default null comment '已付款时间',
 `send_time` datetime default null comment '已发货时间',
 `close_time` datetime default null comment '已关闭时间',
 `end_time` datetime default null comment '已结束时间',
 `create_time` datetime default null comment '已创建时间',
 `update_time` datetime default null comment '更新时间',
  PRIMARY KEY(`id`),
  UNIQUE KEY `order_no_index`(`order_no`) USING BTREE
 )ENGINE=InnoDB DEFAULT CHARSET=UTF8
```
### 订单明细表
```
create table neuedu_order_item(
 `id` int(11) not null auto_increment comment '订单明细id', 
 `order_no` bigint(20) not null comment '订单编号',
 `user_id` int (11) not null comment '用户id',
 `product_id` int(11) not null comment '商品id',
 `product_name` varchar(100) not null comment '商品名称',
 `product_image` varchar(100) comment '商品主图',
 `current_unit_price` decimal(20,2) not null comment '下单时商品的价格，元为单位，保留两位小数',
 `quantity` int(10) not null comment '商品购买的数量',
 `total_price` decimal(20,2) not null comment '商品的总价格，元为单位，保留两位小数',
 `create_time` datetime default null comment '已创建时间',
 `update_time` datetime default null comment '更新时间',
  PRIMARY KEY(`id`),
  KEY `order_no_index`(`order_no`) USING BTREE,
  KEY `order_no_user_id_index`(`order_no`,`user_id`) USING BTREE,
 )ENGINE=InnoDB DEFAULT CHARSET=UTF8
```
### 支付表
```
create table neuedu_payinfo(
 `id` int(11) not null auto_increment comment '主键', 
 `order_no` bigint(20) not null comment '订单编号',
 `user_id` int (11) not null comment '用户id',
 `pay_platform` int(4) not null default 1 comment '1支付宝 2微信',
 `platform_status` varchar(50) comment '支付状态',
 `platform_number` varchar(100) comment '流水号',
 `create_time` datetime default null comment '已创建时间',
 `update_time` datetime default null comment '更新时间',
  PRIMARY KEY(`id`)
 )ENGINE=InnoDB DEFAULT CHARSET=UTF8
```
### 地址表
```
create table neuedu_shipping(
 `id` int(11) not null auto_increment comment '地址id', 
 `user_id` int (11) not null comment '用户id',
 `receiver_name` varchar(20) default null comment '收货姓名', 
 `receiver_phone` varchar(20) default null comment '收货固定电话', 
 `receiver_mobile` varchar(20) default null comment '收货移动电话', 
 `receiver_province` varchar(20) default null comment '省份', 
 `receiver_city` varchar(20) default null comment '城市', 
 `receiver_district` varchar(20) default null comment '区/县', 
 `receiver_address` varchar(200) default null comment '详细地址', 
 `receiver_zip` varchar(6) default null comment '邮编', 
 `create_time` datetime default null comment '创建时间',
 `update_time` datetime default null comment '最后一次更新时间',
  PRIMARY KEY(`id`)
 )ENGINE=InnoDB DEFAULT CHARSET=UTF8
```
### 项目架构--四层架构
```
 视图层：页面
 控制层controller：接收视图层传递的数据，负责调用业务逻辑层
 业务逻辑层service：处理业务逻辑 （接口和实现类）
 Dao层：和数据库交互，主要对数据库进行增删改查
 视图层调用控制层，控制层调用业务逻辑层，业务逻辑层调用Dao层
```
### mybatis-generator插件
```
 依赖jar包：mysql驱动包和mybatis-generator依赖包
 插件mybatis-generator-maven-plugin
 配置文件(db.properties)：封装数据库的参数
 配置generatorConfig.xml
 生成Dao接口，实体类，映射文件
```
### 搭建ssm框架
```
 加载依赖的jar包
 配置spring.xml
 配置springmvc.xml
 配置mybatis-config.xml
 新版本的web.xml替换旧版本
 
 配置tomcat
 
 @RestController和@Controller的区别
 @RestController返回前端页面是json数据
 @Controller前端显示页面
 @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用
```
### 知识点
```
对数据库中的密码通过MD5加密
    public static String getMD5Code(String strObj) {
    String resultString = null;
    try {
      resultString = new String(strObj);
      MessageDigest md = MessageDigest.getInstance("MD5");
      // md.digest() 该函数返回值为存放哈希值结果的byte数组
      resultString = byteToString(md.digest(strObj.getBytes()));
    } catch (NoSuchAlgorithmException ex) {
      ex.printStackTrace();
    }
    return resultString;
  }
调用getMD5Code方法对密码加密
MD5加密特点：不可逆的加密算法(通过MD5加密变成密文之后，不能通过密文获取到原密码)
注册密码变成密文的之后，登录密码也应进行加密，否则登录时输入明文密码会显示密码输入错误

定义响应状态码(枚举类型enum)：
    PARAM_EMPTY(2,"参数为空"),
    EXISTS_USERNAME(3,"用户名已经存在"),
    EXISTS_EMAIL(4,"邮箱已经存在"),
    NOT_EXISTS_USERNAME(5,"用户名不存在"),
    USER_NOT_LOGIN(6,"用户未登录"),
参数：status(状态)、msg(消息)
提供有参和无参的构造函数以及set和get方法

注册接口：
注册接口中的参数：String username、String password、String email、String phone、String question、String answer
多参数用数据绑定接收(对象形式)
Impl实现类实现Service中的注册方法，实现类处理注册的业务逻辑，控制层调用业务逻辑层把数据返回给视图层
步骤一：参数非空的校验
步骤二：判断用户名是否存在
步骤三：判断邮箱是否存在
在dao层中书写校验邮箱的方法(与数据库做交互)，映射类中写sql语句，实现类处理具体的业务逻辑
步骤四：注册
步骤五：返回结果

用户只有登录之后才能对购物车进行操作，购物车接口如何判断用户已经登录？
把用户登录的信息保存到session中
往session中保存数据：session.setAttribute(Const.CURRENTUSER,serverResponse.getData())
Const.CURRENTUSER 代表用户表中的字段名
serverResponse.getData() 代表字段中所对应的值
通过HttpSession获取session当中的用户信息

检查用户名或邮箱是否有效的接口
参数：String str、String type，str对应用户名或邮箱的值，type对应的是username或email
什么时候使用？注册接口在输入用户名或邮箱之后会反馈用户名或邮箱是否已存在，输入完成之后通过ajax异步加载调用接口返回数据，防止恶意调用接口
在service中定义检查用户名和密码的方法，在实现类中处理业务逻辑
步骤一：参数的非空校验
通过调用StringUtils.isBlank()判断
步骤二：判断用户名或邮箱是否存在
步骤三：返回结果

重构登录和注册接口
登录的步骤二检查用户名是否存在调用检查用户名或邮箱是否有效接口中的check_valid()

获取当前登录的用户的信息
登录之后用户信息保存到了session当中，直接操作session获取信息
如何判断对象的类型是否是某一类型：使用instanceof
```
