package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin) {
        // パスワードをハッシュ化
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode(admin.getPassword()));

        // リポジトリに保存
        return adminRepository.save(admin);
    }
    // ログイン機能
    public boolean authenticateAdmin(String email, String rawPassword) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(rawPassword, admin.getPassword());
        }
        return false; // 管理者が存在しない、または認証失敗
    }

}
