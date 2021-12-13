package edu.yctc.erpsystem.bo;

import edu.yctc.erpsystem.entity.MaterialExportDO;

import java.util.List;

/**
 * MaterialExport业务逻辑对象
 *
 * @author lqp
 */
public class MaterialExportBO {

    /**
     * 原材料出库信息
     */
    private MaterialExportDO materialExportDO;

    /**
     * 原材料入库id
     */
    private List<String> materialInventoryIds;

    public MaterialExportDO getMaterialExportDO() {
        return materialExportDO;
    }

    public void setMaterialExportDO(MaterialExportDO materialExportDO) {
        this.materialExportDO = materialExportDO;
    }

    public List<String> getMaterialInventoryIds() {
        return materialInventoryIds;
    }

    public void setMaterialInventoryIds(List<String> materialInventoryIds) {
        this.materialInventoryIds = materialInventoryIds;
    }

    @Override
    public String toString() {
        return "MaterialExportBO{" +
                "materialExportDO=" + materialExportDO +
                ", materialInventoryIds=" + materialInventoryIds +
                '}';
    }

}
