package com.gabrielgrs.aulaspring.services.validation;

import com.gabrielgrs.aulaspring.domain.Cliente;
import com.gabrielgrs.aulaspring.domain.enuns.TipoCliente;
import com.gabrielgrs.aulaspring.dto.ClienteNewDTO;
import com.gabrielgrs.aulaspring.repositories.ClienteRepository;
import com.gabrielgrs.aulaspring.resources.exception.FieldMessage;
import com.gabrielgrs.aulaspring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {

        List<FieldMessage> fieldMessageList = new ArrayList<>();

        if (clienteNewDTO.getTipo() == null) {
            fieldMessageList.add(new FieldMessage("tipo", "Tipo nao pode ser nulo"));
        }

        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(clienteNewDTO.getCpfOuCnpj())) {
            fieldMessageList.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(clienteNewDTO.getCpfOuCnpj())) {
            fieldMessageList.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Cliente aux = clienteRepository.findByEmail(clienteNewDTO.getEmail());
        if (aux != null) {
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
