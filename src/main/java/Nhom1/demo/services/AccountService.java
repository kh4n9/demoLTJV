package Nhom1.demo.services;

import Nhom1.demo.entities.Account;
import Nhom1.demo.entities.Hash;
import Nhom1.demo.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    // không hash
    public void save(Account account) {
        accountRepository.save(account);
    }
    public void save(Account account, String type) {
        Hash hash = new Hash();
        switch (type) {
            case "md5+salt":
                account.setPassword(hash.computeMD5(hash.addSalt(account.getUsername(), account.getPassword())));
                accountRepository.save(account);
                break;
            case "md5+sha256":
                account.setPassword(hash.computeSHA256(hash.computeMD5(account.getPassword())));
                accountRepository.save(account);
                break;
            default:
                accountRepository.save(account);
        }
    }
    public boolean authenticate(String username, String password) {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            return password.equals(account.getPassword());
        }
        return false;
    }
    public boolean authenticate(String username, String password, String type) {
        Hash hash = new Hash();
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            if (type.equals("md5+salt")) {
                return account.getPassword().equals(hash.computeMD5(hash.addSalt(account.getUsername(), password)));
            }
            if (type.equals("md5+sha256")) {
                return account.getPassword().equals(hash.computeSHA256(hash.computeMD5(password)));
            }
            else {
                return password.equals(account.getPassword());
            }
        }
        return false;
    }
}
