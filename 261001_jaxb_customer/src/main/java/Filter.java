import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Filter {
    public static Country getCountryByName(String name, DataHolder dataHolder){
        for(Country country : dataHolder.getCountries()){
            if(country.getName().equals(name)){
                return country;
            }
        }
        return null;
    }

    public static List<Customer> getAllCustomer(DataHolder dataHolder){
        List<Customer> customers = new ArrayList<>();
        for(Country country : dataHolder.getCountries()){
            for (City city : country.getCities()){
                customers.addAll(city.getCustomers());
            }
        }
        return null;
    }

    public static List<Customer> getAllCostumerByCity(String cityName, DataHolder dataHolder){
        List<Customer> customers = new ArrayList<>();
        for(Country country : dataHolder.getCountries()){
            for (City city : country.getCities()){
                if(city.getName().equals(cityName)){
                    customers.addAll(city.getCustomers());
                }
            }
        }
        return null;
    }

    public static List<Customer> getAllCostumerContainsCity(String cityName, DataHolder dataHolder){
        List<Customer> customers = new ArrayList<>();
        for(Country country : dataHolder.getCountries()){
            for (City city : country.getCities()){
                for(Customer customer : city.getCustomers()){
                    if (customer.getCity().getName().equals(cityName)){
                        customers.add(customer);
                    }
                }
            }
        }
        return null;
    }

    public static List<Customer> getCustomerSortedByLastAndFirstName(DataHolder dataHolder){
        List<Customer> customers = new ArrayList<>();
        for(Country country : dataHolder.getCountries()){
            for (City city : country.getCities()){
                customers.addAll(city.getCustomers());
            }
        }

        customers = customers.stream()
                .sorted(Comparator.comparing(Customer::getLastName))
                .toList();

        customers = customers.stream()
                .sorted(Comparator.comparing(Customer::getFirstName))
                .toList();
        return null;
    }
}