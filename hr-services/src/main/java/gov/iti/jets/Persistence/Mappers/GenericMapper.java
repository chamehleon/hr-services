package gov.iti.jets.Persistence.Mappers;

import gov.iti.jets.Persistence.DTOs.BaseDTO;
import gov.iti.jets.Persistence.DTOs.EmployeeDTO;
import gov.iti.jets.Persistence.Entities.BaseEntity;
import gov.iti.jets.Persistence.Entities.Employee;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

public interface GenericMapper <ENTITY extends BaseEntity, DTO extends BaseDTO> {


    DTO  toDto(ENTITY entity);
    ENTITY toEntity(DTO dto);



    Collection<DTO> collectionToDto(Collection<ENTITY> collection);
    Collection<ENTITY> collectionToEntity(Collection<DTO> collection);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    default <T> T partialUpdate(T dto, T entity) {
        if (dto != null) {
            Method[] methods = dto.getClass().getMethods();
            for (Method method : methods) {
                if (isGetter(method)) {
                    try {
                        Object newValue = method.invoke(dto);
                        if (newValue != null) {
                            String setterName = method.getName().replaceFirst("get", "set");
                            Method setterMethod = entity.getClass().getMethod(setterName, method.getReturnType());
                            setterMethod.invoke(entity, newValue);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        toEntityAfterMapping((ENTITY) entity, (DTO) dto);
        return entity;
    }
    private boolean isGetter(Method method) {
        String methodName = method.getName();
        return methodName.startsWith("get")
                && !methodName.equals("getClass")
                && method.getParameterTypes().length == 0
                && !void.class.equals(method.getReturnType());
    }

     void toEntityAfterMapping(@MappingTarget ENTITY entity, DTO dto);

}
