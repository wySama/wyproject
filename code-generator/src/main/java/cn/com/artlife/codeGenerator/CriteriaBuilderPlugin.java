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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * ---------------------------------------------------------------------------
 * 增加Criteria Builder方法
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2016/12/28 14:56
 * ---------------------------------------------------------------------------
 */
@Deprecated
public class CriteriaBuilderPlugin extends ExampleEnhancedPlugin{
    private static final Logger logger = LoggerFactory.getLogger(CriteriaBuilderPlugin.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(List<String> warnings) {
        logger.warn("itfsw:插件"+this.getClass().getTypeName()+"插件已经过时，请使用com.itfsw.mybatis.generator.plugins.ExampleEnhancedPlugin插件替换！");
        return super.validate(warnings);
    }
}
