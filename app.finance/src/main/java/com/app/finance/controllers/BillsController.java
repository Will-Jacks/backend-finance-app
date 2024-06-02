package com.app.finance.controllers;

import com.app.finance.entities.Bills;
import com.app.finance.entities.Users;
import com.app.finance.services.BillsService;
import com.app.finance.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillsController {
    private final BillsService billsService;
    private final UserService usersService;

    public BillsController (BillsService billsService, UserService usersService){
        this.billsService = billsService;
        this.usersService = usersService;
    };


    @GetMapping("/all")
    public List<Bills> getAll(){
        return billsService.getBills();
    }

    @GetMapping("/{userId}")
    public List<Bills> getBillsByUserId(@PathVariable Long userId) {
        return billsService.getBillsByUserId(userId);
    }


    @PostMapping("/save/{userId}")
    public List<Bills> saveBill(@PathVariable Long userId, @RequestBody Bills bill){
        Users user = usersService.getUserById(userId);
        bill.setUser(user);
        billsService.saveBills(bill);
        return billsService.getBills();
    }

    @PutMapping("/update/{billId}")
    public List<Bills> updateBill(@PathVariable Long billId, @RequestBody Bills bills) {
        Bills existingBill = billsService.getBillById(billId);
        if (existingBill == null) {
            throw new RuntimeException("Bill not found");
        }

        // Copy non-null properties from the incoming bill to the existing bill
        if (bills.getTitle() != null) {
            existingBill.setTitle(bills.getTitle());
        }
        if (bills.getDescription() != null) {
            existingBill.setDescription(bills.getDescription());
        }
        if (bills.getState() != null) {
            existingBill.setState(bills.getState());
        }
        if (bills.getValue() != null) {
            existingBill.setValue(bills.getValue());
        }

        // Ensure the user is set (if it comes in the request, else keep the existing one)
        if (bills.getUser() != null) {
            existingBill.setUser(bills.getUser());
        } else {
            // Optionally, you can throw an exception or handle it differently if user is mandatory
            throw new RuntimeException("User must not be null");
        }

        billsService.saveBills(existingBill);
        return billsService.getBills();
    }

    @DeleteMapping("/delete/{id}")
    public List<Bills> deleteBill(@PathVariable Long id){
        billsService.deleteBill(id);
        return billsService.getBills();
    }
}
