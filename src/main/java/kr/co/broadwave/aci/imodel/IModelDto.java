package kr.co.broadwave.aci.imodel;

import kr.co.broadwave.aci.company.Company;
import kr.co.broadwave.aci.mastercode.MasterCode;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Minkyu
 * Date : 2019-12-19
 * Time : 16:45
 * Remark : 모델등록 클래스 Dto
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class IModelDto {
    private Long id; // 장비 고유ID
    private String mdNumber; // 모델 번호
    private String mdName; // 모델명
    private MasterCode mdTypeId; // 모델타입
    private String mdSubname; // 모델약칭
    private String mdRemark; // 모델특이사항

    private LocalDateTime insertDateTime;
    private String insert_id;
    private LocalDateTime modifyDateTime;
    private String modify_id;

    public Long getId() {
        return id;
    }

    public String getMdNumber() {
        return mdNumber;
    }

    public String getMdName() {
        return mdName;
    }

    public Long getMdTypeId() {
        return mdTypeId.getId();
    }

    public String getMdSubname() {
        return mdSubname;
    }

    public String getMdRemark() {
        return mdRemark;
    }

    public LocalDateTime getInsertDateTime() {
        return insertDateTime;
    }

    public String getInsert_id() {
        return insert_id;
    }

    public LocalDateTime getModifyDateTime() {
        return modifyDateTime;
    }

    public String getModify_id() {
        return modify_id;
    }
}