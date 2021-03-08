package com.zero.base.sys.modular.system.factory;


import com.zero.base.common.pojo.node.LayuiTreeNode;

/**
 * 封装layui属性组件数据
 *
 * @author yongzhi
 * @Date 2019-8-26 14:01
 */
public class LayuiTreeFactory {

    /**
     * 生成layuiTree根节点
     *
     * @author yongzhi
     * @Date 2019-8-26 14:21
     */
    public static LayuiTreeNode createRoot() {
        LayuiTreeNode treeNode = new LayuiTreeNode();
        treeNode.setChecked(true);
        treeNode.setId(0L);
        treeNode.setTitle("顶级");
        treeNode.setSpread(true);
        treeNode.setPid(-1L);
        return treeNode;
    }

}
