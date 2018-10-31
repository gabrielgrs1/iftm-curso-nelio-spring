package com.gabrielgrs.aulaspring.services.validation;

import com.gabrielgrs.aulaspring.domain.Cliente;
import com.gabrielgrs.aulaspring.domain.enuns.TipoCliente;
import com.gabrielgrs.aulaspring.dto.ClienteDTO;
import com.gabrielgrs.aulaspring.dto.ClienteNewDTO;
import com.gabrielgrs.aulaspring.repositories.ClienteRepository;
import com.gabrielgrs.aulaspring.resources.exception.FieldMessage;
import com.gabrielgrs.aulaspring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> fieldMessageList = new ArrayList<>();

        Cliente cliente = clienteRepository.findByEmail(clienteDTO.getEmail());

        if (cliente != null && !uriId.equals(cliente.getId())) {
            fieldMessageList.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage fieldMessage :
                fieldMessageList) {

            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(fieldMessage.getMessage())
                    .addPropertyNode(fieldMessage.getFieldName())
                    .addConstraintViolation();
        }

        return fieldMessageList.isEmpty();
    }
}
