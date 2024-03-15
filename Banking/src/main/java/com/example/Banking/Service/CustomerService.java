package com.example.Banking.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Banking.Dao.AccountRepository;
import com.example.Banking.Dao.CustomerRepository;
import com.example.Banking.Model.Accounts;
import com.example.Banking.Model.Customer;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountRepository accountRepository;

	public String saveCustomer(Customer customer) {
		Accounts account = new Accounts();
		account.setAcctID(customer.getAcctID());
		account.setBalance(0);
		account.setAcctStatus("Active");
		customerRepository.save(customer);
		accountRepository.save(account);
		return "Customer "+customer.getCustName()+" Account created successfully";
	}

	public Customer getCustomerInfo(int acctID) {
		return customerRepository.findById(acctID).orElse(null);
	}

	public void deleteCustomer(int acctID) {
		customerRepository.deleteById(acctID);
	}

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Customer customer = customerRepository.findByAcctID(Integer.parseInt(username));
			if (customer != null) {
				return new MyCustomerDetails(customer);
			}
			throw new UsernameNotFoundException(username);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	class MyCustomerDetails implements UserDetails {
		private final Customer customer;

		public MyCustomerDetails(Customer customer) {
			this.customer = customer;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getPassword() {
			// TODO customer-generated method stub
			return customer.getPassword();
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return customer.getAcctID() + "";
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

		// Implement all methods of UserDetails interface including getUsername(),
		// getPassword(), getAuthorities()

	}*/

}
