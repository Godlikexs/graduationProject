package com.xxs.graduationproject.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;
public class AutoGeneratorUtil {
    /**
     * 启动生成代码
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("------开始---------");
        doGenerator();
        System.out.println("------结束---------");
    }

    /**
     * 基础配置
     */
    private static String outputDir = System.getProperty("user.dir") + "/src/main/java";
    private static String author = "XiongXiaoSong";
    /**
     * 数据库配置
     */
    private static DbType dbType = DbType.MYSQL;
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String userName = "root";
    private static String password = "123456";
    private static String url = "jdbc:mysql://localhost:3306/graduationproject?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&";
    private static String[] tables = {"user"};//要生成的表
    /**
     * 生成包路径
     */
    private static String packageParent = "com.xxs.graduationproject";//项目目录
    private static String entity = "sys.entity";
    private static String mapper = "sys.mapper";
    private static String mapperXml = "sys.mapper.xml";
    private static String service = "sys.service";
    private static String serviceImpl = "sys.service.impl";
    private static String controller = "sys.controller";

    public static void doGenerator() {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //代码生成存放位置
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setOpen(true);
        gc.setAuthor(author);
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceImplName("%sService");
        gc.setServiceName("I%sService");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(dbType);
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        dsc.setUrl(url);
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(tables);
        strategy.setSuperEntityColumns(new String[]{});
        //strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        List<TableFill> tableFillList = new ArrayList<>();
        TableFill fill = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        tableFillList.add(fill);
        fill = new TableFill("create_time", FieldFill.INSERT);
        tableFillList.add(fill);
        strategy.setTableFillList(tableFillList);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageParent);
        // 代码生成包路径
        pc.setEntity(entity);
        pc.setMapper(mapper);
        pc.setXml(mapperXml);
        pc.setService(service);
        pc.setServiceImpl(serviceImpl);
        pc.setController(controller);
        mpg.setPackageInfo(pc);
        // 注入自定义配置，可以在 VM 中使用 ${cfg.packageMy} 设置值
        // InjectionConfig cfg = new InjectionConfig() {
        //     public void initMap() {
        //         Map<String, Object> map = new HashMap<String, Object>();
        //         map.put("packageMy", packageBase);
        //         this.setMap(map);
        //     }
        // };

        // mpg.setCfg(cfg);

        // TemplateConfig tc = new TemplateConfig();
        // tc.setEntity("templates/entity.java.vm");
        // tc.setMapper("templates/mapper.java.vm");
        // tc.setXml("templates/mapper.xml.vm");
        // tc.setServiceImpl("templates/serviceImpl.java.vm");
        // tc.setService("templates/service.java.vm");
        // tc.setController("templates/controller.java.vm");
        // mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
    }
}
