package kr.co.broadwave.aci.dashboard;

import kr.co.broadwave.aci.company.Company;
import kr.co.broadwave.aci.imodel.IModel;
import kr.co.broadwave.aci.mastercode.MasterCode;
import lombok.*;

/**
 * @author Minkyu
 * Date : 2019-11-22
 * Remark :
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class DashboardDeviceListViewDto {
    private Long id; // 장비 고유ID

    private String emNumber; // 장비 번호
    private MasterCode emType; // 장비타입
    private String mdName; // 모델명
    private Double mdMaximumPayload; // 최대적재량
    private String mdUnit; // 단위
    private Company company; // 소속운영사
    private MasterCode emLocation; // 지역
    private MasterCode emCountry; // 국가
    private String emInstallDate; // 설치일자
    private String emSubName; // 약칭
    private String filePath; // 파일경로Path
    private String saveFileName; // 저장된경로

    public String getFilePath() {
        return filePath;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public String getMdName() {
        return mdName;
    }

    public Double getMdMaximumPayload() {
        return mdMaximumPayload;
    }

    public String getMdUnit() {
        return mdUnit;
    }

    public String getEmSubName() {
        return emSubName;
    }

    public Long getId() {
        return id;
    }

    public String getEmNumber() {
        return emNumber;
    }

    public String getEmType() {
        return emType.getName();
    }

    public String getCompany() {
        return company.getCsOperator();
    }

    public String getEmLocation() {
        return emLocation.getName();
    }

    public String getEmCountry() {
        return emCountry.getName();
    }

    public String getEmInstallDate() {
        return emInstallDate;
    }

}
