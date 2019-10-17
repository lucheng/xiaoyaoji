package cn.com.xiaoyaoji.data.bean;

import cn.com.xiaoyaoji.core.annotations.Alias;
import cn.com.xiaoyaoji.core.annotations.Ignore;
import cn.com.xiaoyaoji.core.common.DocType;
import cn.com.xiaoyaoji.core.common._HashMap;
import cn.com.xiaoyaoji.core.util.JsonUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;

/**
 * 接口
 * @author zhoujingjie
 * @date 2016-07-13
 * @see Doc
 */
@Alias("interface")
@Deprecated
public class Interface {

    private String id;
    private String name;
    private String description;
    private String folderId;
    @Ignore
    private String folder;
    private String url;
    private String requestMethod;
    private String contentType;
    private String requestHeaders;
    private String requestArgs;
    private String responseArgs;
    private String example;
    private String projectId;
    private String moduleId;
    private Date createTime;
    private Date lastUpdateTime;
    private String dataType;
    private String protocol;
    private String status;
    private Integer sort;

    public interface Status{
        //启用
        String ENABLE = "ENABLE";
        //已废弃
        String DEPRECATED="DEPRECATED";
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getRequestArgs() {
        return requestArgs;
    }

    public void setRequestArgs(String requestArgs) {
        this.requestArgs = requestArgs;
    }

    public String getResponseArgs() {
        return responseArgs;
    }

    public void setResponseArgs(String responseArgs) {
        this.responseArgs = responseArgs;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }
    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Doc toDoc(){
        Doc doc = new Doc();
        doc.setId(getId());
        doc.setName(getName());
        doc.setSort(getSort());
        if("HTTP".equals(getProtocol())) {
            doc.setType(DocType.SYS_HTTP.getTypeName());
        }else if("WEBSOCKET".equals(getProtocol())){
            doc.setType(DocType.SYS_WEBSOCKET.getTypeName());
        }else{
            doc.setType(DocType.SYS_DOC_MD.getTypeName());
        }

        doc.setCreateTime(getCreateTime());
        doc.setLastUpdateTime(getLastUpdateTime());
        doc.setProjectId(getProjectId());
        doc.setParentId(getFolderId());
        doc.setContent(JsonUtils.toString(new _HashMap<>()
                .add("description",getDescription())
                .add("url",getUrl())
                .add("requestMethod",getRequestMethod())
                .add("contentType",getContentType())
                .add("requestHeaders", JSON.parse(getRequestHeaders()))
                .add("requestArgs", JSON.parse(getRequestArgs()))
                .add("responseArgs", JSON.parse(getResponseArgs()))
                .add("example",getExample())
                .add("dataType",getDataType())
                .add("status",getStatus())
        ));
        return doc;
    }
}
