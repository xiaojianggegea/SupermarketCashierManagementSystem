package com.guohuaijiang.service;

import com.guohuaijiang.bean.User;
import com.guohuaijiang.util.MyData;

public class UserServiceImpl implements UserService {

	@Override
	public User loginByUsernameAndUsernamepwd(String username, String userpwd) {
		User user = null;

		User userArray[] = MyData.userArray;
		if (userArray != null && userArray.length > 0) {
			for (int i = 0; i < userArray.length; i++) {
				User temp_user = userArray[i];
				if (temp_user != null) {
					if (temp_user.getUser_name().equals(username) && temp_user.getUser_pwd().equals(userpwd)) {
//						user = new User();
//						user.setUser_name(username);
//						user.setUser_type(temp_user.getUser_type());
						user=temp_user;
						break;
					}
				}
			}
		}

		return user;
	}

}
