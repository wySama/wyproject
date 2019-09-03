import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {
    public static void main(String args[]){

        try {

            Map root = new HashMap();
            root.put("name", "SysCode");
            root.put("controllerMapping", "sysCode");
            root.put("package", "cn.com.artlife.archiveCenterManage");
            root.put("servicePackage", "cn.com.artlife.archiveCenterManage");
            root.put("bizPackage", "cn.com.artlife.archiveCenterService");
            root.put("ID", "CodeId");
            root.put("id", "codeId");

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            cfg.setDirectoryForTemplateLoading(new File("D:\\wy\\wydemo\\wyproject\\code-generator\\src\\main\\resources\\"));
            cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
//            {
//                //接口controller
//                Template temp = cfg.getTemplate("Controller.ftl");
//                String fileName = root.get("name") + "Controller.java";
//                String destStr="D:\\intellij\\spring-cloud-parent\\archive-center-manage\\src\\main\\java\\cn\\com\\artlife\\archiveCenterManage\\controller\\";
//                File file = new File( destStr + fileName);
//                if (file.exists()) {
//                    file.renameTo(new File(destStr + fileName + "." + System.currentTimeMillis()));
//                }
//                FileWriter fw = new FileWriter(file);
//                BufferedWriter bw = new BufferedWriter(fw);
//                temp.process(root, bw);
//                bw.flush();
//                fw.close();
//            }
//            {
//                //接口service
//                Template temp = cfg.getTemplate("Service.ftl");
//                String fileName = root.get("name")+"Service.java";
//                String destStr= "D:\\intellij\\spring-cloud-parent\\archive-center-sdk\\src\\main\\java\\cn\\com\\artlife\\archiveCenterSDK\\service\\";
//                File file = new File(destStr + fileName);
//                if(file.exists()){
//                    file.renameTo(new File(destStr + fileName+"."+System.currentTimeMillis()) );
//                }
//                FileWriter fw = new FileWriter(file);
//                BufferedWriter bw = new BufferedWriter(fw);
//                temp.process(root, bw);
//                bw.flush();
//                fw.close();
//            }
            {
                //业务controller
                Template temp = cfg.getTemplate("BizController.ftl");
                String fileName = root.get("name")+"Controller.java";
                String destStr= "D:\\wy\\wydemo\\wyproject\\mydemo\\src\\main\\java\\com\\wy\\mydemo\\controller\\";
                File file = new File(destStr + fileName);
                if(file.exists()){
                    file.renameTo(new File(destStr + fileName+"."+System.currentTimeMillis()) );
                }
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                temp.process(root, bw);
                bw.flush();
                fw.close();
            }
            {
                //业务list页面
                Template temp = cfg.getTemplate("HtmlList.ftl");
                String fileName = root.get("controllerMapping")+".ftl";
                String destStr= "D:\\wy\\wydemo\\wyproject\\mydemo\\src\\main\\resources\\templates\\views\\";
                File file = new File(destStr + fileName);
                if(file.exists()){
                    file.renameTo(new File(destStr + fileName+"."+System.currentTimeMillis()) );
                }
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                temp.process(root, bw);
                bw.flush();
                fw.close();
            }
            {
                //业务edit页面
                Template temp = cfg.getTemplate("HtmlEdit.ftl");
                String fileName = root.get("controllerMapping")+"Edit.ftl";
                String destStr= "D:\\wy\\wydemo\\wyproject\\mydemo\\src\\main\\resources\\templates\\views\\";
                File file = new File(destStr + fileName);
                if(file.exists()){
                    file.renameTo(new File(destStr + fileName+"."+System.currentTimeMillis()) );
                }
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                temp.process(root, bw);
                bw.flush();
                fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
