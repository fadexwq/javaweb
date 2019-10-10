package cn.xwq.logistics.pojo;

public class BaseData {
    private Long baseId;

    private String baseName;

    private String baseDesc;

    private Long parentId;

    public BaseData(Long baseId, String baseName, String baseDesc, Long parentId) {
        this.baseId = baseId;
        this.baseName = baseName;
        this.baseDesc = baseDesc;
        this.parentId = parentId;
    }

    public BaseData() {
        super();
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName == null ? null : baseName.trim();
    }

    public String getBaseDesc() {
        return baseDesc;
    }

    public void setBaseDesc(String baseDesc) {
        this.baseDesc = baseDesc == null ? null : baseDesc.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}