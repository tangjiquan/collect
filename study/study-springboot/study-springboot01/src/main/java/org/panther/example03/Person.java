package org.panther.example03;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @Author: Kevin
 * @Date: Created in 下午9:02 18-4-7
 * @Version:
 * @Description:
 */
public class Person {

    private String id;
    private String name;
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date createTime;//创建时间.

    @JSONField(serialize=false)
    private String remarks;//备注信息.

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
