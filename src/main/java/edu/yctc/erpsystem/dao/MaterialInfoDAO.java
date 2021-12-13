package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.MaterialInfoDO;
import edu.yctc.erpsystem.vo.MaterialInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 材料原始信息DAO接口
 *
 * @author lqp
 */
@Mapper
public interface MaterialInfoDAO {

    /**
     * 获得料品原始信息数据的数量
     *
     * @param params 过滤参数
     * @return 原材料报废数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得料品原始信息
     *
     * @param params 过滤参数
     * @return 料品原始信息链表
     */
    List<MaterialInfoDO> getMaterialInfo(Map<String, Object> params);

    /**
     * 插入一条记录
     *
     * @param materialInfoDO 料品原始信息实体
     */
    void insert(MaterialInfoDO materialInfoDO);

    /**
     * 删除记录
     *
     * @param id 记录id
     */
    void delete(@Param("id") String id);

    /**
     * 修改料品原始信息
     *
     * @param materialInfoDO 料品原始信息实体
     */
    void updateMaterialInfo(MaterialInfoDO materialInfoDO);

    /**
     * 通过id修改checkerId checkerFlag
     *
     * @param materialInfoDO 料品原始信息实体
     */
    void updateCheckerById(MaterialInfoDO materialInfoDO);

    /**
     * 通过主键获得材料原始信息
     *
     * @param id 主键
     * @return 材料原始信息
     */
    MaterialInfoDO getMaterialInfoById(@Param("id") String id);

    /**
     * 通过主键获得所有材料原始信息
     *
     * @param ids 主键链表
     * @return 材料原始信息链表
     */
    List<MaterialInfoDO> getMaterialInfoByIds(@Param("ids") List<String> ids);

    /**
     * 通过品名等获得所有材料原始信息
     *
     * @param supplierId 供应商id
     * @param spec 规格/初R值/电阻线(线径)
     * @param itemName 品名／磁棒／尺寸(材质)
     * @param unitPrice 单价
     * @return 材料原始信息链表
     */
    List<MaterialInfoVO> getMaterialInfoBySome(@Param("supplierId") List<String> supplierId, @Param("spec") List<String> spec, @Param("itemName") List<String> itemName, @Param("unitPrice") List<Double> unitPrice);

}
