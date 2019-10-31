package kr.co.broadwave.aci.company;

import kr.co.broadwave.aci.accounts.AccountRole;
import kr.co.broadwave.aci.bscodes.ApprovalType;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Minkyu
 * Date : 2019-10-31
 * Time : 16:45
 * Remark : 업체등록 클래스 Dto
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CompanyDto {

    private String csNumber; // 관리코드

    private String csOperator; // 운영사명
    private String csOperatorSub; // 운영사명(약칭)
    private String csDivision; // 업체구분
    private String csRegional; // 운영권역
    private String csRepresentative; // 대표자
    private String csBuisnessNumber; // 사업자번호
    private String csManager; // 담당자
    private String csTelephone; // 전화번호
    private String csFax; // 팩스번호

}
