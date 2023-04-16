package com.myspringboot.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.myspringboot.controller.exception.FieldMessage;
import com.myspringboot.model.Cliente;
import com.myspringboot.model.dto.ClienteDTO;
import com.myspringboot.repositories.ClienteRepository;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	 
	@Autowired
	HttpServletRequest hsr;
	@Autowired
	private ClienteRepository cr;
	
	@Override
	 public void initialize(ClienteUpdate ann) {
	 }
	 @Override
	 public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
	 List<FieldMessage> list = new ArrayList<>();
	 
	 Cliente obj = cr.findByEmail(objDto.getEmail());
	 
	 @SuppressWarnings("unchecked")
	 Map<String, String> map = (Map<String, String>) hsr.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	 
	 Integer id = Integer.parseInt( map.get("id") );
	 
	 if (obj != null && !obj.getId().equals(id)) {
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
