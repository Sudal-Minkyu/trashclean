package kr.co.broadwave.aci.equipment;

import kr.co.broadwave.aci.awsiot.ACIAWSIoTDeviceService;
import kr.co.broadwave.aci.company.CompanyListDto;
import kr.co.broadwave.aci.company.CompanyRepositoryCustom;
import kr.co.broadwave.aci.keygenerate.KeyGenerateService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Minkyu
 * Date : 2019-10-31
 * Time : 16:56
 * Remark : Equipment Service
 */
@Slf4j
@Service
public class EquipmentService {
    private final ModelMapper modelMapper;
    private final EquipmentRepository equipmentRepository;
    private final KeyGenerateService keyGenerateService;
    private final EquipmentRepositoryCustom equipmentRepositoryCustom;
    private final CompanyRepositoryCustom companyRepositoryCustom;
    private final ACIAWSIoTDeviceService aciawsIoTDeviceService;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository,
                            KeyGenerateService keyGenerateService,
                            CompanyRepositoryCustom companyRepositoryCustom,
                            EquipmentRepositoryCustom equipmentRepositoryCustom,
                            ACIAWSIoTDeviceService aciawsIoTDeviceService,
                            ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.aciawsIoTDeviceService = aciawsIoTDeviceService;
        this.companyRepositoryCustom = companyRepositoryCustom;
        this.equipmentRepositoryCustom = equipmentRepositoryCustom;
        this.equipmentRepository = equipmentRepository;
        this.keyGenerateService = keyGenerateService;
    }

    public Equipment save(Equipment equipment) throws Exception {

        //장비코드 가공하기
        if (equipment.getEmNumber() == null || equipment.getEmNumber().isEmpty()){

            String emTypeCode = equipment.getEmType().getCode();
            String emCountryCode = equipment.getEmCountry().getCode();
            String emLocationCode = equipment.getEmLocation().getCode();

            String emNumber = keyGenerateService.keyGenerate("bs_equipment",emTypeCode+'-'+emCountryCode+'-'+emLocationCode+'-',equipment.getModify_id());

            //고유 장비번호 저장이름 바꾸기 : 장비타입-국가-지역-순번
            equipment.setEmNumber(emNumber);
        }

        if(equipment.getEmType().getCode().equals("ISOL")) {
            String p1 = Double.toString(equipment.getVInterval());
            String p2 = Double.toString(equipment.getVPresstime());
            String p3 = Double.toString(equipment.getVInputtime());
            String p4 = Double.toString(equipment.getVSolenoidtime());
            String p5 = Double.toString(equipment.getVYellowstart());
            String p6 = Double.toString(equipment.getVRedstart());

            aciawsIoTDeviceService.setDeviceBaseSetting(equipment.getEmNumber(),p1,p2,p3,p4,p5,p6);
        }

        return equipmentRepository.save(equipment);
    }

    public Optional<Equipment> findByEmNumber(String emNumber) {
        return equipmentRepository.findByEmNumber(emNumber);
    }

    public Page<EquipmentListDto> findByEquipmentSearch
            (String emNumber, String emDesignation, Long emTypeId,Long emCountryId,String emDashboard, Pageable pageable) {
        return equipmentRepositoryCustom.findByEquipmentSearch(emNumber,emDesignation,emTypeId,emCountryId,emDashboard,pageable);
    }


    public EquipmentDto findById(Long id) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        return optionalEquipment.map(equipment -> modelMapper.map(equipment, EquipmentDto.class)).orElse(null);
//        if (optionalEquipment.isPresent()) { // 윗줄과같은거(73Line)
//            return modelMapper.map(optionalEquipment.get(), EquipmentDto.class);
//        } else {
//            return null;
//        }
    }

    public void delete(Equipment equipment) {
        equipmentRepository.delete(equipment);
    }

    public Page<CompanyListDto> findByAgencySearch(String csNumber, String csOperator, Pageable pageable) {
        return companyRepositoryCustom.findByAgencySearch(csNumber,csOperator,pageable);
    }

    public List<EquipmentEmnumberDto> queryDslDeviceEmNumber(String emNumber, Long emTypeId, Long emCountryId, Long emLocationId) {
        return equipmentRepositoryCustom.queryDslDeviceEmNumber(emNumber,emTypeId,emCountryId,emLocationId);
    }

    public List<EquipmentBaseListDto> findByBaseEquipmentSearch(String emNumber, Long emLocationId, String emTypeId, Long emCountryId) {
        return equipmentRepositoryCustom.findByBaseEquipmentSearch(emNumber,emLocationId,emTypeId,emCountryId);
    }

    public List<EquipmentBaseDto> EquipmentBaseSettingQuerydsl(List<String> emNumbers) {
        return equipmentRepositoryCustom.EquipmentBaseSettingQuerydsl(emNumbers);
    }

    public List<EquipmentCollectionListDto> findByEquipmentCollectionQuerydsl(Long emTypeId, Long emCountryId, Long emLocationId,Pageable pageable) {
        return equipmentRepositoryCustom.findByEquipmentCollectionQuerydsl(emTypeId,emCountryId,emLocationId,pageable);
    }

    public List<EquipmentCollectionRegDto> findByRoutingEmNumberQuerydsl(List<String> streetRouting) {
        return equipmentRepositoryCustom.findByRoutingEmNumberQuerydsl(streetRouting);
    }

    public EquipmentCollectionTypeDto findByRoutingEmTypeQuerydsl(String streetdevice) {
        return equipmentRepositoryCustom.findByRoutingEmTypeQuerydsl(streetdevice);
    }

    public Page<EquipmentWaitingCollectionListDto> findByWaitingEquipmentCollectionQuerydsl(String emType, Long emCountryId, Long emLocationId, String emNumber, String emState, Pageable pageable) {
        return equipmentRepositoryCustom.findByWaitingEquipmentCollectionQuerydsl(emType,emCountryId,emLocationId,emNumber,emState,pageable);
    }

    public List<EquipmentEmNumberListDto> findByDeviceNumber(String code) {
        return equipmentRepositoryCustom.findByDeviceNumber(code);
    }
}
