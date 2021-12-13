package edu.yctc.erpsystem.dao;

import edu.yctc.erpsystem.entity.QualitySamplingDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 质量抽检信息DAO接口
 *
 * @author zzy
 */
@Mapper
public interface QualitySamplingDAO {

    /**
     * 获得质量抽检信息数据的数量
     *
     * @param params 过滤参数
     * @return 质量抽检信息数据的数量
     */
    int count(Map<String, Object> params);

    /**
     * 获得所有质量抽检信息
     *
     * @param params 过滤参数
     * @return 质量抽检信息链表
     */
    List<QualitySamplingDO> getQualitySampling(Map<String, Object> params);

    /**
     * 新增质量抽检信息
     *
     * @param qualitySamplingDO 质量抽检实体
     */
    void insert(QualitySamplingDO qualitySamplingDO);

    /**
     * 删除
     *
     * @param id 质量抽检信息id
     */
    void delete(@Param("id") String id);

    /**
     * 编辑质量抽检信息
     *
     * @param qualitySamplingDO 质量抽检实体
     */
    void updateQualitySampling(QualitySamplingDO qualitySamplingDO);

    /**
     * 编辑审核信息
     *
     * @param qualitySamplingDO 质量抽检实体
     */
    void updateCheckerById(QualitySamplingDO qualitySamplingDO);

}
