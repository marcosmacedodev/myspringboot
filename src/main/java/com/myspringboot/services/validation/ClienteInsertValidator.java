package com.myspringboot.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.myspringboot.controller.exception.FieldMessage;
import com.myspringboot.model.Cliente;
import com.myspringboot.model.dto.ClienteNewDTO;
import com.myspringboot.model.enums.TipoCliente;
import com.myspringboot.repositories.ClienteRepository;
import com.myspringboot.services.validation.utils.Utils;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	 
	@Autowired
	private ClienteRepository cr;
	
	@Override
	 public void initialize(ClienteInsert ann) {
	 }
	 @Override
	 public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	 List<FieldMessage> list = new ArrayList<>();
	 
	 if (objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getId()) && !Utils.validateCPF(objDto.getCpfouCnpJ())) {
		 list.add(new FieldMessage("cpfouCnpJ", "CPF inválido"));
	 }
	 
	 if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getId()) && !Utils.validateCNPJ(objDto.getCpfouCnpJ())) {
		 list.add(new FieldMessage("cpfouCnpJ", "CNPJ inválido"));
	 }
	 
	 Cliente obj = cr.findByEmail(objDto.getEmail());
	 
	 if (obj != null) {
		 list.add(new FieldMessage("email", "Este e-mail já foi cadastrado. Tente outro e-mail."));
	 }
	 
	 // inclua os testes aqui, inserindo erros na lista

	 for (FieldMessage e : list) {
	 context.disableDefaultConstraintViolation();
	 context.buildConstraintViolationWithTemplate(e.getMessage())
	 .addPropertyNode(e.getFieldName()).addConstraintViolation();
	 }
	 return list.isEmpty();
	 }

}
