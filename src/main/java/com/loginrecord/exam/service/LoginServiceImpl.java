package com.loginrecord.exam.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loginrecord.exam.entity.Login;
import com.loginrecord.exam.repository.LoginRepository;

@Service
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService {
	
	private LoginRepository loginRepository;
	
	@Autowired
	public LoginServiceImpl(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public List<Login> getUniqueDates() {
		return loginRepository.getUniqueDates();
	}

	@Override
	public List<Login> getUsers(Date startDate, Date endDate) {
		return loginRepository.getUsers(startDate, endDate);
	}

	@Override
	public Map<String, Long> getLogins(Date start, Date end, String[] attribute1, String[] attribute2, String[] attribute3,
			String[] attribute4) {
		List<Login> logins = loginRepository.getLogins(start, end, attribute1, attribute2, attribute3, attribute4);
		Map<String, Long> loginCount = new TreeMap<String, Long>();
		if (logins != null && !logins.isEmpty()) {
			for (Login login : logins) {
				if (!loginCount.containsKey(login.getUser()))
					loginCount.put(login.getUser(), 0L);
				
				loginCount.put(login.getUser(), loginCount.get(login.getUser()) + 1L);
			}
		}
		return loginCount;
	}

	@Override
	@Transactional(readOnly = false)
	public long insert() {
		return loginRepository.insert();
	}

}
