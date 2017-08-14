package br.com.season.springproject.utils;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.season.springproject.entity.UserProfile;
import br.com.season.springproject.service.UserService;

@Component
public class UserProfileUtils extends PropertyEditorSupport {
	
	@Autowired
	private UserService userService;
	
	@Override
    public void setAsText(String text) {
        UserProfile userProfile = this.userService.findRoleById(Integer.valueOf(text));

        this.setValue(userProfile);
    }

}
