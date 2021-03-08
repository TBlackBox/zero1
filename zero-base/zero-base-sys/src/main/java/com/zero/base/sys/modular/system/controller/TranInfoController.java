package com.zero.base.sys.modular.system.controller;

import com.zero.base.common.core.base.controller.BaseController;
import com.zero.base.common.i18n.context.TranslationContext;
import com.zero.base.common.i18n.context.UserTranslationContext;
import com.zero.base.common.i18n.enums.TranslationEnum;
import com.zero.base.common.i18n.enums.TranslationItem;
import com.zero.base.common.model.exception.RequestEmptyException;
import com.zero.base.common.model.response.ResponseData;
import com.zero.base.common.model.response.SuccessResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

/**
 * 多语言表控制器
 *
 * @author yongzhi
 * @Date 2019-10-17 22:20:54
 */
@Controller
@RequestMapping("/translation")
public class TranInfoController extends BaseController {

    /**
     * 获取当前用户字典
     *
     * @author yongzhi
     * @Date 2019-06-20
     */
    @ResponseBody
    @RequestMapping("/getUserTranslation")
    public ResponseData getUserTranslation() {
        TranslationEnum userCurrentTrans = UserTranslationContext.getUserCurrentTrans();
        Map<String, String> translationByLanguage = TranslationContext.getTranslationByLanguage(userCurrentTrans);
        return ResponseData.success(translationByLanguage);
    }

    /**
     * 切换用户的语言
     *
     * @author yongzhi
     * @Date 2019-06-20
     */
    @ResponseBody
    @RequestMapping("/changeUserTranslation")
    public ResponseData changeUserTranslation(@RequestParam("code") Integer code) {
        TranslationEnum translationEnum = TranslationEnum.valueOf(code);
        if (translationEnum == null) {
            throw new RequestEmptyException("请求不合法！code值错误！");
        }
        UserTranslationContext.setUserCurrentTrans(translationEnum);
        return SUCCESS_TIP;
    }

    /**
     * 获取当前支持的语言列表
     *
     * @author yongzhi
     * @Date 2019-06-20
     */
    @ResponseBody
    @RequestMapping("/languages")
    public ResponseData languages() {
        TranslationEnum[] values = TranslationEnum.values();

        ArrayList<TranslationItem> results = new ArrayList<>();
        for (TranslationEnum value : values) {
            results.add(new TranslationItem(value.getCode(), value.getDescription()));
        }

        return new SuccessResponseData(results);
    }

}


