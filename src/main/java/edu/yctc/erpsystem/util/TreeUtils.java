package edu.yctc.erpsystem.util;

import edu.yctc.erpsystem.entity.OrganizationDO;
import edu.yctc.erpsystem.entity.ResourceDO;
import edu.yctc.erpsystem.vo.TreeViewVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 生成树工具类
 *
 * @author xiaotao
 */
public class TreeUtils {

    /**
     * 得到生成的树
     *
     * @param resources 资源
     * @return 树
     */
    static public List<TreeViewVO> getTree(List<ResourceDO> resources) {
        List<TreeViewVO> root = resources.stream().filter(item -> null == item.getSyResourceId())
                .map(item -> new TreeViewVO(item.getId(), null, item.getName(), item.getIcons(), item.getUrl())).collect(Collectors.toList());
        return buildTree(resources, root);
    }

    /**
     * 得到生成的树
     *
     * @param resources 资源
     * @return 树
     */
    static public List<TreeViewVO> getOrganizationTree(List<OrganizationDO> resources) {

        List<TreeViewVO> root = resources.stream().filter(item -> null == item.getSyOrganizationId())
                .map(item -> new TreeViewVO(item.getId(), null, item.getName(), item.getIcons(), item.getAddress())).collect(Collectors.toList());
        return buildOrganizationTree(resources, root);
    }

    /**
     * 递归生成树
     *
     * @param resources 资源
     * @param root 根节点
     * @return 树
     */
    static private List<TreeViewVO> buildTree(List<ResourceDO> resources, List<TreeViewVO> root) {
        for (TreeViewVO temp : root) {
            List<TreeViewVO> children = resources.stream().filter(item -> temp.getId().equals(item.getSyResourceId()))
                    .map(item -> new TreeViewVO(item.getId(), temp.getId(), item.getName(), item.getIcons(), item.getUrl())).collect(Collectors.toList());
            buildTree(resources, children);
            if (children.size() > 0) {
                temp.setNodes(children);
            }
        }
        return root;
    }

    /**
     * 递归生成树
     *
     * @param resources 资源
     * @param root 根节点
     * @return 树
     */
    static private List<TreeViewVO> buildOrganizationTree(List<OrganizationDO> resources, List<TreeViewVO> root) {
        for (TreeViewVO temp : root) {
            List<TreeViewVO> children = resources.stream().filter(item -> temp.getId().equals(item.getSyOrganizationId()))
                    .map(item -> new TreeViewVO(item.getId(), temp.getId(), item.getName(), item.getIcons(), item.getAddress())).collect(Collectors.toList());
            buildOrganizationTree(resources, children);
            if (children.size() > 0) {
                temp.setNodes(children);
            }
        }
        return root;
    }

}
