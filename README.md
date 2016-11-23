# OnlineShoppingSystem

本仓库下存放网上购物系统源代码。

---

<!-- TOC -->

- [OnlineShoppingSystem](#onlineshoppingsystem)
    - [工程目录结构简介](#%E5%B7%A5%E7%A8%8B%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84%E7%AE%80%E4%BB%8B)
    - [其他](#%E5%85%B6%E4%BB%96)

<!-- /TOC -->

---

## 工程目录结构简介
```
├─config
│      database-config.xml                                      //数据库配置文件
│
├─src
│  └─com
│      └─groupnine
│          └─oss
│              ├─admin                                          //管理员相关
│              │  ├─action                                      //控制层
│              │  │      GetUnsolvedTransactionAction.java
│              │  │
│              │  ├─dao                                         //数据库访问层
│              │  │      AdminDao.java
│              │  │      AdminDaoImpl.java
│              │  │
│              │  ├─entity                                      //用于后台传递数据的实体
│              │  │      Admin.java
│              │  │      Transaction.java
│              │  │
│              │  └─service                                     //服务层
│              │          AdminService.java
│              │          AdminServiceImpl.java
│              │
│              ├─demo                                           //该包下存放相关的示例代码，可以在编码时参考，
│              │  ├─action                                      //若与 mealOrdering 仓库中代码有冲突，以本包中代码为准
│              │  │      UploadImageAction.java
│              │  │
│              │  ├─dao
│              │  │      DemoDao.java
│              │  │
│              │  └─service
│              │          ImageUpload.java
│              │
│              ├─filter                                         //项目中用到的过滤器
│              │      EncodingFilter.java
│              │      LoginFilter.java
│              │
│              ├─pub                                            //三个用户角色都能用到的一些类
│              │  └─entity
│              │          GoodsInOrder.java 
│              │          Order.java                            
│              │
│              ├─seller                                         //商家相关
│              │  ├─action 
│              │  │      GetShopInfoAction.java
│              │  │
│              │  ├─dao
│              │  │      SellerDao.java
│              │  │      SellerDaoImpl.java
│              │  │
│              │  ├─entity
│              │  │      Goods.java
│              │  │      GoodsAttr.java
│              │  │      GoodsImage.java
│              │  │      Shop.java
│              │  │
│              │  └─service
│              │          SellerService.java
│              │          SellerServiceImpl.java
│              │
│              ├─user                                           //用户相关
│              │  ├─action
│              │  │      GetUserInfoAction.java
│              │  │
│              │  ├─dao
│              │  │      UserDao.java
│              │  │      UserDaoImpl.java
│              │  │
│              │  ├─entity
│              │  │      GoodsInShoppingCart.java
│              │  │      Receiver.java
│              │  │      User.java
│              │  │
│              │  └─service
│              │          UserService.java
│              │          UserServiceImpl.java
│              │
│              └─util                                           // 项目中用到的一些工具
│                      DBConfig.java                            // DBUtil 的辅助类，用于从 xml 文件中读出配置信息
│                      DBUtil.java                              // 用于获取数据库连接
│                      Page.java                                // 一个泛型类，用于分页获取数据
│                      StringUtil.java                          // 一个字符串的工具类，只有一个方法，有需要就用。
│ 
└─WebContent                                                    
   │  index.jsp                                                 //网站首页
   │
   ├─demopages                                                  //示例代码，与 demo 包下的类协同工作
   │      imageUpload.jsp
   │
   ├─META-INF
   │      MANIFEST.MF
   │
   ├─pages                                                      //网站其他页面
   │  │  user-signup.jsp                                        //用户注册页
   │  │
   │  ├─core                                                    //与登录注册、后台无关的其他页面
   │  │      shopping-cart.jsp
   │  │
   │  ├─home                                                    //存放后台管理页面
   │  │      admin.jsp
   │  │      seller.jsp
   │  │      user.jsp
   │  │
   │  └─login                                                   //登录页面
   │          admin.jsp
   │          user.jsp
   │
   ├─style                                                      //样式文件
   │  ├─css
   │  │
   │  ├─fonts
   │  │
   │  └─js
   │
   └─WEB-INF 
       │  web.xml
       │
       └─lib                                                    //项目中用到的所有第三方库文件, 可能不全，少的自行补充。
```

## 其他

- 以上目录结构只是初步的框架，如需其他类和文件，直接添加到相应文件夹即可。
- 因为时间紧张，所以实体类设计的可能不够好，如需修改的话自行修改自己负责的部分。