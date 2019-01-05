package com.example.ljh.liangminghui.home;

import java.util.List;

public class HomeBean {
    private String id;
    private String title;
    private String des;
    private List<String> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public static class HomeResBean{
        private String reason;
        private List<HomeBean> result;
        private String error_code;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getError_code() {
            return error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public List<HomeBean> getResult() {
            return result;
        }

        public void setResult(List<HomeBean> result) {
            this.result = result;
        }
    }
}
