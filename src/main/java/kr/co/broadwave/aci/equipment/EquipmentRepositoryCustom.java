package kr.co.broadwave.aci.equipment;

import kr.co.broadwave.aci.mastercode.MasterCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Minkyu
 * Date : 2019-11-01
 * Time : 09:17
 * Remark : EquipmentRepositoryCystom
 */
public interface EquipmentRepositoryCustom {

    Page<EquipmentListDto> findByEquipmentSearch(String emNumber, String emDesignation, Long emTypeId, Long emCountryId,String emDashboard, Pageable pageable);

    List<EquipmentEmnumberDto> queryDslDeviceEmNumber(String emNumber, Long emTypeId, Long emCountryId, Long emLocationId);

    List<EquipmentBaseListDto> findByBaseEquipmentSearch(String emNumber, Long emLocationId, String emTypeId, Long emCountryId);

    List<EquipmentBaseDto> EquipmentBaseSettingQuerydsl(List<String> emNumbers);

    List<EquipmentCollectionListDto> findByEquipmentCollectionQuerydsl(Long emTypeId, Long emCountryId,Long emLocationId,Pageable pageable);

    List<EquipmentCollectionRegDto> findByRoutingEmNumberQuerydsl(List<String> streetRouting);

    EquipmentCollectionTypeDto findByRoutingEmTypeQuerydsl(String streetdevice);

    Page<EquipmentWaitingCollectionListDto> findByWaitingEquipmentCollectionQuerydsl(String emType, Long emCountryId, Long emLocationId, String emNumber, String emState, Pageable pageable);

    List<EquipmentEmNumberListDto> findByDeviceNumber(String code);
}
