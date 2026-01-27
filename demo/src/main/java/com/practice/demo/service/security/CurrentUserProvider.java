package com.practice.demo.service.security;

import com.practice.demo.beans.User;

public interface CurrentUserProvider {
     
	User getCurrentUser();
	
}
