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

    @ManyToOne
    private CustomerType type;
    
    
    @OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="customer", targetEntity=CustomerExtension.class, orphanRemoval=true)
    private CustomerExtension extension;
    
    // Очень важно, устанавливать параметер orphanRemoval=true, без него, удаления происходит только в одной таблице CUSTOMER_ADDRESS, 
    // в таблице ADRESS записи остаются.
    @OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
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
	
	public Long getId() {
		return id;
	}

	public void setExtension(CustomerExtension extension) {
		this.extension = extension;
		if (extension != null) {
			this.extension.setCustomer(this);
		}
	}
	
	public void addAdress(Adress adress){
		adress.setCustomer(this);
		this.adress.add(adress);
	}
	
	public Set<Adress> getAdress() {
		return adress;
	}

	public CustomerType getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", type=" + type + "]";
	}
    

}
