package kr.co.broadwave.aci.collection;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Minkyu
 * Date : 2020-01-30
 * Time :
 * Remark : 수거업무리스트 클래스 TaskListDto
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Slf4j
public class CollectionTaskListDto {
    private Long id;
    private Integer ctSeq;
    private String ctCode; // 수거관리코드
    private String yyyymmdd; // 수거처리일
    private String deviceid; // 장비코드
    private String devicetype; // 장비타입
    private String devicemodeltype; // 장비모델종류
    private String csOperator; // 운영사
    private String filePath; // 파일경로 1
    private String saveFileName; // 파일경로 2

    public Integer getCtSeq() {
        return ctSeq;
    }

    public String getFilePath() {
        if(filePath==null){
            filePath = "/defaultimage";
            return filePath;
        }else{
            return filePath;
        }
    }

    public String getSaveFileName() {
        if(saveFileName==null){
            saveFileName = "/model.jpg";
            return saveFileName;
        }else{
            return "/s_"+saveFileName;
        }
    }

    public Long getId() {
        return id;
    }

    public String getCtCode() {
        return ctCode;
    }

    public StringBuffer getYyyymmdd() {
        StringBuffer yyyymmddDate = new StringBuffer(yyyymmdd); // ex)2020-11-04
        yyyymmddDate.insert(4,'-');
        yyyymmddDate.insert(7,'-');
        return yyyymmddDate;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public String getDevicemodeltype() {
        return devicemodeltype;
    }

    public String getCsOperator() {
        return csOperator;
    }
}
