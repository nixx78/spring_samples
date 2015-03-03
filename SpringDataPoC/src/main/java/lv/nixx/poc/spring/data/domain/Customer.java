package lv.nixx.poc.spring.data.domain;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    private CustomerType type;
    
    @OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="customer", targetEntity=CustomerExtension.class)
    private CustomerExtension extension;
    
    @OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Adress> adress = new HashSet<>();

    protected Customer() {
    }

    public Customer(String firstName, String lastName, CustomerType customerType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = customerType;
    }

	public CustomerExtension getExtension() {
		return extension;
	}

	public void setExtension(CustomerExtension extension) {
		this.extension = extension;
		this.extension.setCustomer(this);
	}
	
	public void addAdress(Adress adress){
		adress.setCustomer(this);
		this.adress.add(adress);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", type=" + type + "]";
	}
    

}
