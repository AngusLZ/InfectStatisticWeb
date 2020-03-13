## java 代码风格

- 缩进

> 缩进使用一次 tab 键

- 变量命名

> 1. 命名不允许使用中文和英文混合的方式
> 2. 命名都是以字母组成
> 3. 命名遵顺驼峰形式，特殊的名字除外
> 4. 常量名为大写
> 5. 包名，函数名使用小写，类名首字母大写

- 每行最多字符数

> 每行最多不超过 120 个 chars

- 注释规则

> 1. 类的最开头添加创建者信息
> 2. 方法内单行注释使用// 多行注释使用/\*\*/
> 3. 注释在所需注释内容的上一行
> 4. 使用中文注释

- 格式

> 1. if/for/while/switch/do 等保留字与左右括号之间都必须加空格
> 2. 任何运算符左右必须加一个空格。
> 3. 缩进采用 tab 键
> 4. 多参数的时候，参数逗号后加空格
> 5. 左大括号前不换行。
>    左大括号后换行。
>    右大括号前换行。
>    右大括号后还有 else 等代码则不换行；表示终止右大括号后必须换行。

## 前端

### html

> 缩进使用 4 个空格

> 嵌套的节点有缩进

> 属性名全小写，用中划线作为分隔符

> 属性使用双引号

> 属性顺序应该按照特定的顺序出现以保证易读性

````
     class

     id

     name

     data-*

     src, for, type, href, value , max-length, max, min, pattern

     placeholder, title, alt

     aria-*, role

     required, readonly, disabled
````

### css

> 一、以下情况不需要空格

> 1.属性名后

> 2.!important '!'后

> 3.属性值中'('后和')'前

> 4.行末不要有多余的空格

> 二、以下情况需要换行

> 1.'{'后和'}'前

> 2.每个属性独占一行

> 3.多个规则的分隔符','后

> 三、属性声明顺序

> 1.相关的属性声明按照以下顺序分组处理
````
.declaration-order {
    display: block;
    float: right;
 
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 100;
 
    border: 1px solid #e5e5e5;
    border-radius: 3px;
    width: 100px;
    height: 100px;
 
    font: normal 13px "Helvetica Neue", sans-serif;
    line-height: 1.5;
    text-align: center;
 
    color: #333;
    background-color: #f5f5f5;
 
    opacity: 1;
}
````

### JavaScript

> 一、缩进

> 缩进使用4个空格

> 二、变量命名

> 1.变量、函数、类采用驼峰式命名

> 2.变量小写、函数首字母小写、类首字母大写

> 3.常量全大写，用下划线连接

> 4.jquery对象必须以'$'开头命名

> 三、注释

> 1.单行注释，缩进与下一行代码保持一致

> 2.多行注释，最少三行, '*'后跟一个空格

> 四、其他

> 1.每行最多100个字符
>
> 2.方法间空一行，代码内部可用空行区分板块
>
> 3.操作符前后带一个空格


参考：<https://blog.csdn.net/weixin_38984030/article/details/88050502>
