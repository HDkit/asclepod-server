package com.hms.microservices.patient_service.common.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MedicalRecordMapper {
}
