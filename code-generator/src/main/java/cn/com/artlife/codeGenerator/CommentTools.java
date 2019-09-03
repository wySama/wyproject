/*
 * Copyright (c) 2017.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.com.artlife.codeGenerator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;

/**
 * ---------------------------------------------------------------------------
 * 插件评论生成工具
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2016/12/28 17:57
 * ---------------------------------------------------------------------------
 */
public class CommentTools {

    /**
     * 生成通用属性注解
     *
     * @param field
     * @param introspectedTable
     */
    public static void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**"); //$NON-NLS-1$
        field.addJavaDocLine(" * 这是Mybatis Generator拓展插件生成的属性(请勿删除)."); //$NON-NLS-1$
        sb.append(" * This field corresponds to the database table "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString());

        field.addJavaDocLine(" *");
        field.addJavaDocLine(" * "+MergeConstants.NEW_ELEMENT_TAG);
        field.addJavaDocLine(" * @author hewei");

        field.addJavaDocLine(" */"); //$NON-NLS-1$
    }

    /**
     * 生成通用内部类注解
     *
     * @param innerClass 类
     * @param introspectedTable 表
     */
    public static void addInnerClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**"); //$NON-NLS-1$
        innerClass.addJavaDocLine(" * 这是Mybatis Generator拓展插件生成的类(请勿删除)."); //$NON-NLS-1$
        sb.append(" * This class corresponds to the database table "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerClass.addJavaDocLine(sb.toString());
        innerClass.addJavaDocLine(" *");
        innerClass.addJavaDocLine(" * "+MergeConstants.NEW_ELEMENT_TAG);
        innerClass.addJavaDocLine(" * @author hewei");
        innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
    }

    /**
     * 生成通用内部Enum注释
     *
     * @param innerEnum 类
     * @param introspectedTable 表
     */
    public static void addInnerEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();
        innerEnum.addJavaDocLine("/**"); //$NON-NLS-1$
        innerEnum.addJavaDocLine(" * 这是Mybatis Generator拓展插件生成的枚举(请勿删除)."); //$NON-NLS-1$
        sb.append(" * This class corresponds to the database table "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerEnum.addJavaDocLine(sb.toString());
        innerEnum.addJavaDocLine(" *");
        innerEnum.addJavaDocLine(" * "+MergeConstants.NEW_ELEMENT_TAG);
        innerEnum.addJavaDocLine(" * @author hewei");
        innerEnum.addJavaDocLine(" */"); //$NON-NLS-1$
    }

    /**
     * 生成通用方法注解
     *
     * @param method 方法
     * @param introspectedTable 表
     */
    public static void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        method.addJavaDocLine(" * 这是Mybatis Generator拓展插件生成的方法(请勿删除).");
        sb.append(" * This method corresponds to the database table ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" *");
        method.addJavaDocLine(" * "+MergeConstants.NEW_ELEMENT_TAG);
        method.addJavaDocLine(" * @author hewei");
        method.addJavaDocLine(" */"); //$NON-NLS-1$
    }

    /**
     * 生成xml element 注释
     *
     * @param xmlElement the xml element
     */
    public static void addComment(XmlElement xmlElement) {

        xmlElement.addElement(new TextElement("<!--")); //$NON-NLS-1$

        StringBuilder sb = new StringBuilder();
        sb.append("  WARNING - "); //$NON-NLS-1$
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        xmlElement.addElement(new TextElement(sb.toString()));
        xmlElement.addElement(new TextElement("  这个节点为代码生成工具生成，请不要修改!")); //$NON-NLS-1$
        xmlElement.addElement(new TextElement("  @author hewei"));

        xmlElement.addElement(new TextElement("-->")); //$NON-NLS-1$
    }

    /**
     * 生成通用接口注解
     *
     * @param interf 接口
     * @param introspectedTable 表
     */
    public static void addInterfaceComment(Interface interf, IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();
        interf.addJavaDocLine("/**"); //$NON-NLS-1$
        interf.addJavaDocLine(" * 这是Mybatis Generator拓展插件生成的接口(请勿删除)."); //$NON-NLS-1$
        sb.append(" * This class corresponds to the database table "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTable());
        interf.addJavaDocLine(sb.toString());
        interf.addJavaDocLine(" *");
        interf.addJavaDocLine(" * "+MergeConstants.NEW_ELEMENT_TAG);
        interf.addJavaDocLine(" * @author hewei");
        interf.addJavaDocLine(" */"); //$NON-NLS-1$
    }

}
