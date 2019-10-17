package cn.com.xiaoyaoji.data.bean;

import cn.com.xiaoyaoji.core.annotations.Alias;
import cn.com.xiaoyaoji.core.annotations.Ignore;
import cn.com.xiaoyaoji.core.common.DocType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 模块
 * @author zhoujingjie
 * @date 2016-07-13
 */
@Alias("module")
public class Module {

    private String id;
    private String name;
    private Date lastUpdateTime;
    private Date createTime;
    private String projectId;
    //请求头
    private String requestHeaders;
    //请求参数
    private String requestArgs;

    @Ignore
    private List<Folder> folders=new ArrayList<>();

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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<Folder> getFolders() {
        return this.folders;
    }
    public void addInterfaceFolder(Folder in){
        this.folders.add(in);
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getRequestArgs() {
        return requestArgs;
    }

    public void setRequestArgs(String requestArgs) {
        this.requestArgs = requestArgs;
    }

    public Doc toDoc(){
        Doc doc = new Doc();
        doc.setId(getId());
        doc.setName(getName());
        doc.setSort(1);
        doc.setType(DocType.SYS_FOLDER.getTypeName());
        doc.setCreateTime(getCreateTime());
        doc.setLastUpdateTime(getLastUpdateTime());
        doc.setProjectId(getProjectId());
        doc.setParentId("0");
        return doc;
    }
}
