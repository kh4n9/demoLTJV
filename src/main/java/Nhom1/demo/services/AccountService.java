package Nhom1.demo.services;

import Nhom1.demo.entities.Account;
import Nhom1.demo.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void save(Account account) {
        accountRepository.save(account);
    }
    public boolean authenticate(String username, String password) {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            // Kiểm tra mật khẩu
            return password.equals(account.getPassword());
        }
        return false;
    }
}
