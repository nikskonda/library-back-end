package by.bntu.fitr.service.user.order;

import by.bntu.fitr.model.user.util.Address;
import by.bntu.fitr.repository.user.order.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderService {

    private UserOrderRepository repository;

    @Autowired
    public UserOrderService(UserOrderRepository repository) {
        this.repository = repository;
    }

    public List<Address> findAddressesByUsername(String username) {
        return repository.findAddressesByUserUsername(username);

    }

}
