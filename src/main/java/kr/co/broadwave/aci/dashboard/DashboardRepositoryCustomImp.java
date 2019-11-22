package kr.co.broadwave.aci.dashboard;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import kr.co.broadwave.aci.company.Company;
import kr.co.broadwave.aci.equipment.QEquipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Minkyu
 * Date : 2019-11-22
 * Remark :
 */
@Repository
public class DashboardRepositoryCustomImp  extends QuerydslRepositorySupport implements DashboardRepositoryCustom{

    public DashboardRepositoryCustomImp() {
        super(Company.class);
    }

    @Override
    public Page<DashboardDeviceListViewDto> findByDashboardListView(Pageable pageable){

        QEquipment equipment = QEquipment.equipment;

        JPQLQuery<DashboardDeviceListViewDto> query = from(equipment)
                .select(Projections.constructor(DashboardDeviceListViewDto.class,
                        equipment.id,
                        equipment.emNumber,
                        equipment.emType,
                        equipment.emMaximumPayload,
                        equipment.emUnit,
                        equipment.company,
                        equipment.emLocation,
                        equipment.emCountry,
                        equipment.emInstallDate
                ));

        query.orderBy(equipment.id.desc());

        final List<DashboardDeviceListViewDto> equipments = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(equipments, pageable, query.fetchCount());
    }
}