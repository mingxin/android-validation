# Android校验库 - Android Validation

简单易用的Android校验库。

这是一个简单Android校验库，按配置来验证用户输入的表单信息。
只需要几行代码，即可验证用户输入，并且将验证错误反馈给用户。
它内置了大量常用的验证类型，足以满足你的功能需求。
它还有一个可扩展的验证选项，你可以通过扩展接口添加你需要的验证方式。

![截图](https://raw.githubusercontent.com/chenyoca/android-validation/develop/documents/screenshot.png)

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
        compile 'com.github.chenyoca:android-validation:2.0-SNAPSHOT'
    }

```
## 已内置支持的校验方式

* **Required** (必填)
* **NotBlank** (非空数据)
* **Digits** (仅数字)
* **Email** (电子邮件)
* **EqualTo** (与指定值相同)
* **Host** (主机地址)
* **URL** (Http URL)
* **IPv4** (IPv4地址)
* **LengthInRange** (指定长度范围)
* **MinLength** (最小长度)
* **MaxLength** (最大长度)
* **Numeric** (数值)
* **CreditCard** (信用卡号)
* **ValueInRange** (最值范围)
* **MinValue** (最小值)
* **MaxValue** (最大值)
* **ChineseMobilePhone** (中国的手机号码)

## 如何使用？

### 方式 1：对单个EditText进行校验

#### 1. 构建校验配置

```java

    // 通过build, add, custom接口来添加校验规则，
    // Apply 用于每次添加校验规则，在 add() 方法中，会自动将保存前一条配置，
    // 但在最后一条规则配置后，记得调用 apply() 确认规则配置完成。

    final Config conf = Config.build(Types.Required).message("必填选项").apply();
    conf.add(Types.LengthInMax).values(20).apply();
    conf.add(Types.Email).apply();
    
```

**!!!! 最后添加的规则一定要调用 apply() !!!!**

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
    final Config conf = Config.build(Types.Required).message("必填选项").apply();
    conf.add(Types.LengthInMax).values(20).apply();
    conf.add(Types.Email).apply();

    final EditText test = (EditText) findViewById(R.id.single_test);

    final Button apply = (Button) findViewById(R.id.single_apply);
    apply.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ResultWrapper rw = AndroidValidator.testField(test, conf, testDisplay);
            int color = rw.passed ?
                    android.R.color.holo_green_dark : android.R.color.holo_red_dark;
            apply.setTextColor(getResources().getColor(color));
        }
    });

    final LinearLayout form = (LinearLayout) findViewById(R.id.form);
    final AndroidValidator av = new AndroidValidator(testDisplay);

    av.putField(R.id.form_field_1, Types.ChineseMobilePhone, Types.Required);
    av.putField(R.id.form_field_2, Types.CreditCard);
    av.putField(R.id.form_field_3, Types.Digits);
    av.putField(R.id.form_field_4, Types.Email);
    av.putField(R.id.form_field_5, Config.build(Types.EqualTo).loader(new EditTextLazyLoader(test)).apply());
    av.putField(R.id.form_field_6, Types.Host);
    av.putField(R.id.form_field_7, Types.HTTPURL);
    av.putField(R.id.form_field_8, Types.LengthInMax);
    av.putField(R.id.form_field_9, Types.LengthInMin);
    av.putField(R.id.form_field_10, Types.LengthInRange);
    av.putField(R.id.form_field_11, Types.NotBlank);
    av.putField(R.id.form_field_12, Types.Numeric);
    av.putField(R.id.form_field_13, Config.build(Types.ValueInMax).values(100).apply());
    av.putField(R.id.form_field_14, Config.build(Types.ValueInMin).values(20).apply());
    av.putField(R.id.form_field_15, Config.build(Types.ValueInRange).values(18, 30).apply());
        
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

### 获取EditText的值

```java

    String username = validator.getValue(R.id.form_field_1);

```

