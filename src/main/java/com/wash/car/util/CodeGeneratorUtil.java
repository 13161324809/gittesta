package com.wash.car.util;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 代码生成器
 */
public class CodeGeneratorUtil {
    public static final String DB_URL = "jdbc:mysql://106.12.33.140:3307/wash-car?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=UTC";
    public static final String DB_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String BASE_PACKAGE_PATH = "com.wash.car";

    /**
     * Description: 窗口获取信息
     * @Author: cddk
     * @Param: [tip]
     * @Return: java.lang.String
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * Description: 执行生成代码
     * @Author: cdd
     * @Param: []
     * @Return: void
     */
    public static void execute(String tabName) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");  // 获取当前项目路径
        gc.setOutputDir(projectPath + "/src/main/java");      // 设置基础包路径
        gc.setAuthor("wash-car");                            // Mapper,Service等类注解中显示创建人信息
        gc.setOpen(false);                                    // 文件生成完毕后，是否需要打开所在路径
//        gc.setSwagger2(true);                               // 实体属性 Swagger2 注解
//        gc.setBaseColumnList(true);                         // 在Mapper.xml文件中是否生成公用SQL代码段
        gc.setBaseResultMap(true);                          // 在Mapper.xml文件中是否生成公用返回集合ResultMap
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(CodeGeneratorUtil.DB_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(CodeGeneratorUtil.DB_DRIVER_NAME);
        dsc.setUsername("root");
        dsc.setPassword("senQwer!@#$");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));   // 模块名称
        pc.setParent(CodeGeneratorUtil.BASE_PACKAGE_PATH);           // 基础包路径
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        //如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mybatis/mapper/"+ tableInfo.getEntityName() +"/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置，具体配置可查看官方文档：
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);          // Entity文件名称命名规范
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);    // Entity字段名称
        strategy.setRestControllerStyle(true);                          // Controller注解使用是否RestController标注,否则是否开启使用Controller标注
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");  // Entity的公共父类
        strategy.setEntityLombokModel(true);                            // 是否使用lombok完成Entity实体标注Getting Setting ToString 方法
        strategy.setControllerMappingHyphenStyle(true);                 // Controller注解名称，不使用驼峰，使用连字符
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");   // controller公共父类
        strategy.setInclude(tabName.split(","));
//        strategy.setTablePrefix(pc.getModuleName() + "_");              // 表前缀，添加该表示，则生成的实体，不会有表前缀，比如sys_dept 生成就是Dept
//        strategy.setFieldPrefix("sys_");                              // 字段前缀
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());          // 用的是freeemarker模板引擎
        mpg.execute();
    }

    public static void main(String[] args) {
        String tabName = "bank_account,order,order_comment,order_consumables,parking_lot,transaction_details,system_message,user";
        execute(tabName);
    }

    public static String lineToHump(String str) {
        Pattern linePattern = Pattern.compile("_(\\w)");
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
