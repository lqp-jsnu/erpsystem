package edu.yctc.erpsystem.vo;

import java.util.List;

/**
 * 树状菜单视图
 *
 * @author lqp
 */
public class TreeViewVO {

    /** syResource主键 */
    private String id;

    /** 父主键 */
    private String pid;

    /** 显示的文字 */
    private String text;

    /** 图标 */
    private String icon;

    /** 链接 */
    private String href;

    /** 子节点 */
    private List<TreeViewVO> nodes;

    public TreeViewVO(String id, String pid, String text, String icon, String href) {
        this.id = id;
        this.pid = pid;
        this.text = text;
        this.icon = icon;
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<TreeViewVO> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeViewVO> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "TreeViewVO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", nodes=" + nodes +
                '}';
    }

}
