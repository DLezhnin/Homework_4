package lab4.service.impl;


import jakarta.transaction.Transactional;
import lab4.entity.Logins;
import lab4.entity.Users;
import lab4.model.Model;
import lab4.repository.LoginsRepo;
import lab4.repository.UsersRepo;
import lab4.service.FileLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class LoadServiceImpl implements FileLoadService {
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    LoginsRepo loginsRepo;


    @Override
    public void saveinfo(List<Model> models) {
        HashMap<String, Users> userlist = new HashMap<>();
        int cnt;
        int logcnt = 0;
        for (int i = 0; i < models.size(); i++) {

            Model model = models.get(i);
            Users user;
            if(userlist.containsKey(model.username)){
                user = userlist.get(model.username);
            } else {
                Optional<Users> opt = usersRepo.findByusername(model.username);
                if (opt.isPresent()) {
                    user = opt.get();
                } else {
                    cnt = userlist.size();
                    cnt++;
                    user = new Users();
                    user.id = cnt;
                    user.username = model.username;
                    user.fio = model.fio;
                    usersRepo.save(user);
                }
                userlist.put(user.username,user);
            }
            logcnt++;
            Logins logins = new Logins();
            logins.id = logcnt;
            logins.accessdate = model.accessdate;
            logins.application = model.app;
            logins.userid = user;
            loginsRepo.save(logins);
        }
    }
}
