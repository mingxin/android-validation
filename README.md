# Android校验库 - Android Validation

简单易用的Android校验库。

![截图](http://static.oschina.net/uploads/space/2014/0626/170940_Q6Fx_191986.png)

## Gradle 依赖

Add repository

```groovy

    allprojects {
     repositories {
         // !!!! ADD THIS !!!!
         maven{ url 'http://oss.sonatype.org/content/groups/public/' }
     }
    }

```

Add dependency

```groovy

    dependencies {
        compile 'com.github.chenyoca:android-validation:2.0'
    }

```

### 将项目打包并发布到Sonatype的命令：

> ./gradlew clean uploadArchives -PUsingSonatype

## 已内置支持的校验方式

* **Required** (必填)
* **NotBlank** (非空数据)
* **Digits** (仅数字)
* **Date** (日期)
* **Email** (电子邮件)
* **EqualTo** (与指定值相同)
* **Host** (主机地址)
* **HTTPURL** (Http URL)
* **IPv4** (IPv4地址)
* **LengthInRange** (指定长度范围)
* **LengthInMin** (最小长度)
* **LengthInMax** (最大长度)
* **Numeric** (数值)
* **CreditCard** (信用卡号)
* **ValueInRange** (最值范围)
* **ValueInMin** (最小值)
* **ValueInMax** (最大值)
* **ChineseMobilePhone** (中国的手机号码)

## 如何使用？

### 方式 1：对单个EditText进行校验

#### 1. 构建校验配置

```java

    Config conf = Config.from(Types.Required, "必填选项！");
    conf.add(Types.LengthInMax, 20);
    conf.add(Types.Email);
    
```

#### 2. 对EditText执行校验

```java

    EditText edittext = (EditText) findViewById(R.id.single_test);
    ResultWrapper result = FormValidator.testField(edittext, conf);

```

### 方式 2：对整个Layout内的EditText全部校验

通过 View ID 来绑定校验配置信息

#### 1. 对表单内各个EditText绑定其校验配置

```java

    final FormValidator fv = new FormValidator();
    // FormValidator.addField(*Config instance*, *view id for EditText*)
    fv.addField(Config.from(Types.ChineseMobilePhone), R.id.form_field_1);
    fv.addField(Config.from(Types.CreditCard), R.id.form_field_2);
    fv.addField(Config.from(Types.Digits), R.id.form_field_3);
    fv.addField(Config.from(Types.Email), R.id.form_field_4);
    fv.addField(Config.from(Types.EqualTo, "chenyoca"), R.id.form_field_5);
    fv.addField(Config.from(Types.Host), R.id.form_field_6);
    fv.addField(Config.from(Types.HTTP_URL), R.id.form_field_7);
    fv.addField(Config.from(Types.LengthInMax, 5), R.id.form_field_8);
    fv.addField(Config.from(Types.LengthInMin, 4), R.id.form_field_9);
    fv.addField(Config.from(Types.LengthInRange, 4,8), R.id.form_field_10);
    fv.addField(Config.from(Types.NotBlank), R.id.form_field_11);
    fv.addField(Config.from(Types.Numeric), R.id.form_field_12);
    fv.addField(Config.from(Types.ValueInMax, 100), R.id.form_field_13);
    fv.addField(Config.from(Types.ValueInMin, 20.0), R.id.form_field_14);
    fv.addField(Config.from(Types.ValueInRange, 18, 30), R.id.form_field_15);
        
```

#### 2. 对表单执行校验配置

##### 2.1 粗鲁的校验 - 直接拿校验结果

```java

    final LinearLayout form = (LinearLayout) findViewById(R.id.form);
    
    // 1. 中断校验：按Layout的ChildView顺序校验，遇到校验失败则中断。
    boolean passed = fv.testForm(form)
    
    // 1. 连续校验：按Layout的ChildView顺序校验，遇到校验失败继续，不中断。
    boolean passed = fv.testFormAll(form)
    
```

##### 2.2 文明的校验

```java

    // 先绑定校验表单
    fv.bind(form)
          .applyTypeToView(); // 将校验规则应用到EditText中，使得输入法根据校验配置，显示不同的布局。
          
    fv.test();
    // Or fv.testAll();
    
```

## 如何扩展？

通过 Config的扩展接口，添加你自定义的校验实现类

```java

    // 添加到已创建的Config中：
    
    conf.add(new TestRunner("出错时，此消息被返回并显示到EditText中") {
        @Override
        public boolean test(CharSequence inputValue) {
            // 校验通过时返回 true
            return inputValue.equal("AABB");
        }
    });
    

```

## 注意

### 校验顺序

校验顺序按Config添加配置的顺序进行校验。

**如果添加 `Required` 校验类型，则 `Required` 无论在哪个顺序被添加，都会被首先校验。**

#### `Required`校验类型对其它类型的影响：

当EditText为空值时，如果添加`Required`校验规则，则校验失败；如果没有，则校验通过并跳过后面的校验类型。

## 其它扩展接口

### 将校验条件应用到EditText中

如“最大长度”、“邮件地址”等校验条件，可以将EditText的输入类型自动切换至相应类型。