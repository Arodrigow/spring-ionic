package com.spring.springionic;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.spring.springionic.domain.Address;
import com.spring.springionic.domain.AppOrder;
import com.spring.springionic.domain.BilletPayment;
import com.spring.springionic.domain.Category;
import com.spring.springionic.domain.City;
import com.spring.springionic.domain.Client;
import com.spring.springionic.domain.CreditCardPayment;
import com.spring.springionic.domain.ItemOrder;
import com.spring.springionic.domain.Payment;
import com.spring.springionic.domain.Product;
import com.spring.springionic.domain.State;
import com.spring.springionic.domain.enums.ClientType;
import com.spring.springionic.domain.enums.PaymentStatus;
import com.spring.springionic.repositories.AddressRepository;
import com.spring.springionic.repositories.CategoryRepository;
import com.spring.springionic.repositories.CityRepository;
import com.spring.springionic.repositories.ClientRepository;
import com.spring.springionic.repositories.ItemOrderRepository;
import com.spring.springionic.repositories.OrderRepository;
import com.spring.springionic.repositories.PaymentRepository;
import com.spring.springionic.repositories.ProductRepository;
import com.spring.springionic.repositories.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ItemOrderRepository itemOrderRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.NATURALPERSON);
		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Address a1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address a2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(a1, a2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(a1, a2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		AppOrder ord1 = new AppOrder(null, sdf.parse("30/09/2017 10:32"), cli1, a1);
		AppOrder ord2 = new AppOrder(null, sdf.parse("10/10/2017 19:35"), cli1, a2);

		Payment pay1= new CreditCardPayment(null, PaymentStatus.PAID, ord1, 6);
		ord1.setPayment(pay1);

		Payment pay2 = new BilletPayment(null, PaymentStatus.PENDING, ord2, sdf.parse("20/10/2017 00:00"), null);
		ord2.setPayment(pay2);

		cli1.getOrders().addAll(Arrays.asList(ord1, ord2));

		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

		ItemOrder io1 = new ItemOrder(ord1, p1, 0.00, 1, 2000.00);
		ItemOrder io2 = new ItemOrder(ord1, p3, 0.00, 2, 80.00);
		ItemOrder io3 = new ItemOrder(ord2, p2, 100.00, 1, 800.00);

		ord1.getItems().addAll(Arrays.asList(io1, io2));
		ord2.getItems().addAll(Arrays.asList(io3));

		p1.getItems().addAll(Arrays.asList(io1));
		p2.getItems().addAll(Arrays.asList(io3));
		p3.getItems().addAll(Arrays.asList(io2));

		itemOrderRepository.saveAll(Arrays.asList(io1, io2, io3));
	}

}
