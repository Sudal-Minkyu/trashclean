package kr.co.broadwave.aci.equipment;

import kr.co.broadwave.aci.company.Company;
import kr.co.broadwave.aci.imodel.IModel;
import kr.co.broadwave.aci.mastercode.MasterCode;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Minkyu
 * Date : 2019-10-31
 * Time : 16:45
 * Remark : 장비등록 클래스
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="bs_equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="em_id")
    private Long id; // 장비관리 고유ID

    @Column(unique = true,name="em_number")
    private String emNumber; // 장비 번호

    @Column(name="em_cereal_number")
    private String emCerealNumber; // 장비 시리얼 번호

    @Column(name="em_designation")
    private String emDesignation; // 장비명칭

    @ManyToOne(targetEntity = MasterCode.class,fetch = FetchType.EAGER)
    @JoinColumn(name="em_type")
    private MasterCode emType; // 장비타입

    @ManyToOne(targetEntity = MasterCode.class,fetch = FetchType.EAGER)
    @JoinColumn(name="em_country")
    private MasterCode emCountry; // 국가

    @ManyToOne(targetEntity = MasterCode.class,fetch = FetchType.EAGER)
    @JoinColumn(name="em_location")
    private MasterCode emLocation; // 지역

    @Column(name="em_aws_number")
    private String emAwsNumber; // AWS상 Device ID

    @Column(name="em_dashboard")
    private String emDashboard; // 대시보드 포함

    @Column(name="em_embedded_number")
    private String emEmbeddedNumber; // 임베디드 기판 번호

    @ManyToOne(targetEntity = IModel.class,fetch = FetchType.EAGER)
    @JoinColumn(name="md_id")
    private IModel mdId; // 등록모델

    @ManyToOne(targetEntity = Company.class,fetch = FetchType.EAGER)
    @JoinColumn(name="cs_id")
    private Company company; // 소속운영사

    @Column(name="em_install_date")
    private String emInstallDate; // 설치일자

    @Column(name="em_subname")
    private String emSubName; // 장비약칭

    @Column(name="em_latitude")
    private Double emLatitude; // 위도

    @Column(name="em_longitude")
    private Double emLongitude; // 경도

    @Column(name="v_interval")
    private Double vInterval; // 센서 데이터 주기적 송신시간

    @Column(name="v_presstime")
    private Double vPresstime; // 압축 정지시간

    @Column(name="v_inputtime")
    private Double vInputtime; // 투입구 열림 시간

    @Column(name="v_solenoidtime")
    private Double vSolenoidtime; // 솔레노이드 열림 시간

    @Column(name="v_yellowstart")
    private Double vYellowstart; // 쓰레기량에 따른 노랑색등의 경계값

    @Column(name="v_redstart")
    private Double vRedstart; // 쓰레기량에 따른 적색등의 경계값

    @Column(name="v_manager")
    private String vManager; // 담당수거원이름

    @Column(name="v_manager_call")
    private String vManagerCall; // 담당수거원번호

    @Column(name="v_manager_id")
    private String vManagerid; // 아이테이너장비 아이디

    @Column(name="v_manager_pass")
    private String vManagerPass; // 아이테이너장비 비번

    @Column(name="v_language")
    private String vLanguage; // 사용언어

    @Column(name="v_mqtt_interval")
    private String vMqttInterval; // mqtt 데이터전송주기(단위,초)

    @Column(name="v_lora_interval")
    private String vLoraInterval; // lora 데이터 전송주기(단위,초)

    @Column(name="v_scale_safe_interval")
    private String vScaleSafeInterval; //

    @Column(name="v_shutter_idle_interval")
    private String vShutterIdleInterval; //

    @Column(name="v_waste_press_interval")
    private String vWastePressInterval; //

    @Column(name="v_waste_capacity")
    private String vWasteCapacity; //

    @Column(name="v_ozon_time")
    private String vOzonTime; //

    @Column(name="v_pay_preamt")
    private String vPayPreamt; //

    @Column(name="v_pay_unit_price")
    private String vPayUnitPrice; // 기준가격

    @Column(name="v_pay_unit_weight")
    private String vPayUnitWeight; // 기준무게

    @Column(name="v_pay_method")
    private String vPayMethod; //

    @Column(name="em_certification_number")
    private String emCertificationNumber; // AWS보안인증서코드

    @ManyToOne(targetEntity = MasterCode.class,fetch = FetchType.EAGER)
    @JoinColumn(name="em_state")
    private MasterCode emState; // 설비상태(iTainer만 해당)

    @Column(name="insert_date")
    private LocalDateTime insertDateTime;

    @Column(name="insert_id")
    private String insert_id;

    @Column(name="modify_date")
    private LocalDateTime modifyDateTime;

    @Column(name="modify_id")
    private String modify_id;

}
