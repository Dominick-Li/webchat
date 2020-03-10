package com.ljm.chat.model.response;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author Dominick Li
 * @createTime 2019/8/12 0:31
 * @description 视图挑战
 **/
public class ResResult extends HashMap<String, Serializable> implements Serializable {
    private static final String RES_SUCCESS = "success";
    private static final String RES_MESSAGE = "msg";
    private static final String RES_DATA = "data";
    private static final String RES_TOTAL_PAGE = "totalPage";
    private static final String RES_LIST = "result";
    private static final String RES_TOTAL_RECORD="totalRecord";
    private static final long serialVersionUID = 1L;
    private static final String ACCESS_TOKEN="access_token";

    public ResResult(boolean success) {
        this.setSuccess(success);
    }

    public ResResult(boolean success, String message) {
        this.setSuccess(success);
        this.setMsg(message);
    }

    public ResResult(boolean success, Serializable[] list, long totalPage, long totalRecord) {
        this.setSuccess(success);
        this.setTotalPage(totalPage);
        this.setList(list);
    }

    public ResResult(boolean success, List<? extends Serializable> result, long totalPage, long totalRecord) {
        this.setSuccess(success);
        this.setTotalPage(totalPage);
        this.setTotalRecord(totalRecord);
        this.setResult(result);
    }

    public boolean isSuccess() {
        return (Boolean) ((Serializable) this.get(RES_SUCCESS));
    }

    public void setSuccess(boolean success) {
        this.put(RES_SUCCESS, success);
    }

    public String getMsg() {
        return (String) this.get(RES_MESSAGE);
    }

    public void setMsg(String message) {
        this.put(RES_MESSAGE,message);
    }



    public void setTotalRecord(long totalRecord){
        this.put(RES_TOTAL_RECORD, totalRecord);
    }
    public long getTotalRecord(){
        return  (long) (Integer) ((Serializable)this.get(RES_TOTAL_RECORD));
    }

    public Serializable getData() {
        return (Serializable) this.get(RES_DATA);
    }

    public void setData(Serializable data) {
        this.put(RES_DATA, data);
    }

    public long getTotalPage() {
        return (long) (Integer) ((Serializable) this.get(RES_TOTAL_PAGE));
    }

    public void setTotalPage(long totalPage) {
        this.put(RES_TOTAL_PAGE, totalPage);
    }

    public Serializable[] getResult() {
        return (Serializable[]) ((Serializable[]) this.get(RES_LIST));
    }

    public void setList(Serializable[] list) {
        this.put(RES_LIST, list);
    }

    public void setResult(List<? extends Serializable> result) {
        Serializable[] resultArray = new Serializable[result.size()];
        resultArray = (Serializable[]) result.toArray(resultArray);
        this.setList(resultArray);
    }

}