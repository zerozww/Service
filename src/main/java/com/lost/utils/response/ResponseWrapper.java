package com.lost.utils.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class ResponseWrapper {

    private int code;
    private String msg;
    private Map<String, Object> extra;

    /**
     * 私有的构造函数
     */
    private ResponseWrapper() {}

    /**
     * 自定义返回结果
     */
    public static ResponseWrapper markDefault(int code,String msg){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(code);
        responseWrapper.setMsg(msg);
        return responseWrapper;
    }

    /**
     * 查询成功且有数据
     */
    public static ResponseWrapper markSuccess(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(ResponseCode.SUCCESS.getCode());
        responseWrapper.setMsg(ResponseCode.SUCCESS.getMsg());
        return  responseWrapper;
    }

    /**
     * 操作成功，但无记录
     */
    public static ResponseWrapper markNoData(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(ResponseCode.NODATA.getCode());
        responseWrapper.setMsg(ResponseCode.NODATA.getMsg());
        return responseWrapper;
    }

    /**
     * 操作成功，但记录已存在
     */
    public static ResponseWrapper markDataExisted(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(ResponseCode.DATA_EXISTED.getCode());
        responseWrapper.setMsg(ResponseCode.DATA_EXISTED.getMsg());
        return responseWrapper;
    }


    /**
     * 参数为空或者参数格式错误
     */
    public static ResponseWrapper markParamError(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(ResponseCode.PARAMS_ERROR.getCode());
        responseWrapper.setMsg(ResponseCode.PARAMS_ERROR.getMsg());
        return responseWrapper;
    }

    /**
     * 查询失败
     */
    public static ResponseWrapper markError(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(ResponseCode.FAILED.getCode());
        responseWrapper.setMsg(ResponseCode.FAILED.getMsg());
        return responseWrapper;
    }

    /**
     * 查询失败
     */
    public static ResponseWrapper markACCOUNT_ERROR(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(ResponseCode.ACCOUNT_ERROR.getCode());
        responseWrapper.setMsg(ResponseCode.ACCOUNT_ERROR.getMsg());
        return responseWrapper;
    }

    /**
     * 无效URL链接
     */
    public static ResponseWrapper markUrlError(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(ResponseCode.URL_ERROR.getCode());
        responseWrapper.setMsg(ResponseCode.URL_ERROR.getMsg());
        return responseWrapper;
    }

    /**
     * 系统异常
     */
    public static ResponseWrapper markSystemError(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(ResponseCode.SYSTEM_ERROR.getCode());
        responseWrapper.setMsg(ResponseCode.SYSTEM_ERROR.getMsg());
        return responseWrapper;
    }

    /**
     * 没有该API访问权限
     */
    public static ResponseWrapper markApiNotPermission(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(ResponseCode.API_NOT_PER.getCode());
        responseWrapper.setMsg(ResponseCode.API_NOT_PER.getMsg());
        return responseWrapper;
    }

    /**
     * 包装ResponseWrapper
     * @param tag Tag名称
     * @param jsonArray JSON数据
     * @return ResponseWrapper
     */
    public static ResponseWrapper getResponseWrapperFromJSONArray(String tag, JSONArray jsonArray) {
        ResponseWrapper responseWrapper;
        if (jsonArray != null && jsonArray.size() > 0) {
            responseWrapper = ResponseWrapper.markSuccess();
            responseWrapper.setExtra(tag, jsonArray);
        } else if (jsonArray != null) {
            responseWrapper = ResponseWrapper.markNoData();
        } else {
            responseWrapper = ResponseWrapper.markError();
        }
        return responseWrapper;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(String key, Object value) {
        if (this.extra == null) {
            this.extra = new HashMap<>();
        }
        this.extra.put(key, value);
    }

    public Object getExtra(String key) {
        return this.extra.get(key);
    }

    public Object removeExtra(String key) {
        return this.extra.remove(key);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
