package lv.nixx.poc.spring.data.domain;

import javax.persistence.*;

@Entity
@Table(name="Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    private CustomerType type;
    
    @OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="customer", targetEntity=CustomerExtension.class)
    private CustomerExtension extension;

    protected Customer() {}

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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", type=" + type + "]";
	}
    

}
