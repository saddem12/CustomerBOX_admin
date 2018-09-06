package com.example.j2eeapp.ui.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.example.j2eeapp.dao.AgenceDao;
import com.example.j2eeapp.domain.AgenceEntity;


@FacesConverter("agenceConverter")
public class AgenceConverter implements Converter{

	private AgenceDao agenceDao;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {

			try {
				AgenceEntity cat = agenceDao.findById(Long.valueOf(value)); 

				return cat;
			} catch(NumberFormatException e) {
			}
		}
		else {
			return null;
		}
		return value;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
			return String.valueOf(((AgenceEntity) object).getId());
		}
		else {
			return null;
		}
	}

	public AgenceDao getAgenceDao() {
		return agenceDao;
	}

	public void setAgenceDao(AgenceDao agenceDao) {
		this.agenceDao = agenceDao;
	}
	

} 