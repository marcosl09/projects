Marcos Lozano Mendez (mal219)

There are two interface the customer and employee. The customer interface is fully operational. The employee
interface has some issues when making a reservation for a customer.

1. Customer interface

There are two options for a customer; new customer and existing customer.

New Customer
For new customer it directs the user to by inputing their information. We require first name, the name of the street
only, city, and the standard abbreveation of the state. We also require the driver's license of the customer, which
in this world are all 10 digits long but cannot be bigger than 2.09 billion in value. The new customer then gets the
option to make a reservation.

Existing customer
For existing customers they have to input their customer id [e.g. 101492896] and they also have the option to make a
reservation.

Making a reservation
It asks the user to look up a location to get a list of car available [e.g. ATL, JFK, SEA, PHL]. It then list the
information about the cars including vehicle ids, which then is required to inputted. [Other possible vehicle id:
48842, 94273, 14231]. Then the user is required to input a start date and an end date. Then a drop off location is
needed, it could either be the same location or a different one.

2. Employee interface

There a three options for an employee: get inventory at location, create a reservation for an existing customer,
create a new customer (with option of creating a reservation as well)

Inventory
Ask for a location [e.g. ATL, ABE, MSP, JFK] and returns a list of cars available at that location

Making a reservation
Works the same as making a reservation in the customer interface

Creating a new customer
Works the same as the new customer option in the customer interface

3. Sources of data
https://www.babycenter.com/top-baby-names-2012.htm For customer names
https://en.wikipedia.org/wiki/List_of_busiest_airports_by_passenger_traffic For location codes used airport code only
picked American air codes for simplicity
Enterprise website - For model of cars and makes, and other info

4. Reflection
I was not able to get an invoice for the customer or display an existing reservation. There is no check when cars
were available between dates.
