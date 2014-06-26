# Android校验库 - Android Validation

简单易用的Android校验库。

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

    Configuration conf = Configuration.buildIn(context, BuildInTypes.Required, "必填选项！");
    conf.add(BuildInTypes.LengthInMax, 20);
    conf.add(BuildInTypes.Email);
    
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
    // FormValidator.configFor(*Config instance*, *view id for EditText*)
    fv.configFor(Configuration.buildIn(context, BuildInTypes.ChineseMobilePhone), R.id.form_field_1);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.CreditCard), R.id.form_field_2);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.Digits), R.id.form_field_3);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.Email), R.id.form_field_4);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.EqualTo, "chenyoca"), R.id.form_field_5);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.Host), R.id.form_field_6);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.HTTP_URL), R.id.form_field_7);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.LengthInMax, 5), R.id.form_field_8);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.LengthInMin, 4), R.id.form_field_9);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.LengthInRange, 4,8), R.id.form_field_10);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.NotBlank), R.id.form_field_11);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.Numeric), R.id.form_field_12);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.ValueInMax, 100), R.id.form_field_13);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.ValueInMin, 20.0), R.id.form_field_14);
    fv.configFor(Configuration.buildIn(context, BuildInTypes.ValueInRange, 18, 30), R.id.form_field_15);
        
```

#### 2. 对表单内执行校验配置

```java

    final LinearLayout form = (LinearLayout) findViewById(R.id.form);
    
    // 1. 中断校验：按Layout的ChildView顺序校验，遇到校验失败则中断。
    boolean passed = fv.testForm(form)
    
    // 1. 连续校验：按Layout的ChildView顺序校验，遇到校验失败继续，不中断。
    boolean passed = fv.testFormAll(form)
    
```

## 如何扩展？

通过 Configuration的扩展接口，添加你自定义的校验实现类

```java

    // 1. 添加到已创建的Configuration中：
    
    conf.add(new TestRunner("出错时，此消息被返回并显示到EditText中") {
        @Override
        public boolean test(CharSequence inputValue) {
            // 校验通过时返回 true
            return inputValue.equal("AABB");
        }
    });
    
    // 2. 通过自定义TestRunner创建Configuration：
    
    Configuration.custom(context, new TestRunner("出错时，此消息被返回并显示到EditText中") {
        @Override
        public boolean test(CharSequence inputValue) {
            return false;
        }
    })


```