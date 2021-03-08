
package com.zero.base.common.model.api;


import com.zero.base.common.model.api.model.ReportResourceReq;
import com.zero.base.common.model.api.model.ResourceUrlReq;
import com.zero.base.common.model.api.model.UserResourceReq;
import com.zero.base.common.model.resource.ResourceDefinition;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

/**
 * 系统管理服务端提供的远程服务
 *
 * @author fengshuonan
 * @date 2018-02-06 14:30
 */
@RequestMapping("/resourceService")
public interface ResourceService {

    /**
     * 报告业务系统的资源(Resources)到服务器,appCode若重复则会覆盖
     */
    @RequestMapping(value = "/reportResources", method = RequestMethod.POST)
    void reportResources(@RequestBody ReportResourceReq reportResourceReq);

    /**
     * 获取用户所拥有的资源url
     */
    @RequestMapping(value = "/getUserResourceUrls", method = RequestMethod.POST)
    Set<String> getUserResourceUrls(@RequestBody UserResourceReq userResourceReq);

    /**
     * 获取资源通过url
     */
    @RequestMapping(value = "/getResourceByUrl", method = RequestMethod.POST)
    ResourceDefinition getResourceByUrl(@RequestBody ResourceUrlReq resourceUrlReq);

}
