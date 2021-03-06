package com.adpanshi.cashloan.cl.model.user;

import com.adpanshi.cashloan.core.common.util.JsonUtil;
import com.adpanshi.cashloan.core.common.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lsk on 2016/8/16.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class AppAbsActionWrapper {
    private Logger logger = LoggerFactory.getLogger(AppAbsActionWrapper.class);
    protected String dataRoot;
    protected HttpServletRequest _req;
    protected HttpServletResponse _resp;
    protected boolean writeJson = true;

    /**
     *
     * @param _resp
     */
    public AppAbsActionWrapper(HttpServletResponse _resp) {
        this(_resp, null);
    }


    public static Map getParams(HttpServletRequest request) {
        LinkedHashMap params = new LinkedHashMap();
        Enumeration enames = request.getParameterNames();

        while (enames.hasMoreElements()) {
            String name = (String) enames.nextElement();
            params.put(toUnderLine(name), request.getParameter(name));
        }
        return params;
    }

    /**
     *
     * @method: toUnderLine
     * @param str
     * @return: java.lang.String
     * @throws
     * @Author: Mr.Wange
     * @Date: 2018/7/31 15:35
     */
    public static String toUnderLine(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c >= 65 && c <= 90) {
                c += 32;
                sb.append("_");
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     *
     * @method: AppAbsActionWrapper
 * @param _resp
 * @param _req
     * @return:
     * @throws
     * @Author: Mr.Wange
     * @Date:
     */
    public AppAbsActionWrapper(HttpServletResponse _resp, HttpServletRequest _req) {
        this._resp = _resp;
        this._req = _req;

        Object result = null;
        try {
            result = doAction();
            if (result == null) {
                result = new Object[][]{
                    {"success", true},
                    {"msg", "操作成功"}
                };
            }

            if (result instanceof Object[][]) {
                result = MapUtil.array2Map((Object[][]) result);
            }
            if (result instanceof Map) {
                Map _result = (Map) result;
                if (!_result.containsKey("success")) {
                    _result.put("success", true);
                }
                if (!_result.containsKey("code")) {
                    boolean success = (Boolean) _result.get("success");
                    _result.put("code", success ? 200 : 400);
                }


                Boolean success = (Boolean) _result.get("success");
                if (success != null) {
                    _result.remove("success");
                    if (!_result.containsKey("code")) {
                        _result.put("code", success ? 200 : 400);
                    }
                }


                Object data = ((Map) result).get("data");
                if (data instanceof Map) {
                    MapUtil.replaceNullValue2EmptyStr((Map) data);
                }

            }

        } catch (Exception e) {
            logger.warn("{}", e);
            result = MapUtil.array2Map(new Object[][]{
//                    {"success", false},
                {"code", 500},
                {"msg", "系统异常，请稍后再试，或联系系统管理员"}
            });
        }
        if (writeJson) {
            JsonUtil.writeJson(result, _resp);
        }
    }

    public abstract Object doAction() ;
}
